package entity;

import java.io.Serializable;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Entity
public class Reservation implements Serializable{

	private int id;
	private String showroomName;
	private String showName;
	private static int nbSeats;
	private Client client;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getShowroomName() {
		return showroomName;
	}
	
	public void setShowroomName(String showroomName) {
		this.showroomName = showroomName;
	}
	
	public String getShowName() {
		return showName;
	}
	
	public void setShowName(String showName) {
		this.showName = showName;
	}
	
	@Column(name="SEATS_REMAINING")
	public int getNbSeats() {
		return nbSeats;
	}
	
	public void setNbSeats(int nbSeats) {
		this.nbSeats = nbSeats;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="CLIENT_ID")
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
}
