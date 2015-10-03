package entity;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

@Entity
public class Client implements Serializable{

	private int id;
	private String name;
	private String email;
	private static Collection<Reservation> reservations_list = new ArrayList<Reservation>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@OneToMany(cascade=ALL, mappedBy="client", fetch=FetchType.EAGER)
	public Collection<Reservation> getReservations_list() {
		return reservations_list;
	}
	
	public void setReservations_list(Collection<Reservation> reservations_list) {
		this.reservations_list = reservations_list;
	}
	
}
