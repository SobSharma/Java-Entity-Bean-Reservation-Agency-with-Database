package ejb;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.google.common.collect.Iterators;

import entity.Client;
import entity.Reservation;

@Stateless(name="ejb/ReservationInterface")

public class ReservationBean implements ReservationInterface {

	@PersistenceContext(unitName="pura1")
    private EntityManager em;
	private static int nbSeatsRemaining=100;
	
	@Override
	public Client createClient(String name, String email) {
	
		Client c = new Client();
		c.setName(name);
		c.setEmail(email);

		em.persist(c);
		return c;
	}
	
		@Override
	public int makeReservation(String showroomName, String showName, int nbSeats, Client c) {
		
			if (checkIfAvailable(nbSeats))
	{
		Reservation r = new Reservation ();
		r.setShowroomName(showroomName);
		r.setShowName(showName);
		r.setNbSeats(nbSeats);
		Collection<Reservation> r0 = c.getReservations_list();
		r0.add(r);
		c.setReservations_list(r0);
		r.setClient(c);
		em.persist(r);
		
		return r.getId();
	}
			else 
			{
				return -1;
			}
		
	}

		private boolean checkIfAvailable (int nb)
		{
			if (nbSeatsRemaining>=nb)
			{ 
				nbSeatsRemaining = nbSeatsRemaining - nb;
				return true;
			}
			else return false;
		}
		
	@Override
	public String cancelReservation(int idRsrv, Collection <Reservation> r) {
		String ch="";
		for (int i=0;i<r.size();i++)
		{
			
			if((Iterators.get(r.iterator(), i).getId())==idRsrv)
			{
				r.remove(i);
				Query q = em.createQuery("Delete FROM Reservation WHERE id= :id");
				q.setParameter("id", idRsrv);
				int x = q.executeUpdate();
		        //q.setParameter("id",idRsrv);
		      
				return "Reservation is canceled for ID "+idRsrv;
			}
			else ch = "Reservation can not be canceled, check your ID";
		}
		return ch;
	}

	@Override
	public String getShowroomName(int idRsrv) {
		Query q = em.createQuery("select r from Reservation r where r.id = :id");
        q.setParameter("id", idRsrv);
        Reservation r = (Reservation) q.getSingleResult();
        return r.getShowroomName();
	}

	@Override
	public String getShowName(int idRsrv) {
		
		Query q = em.createQuery("select r from Reservation r where r.id = :id");
        q.setParameter("id", idRsrv);
        Reservation r = (Reservation) q.getSingleResult();
        return r.getShowName();
	}

	@Override
	public int getNbSeats(int idRsrv) {
		
		Query q = em.createQuery("select r from Reservation r where r.id = :id");
        q.setParameter("id", idRsrv);
        Reservation r = (Reservation) q.getSingleResult();
        return (100-r.getNbSeats());
	}

	@Override
	public String showAllReservation(Client c) {
		String ch="";
		Collection<Reservation> r0 = c.getReservations_list();
		for (int i=0; i<r0.size();i++)
        	{ch = ch +Iterators.get(r0.iterator(), i).getId()+"|";}
		return ch;
	}

	@Override
    public Client findClient(String email) {

        Query q = em.createQuery("select c from Client c where c.email = :email");
        q.setParameter("email", email);
        if (q.getResultList().size()==0) return null;
        else return (Client)q.getSingleResult();
        
    }

	@Override
	public String deleteAccount(Client c) {
		// Merge the customer to the new persistence context
        Client c0 = em.merge(c);

        // Delete all records.
        em.remove(c0);
        return "Account is deleted ";
	}

		

}
