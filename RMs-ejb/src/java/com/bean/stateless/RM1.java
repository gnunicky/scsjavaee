package com.bean.stateless;

import com.Offer;
import com.Reservation;
import com.bean.singleton.StateLocal;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Leandro
 */
@Stateless(mappedName = "RM1Bean")
public class RM1 extends RM implements RMLocal{

    @EJB(beanName = "State1")
    private StateLocal state;

    @Resource(name = "ID")
    private int ID;
    
    
    
    public RM1() {    
    }

    

    @Override
    public void writeDB(Offer o){        
        System.out.println("RM"+getID()+" I've write DB"+getID());        
        state.getDB().add(o);
    }
    
    @Override
    public void writeDB(Reservation r){
        System.out.println("RM"+getID()+" I've write DB"+getID());
        
        String offerID=r.getOfferID();
        String passengerID=r.getPassengerID();
        
        for(Offer o:state.getDB()){
            if(o.getOfferID().equalsIgnoreCase(offerID)){
                o.addPassenger(passengerID);
            }
        }
    }

    @Override
    public Object readDB() {
        System.out.println("RM"+getID()+" I've read DB"+getID());
        return state.getDB();
    }

    @Override
    public int getID() {
        return ID;
    }

    

    @Override
    public StateLocal getState() {
        return state;
    }
    
}
