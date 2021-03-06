package com.bean.singleton;

import com.Offer;
import com.Q_entry;
import com.Tag;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;

/**
 *
 * @author Russo , Didomenico e Delpopolo
 */
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public abstract class State implements StateLocal{
    
    private int priority;     //Usato per tracciare l’highest proposed timestamp
    private LinkedList<Q_entry> temp_Q,delivery_Q;
    private int LocalTimestamp;    
    private int lock;
    
    LinkedList<Offer> DB;
    
    public State() {
        priority=0;
        temp_Q=new LinkedList<>();
        delivery_Q=new LinkedList<>();
        LocalTimestamp=0;
        lock=0;  
        testDB();
    }
    
    
    
    @Override
    @Lock(LockType.READ)
    public int getPriority(){return priority;}
    
    @Override
    @Lock(LockType.WRITE)
    public void setPriority(int priority){this.priority=priority;}
    
    @Override
    @Lock(LockType.WRITE)
    public int lock(){
        return lock++;
    }
    
    @Override
    @Lock(LockType.WRITE)
    public int unlock(){
        if(lock>0) lock--;
        return lock;
    }
    
    @Override
    @Lock(LockType.READ)
    public boolean isLock(){return !(lock==0);}
    
    @Override
    @Lock(LockType.READ)
    public int getLocalTimestamp(){return LocalTimestamp;}
    
    @Override
    @Lock(LockType.WRITE)
    public void setLocalTimestamp(int ts){this.LocalTimestamp=ts;}
    
    
    @Override
    @Lock(LockType.WRITE)
    public void addTemp_Q(Q_entry e){
        temp_Q.add(e);
    }
   
    @Override
    @Lock(LockType.READ)
    public LinkedList<Q_entry> getTemp_Q(){return temp_Q;}
    
    @Override
    @Lock(LockType.READ)
    public Q_entry getEntry(Tag tag){
        for(Q_entry e:temp_Q)
            if(e.getTag().compareTo(tag)==0)
                return e;
        return null;
    }
     
    @Override
    @Lock(LockType.READ)
    public LinkedList<Q_entry>  getCommitQueue(){return delivery_Q;}
    
    
        //Solo per testare l'applicazione senza i database
    private void testDB(){
        DB=new LinkedList<>();        
        DB.add(new Offer("ID1","AGV923","Nico","Leo","Salvo",""));
        DB.add(new Offer("ID2","ADJ325","Tizio", "Caio", "Sempronio",""));
        DB.add(new Offer("ID3","T56G2G","Antonella", "Daniele","",""));
    }
    
    @Override
    public List<Offer> getDB(){return DB;}
}
