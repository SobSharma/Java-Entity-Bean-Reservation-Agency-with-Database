package ejb;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Remote;

import entity.*;

@Remote
public interface ReservationInterface {

	public Client createClient(String name, String email);
	public Client findClient(String email);
	public int makeReservation(String showroomName, String showName, int nbSeats, Client c);
	public String cancelReservation(int idRsrv, Collection<Reservation> r);
	public String getShowroomName (int idRsrv);
	public String getShowName (int idRsrv);
	public int getNbSeats(int idRsrv);
	public String showAllReservation(Client c);
	public String deleteAccount(Client c);
	
}
