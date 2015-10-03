package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.naming.InitialContext;

import ejb.ReservationInterface;
import entity.*;

public class ReservationJavaClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReservationInterface rsv;
		Client c;
		String name,email;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        try {
			InitialContext ic = new InitialContext();
			rsv = (ReservationInterface) ic.lookup("ejb.ReservationInterface");
			System.out.println("Welcome to Reservation Agency :");
			System.out.println("Enter Your Name");
			name=br.readLine();
			System.out.println("Enter Your Email");
			email=br.readLine();
			if ((rsv.findClient(email)==null))
			{c = rsv.createClient(name, email);
			System.out.println("New Inscription is Done ! "+c.getName()+" You are Welcome");}
			else
			{c=rsv.findClient(email);
			System.out.println(c.getName()+" Welcome Back !");}
			while(true)
			{
				menu();
				Scanner in = new Scanner(System.in);
				int i = in.nextInt();
				switch (i) {
				case 1:  
					String ch1,ch2,ch3,ch4,ch5;
					
					System.out.println("Would you please "+name+" give the following informations :");
					System.out.println("Enter the Show Name :");
					ch2 = br.readLine();
					System.out.println("Enter the Showroom Name :");
					ch3 = br.readLine();
					System.out.println("Enter the Number of Seats :");
					ch4 = br.readLine();
					int nb = Integer.parseInt(ch4);
					int rslt = rsv.makeReservation(ch3,ch2,nb,c);
					if (rslt > 0 )
					{ System.out.println("Reservation done by ID " + rslt); }
					else { System.out.println("Reservation not done");}
					//System.out.println("The List of all reservation");System.out.println(rsrv.showAllReservation());
					System.out.println("################################################################");
					break;				
					
				
				case 2: 
					
					System.out.println("Enter reservation ID to remove");
					int remove_id = in.nextInt(); 
					System.out.println(rsv.cancelReservation(remove_id,c.getReservations_list()));
					//System.out.println("Reservation Cancelled for ID" + remove_id);
					System.out.println("################################################################");
					break;
					
				case 3:  
					System.out.println("The list of all reservations :");
					System.out.println(rsv.showAllReservation(c));
					System.out.println("################################################################");
					break;
				case 4:  
					System.out.println("Enter reservation ID ");
					int info = in.nextInt(); 
					System.out.println(rsv.getShowName(info));
					//System.out.println("Reservation Cancelled for ID" + remove_id);
					System.out.println("################################################################");
					break;
				case 5: 
					System.out.println("Enter reservation ID ");
					int info1 = in.nextInt(); 
					System.out.println(rsv.getNbSeats(info1));
					//System.out.println("Reservation Cancelled for ID" + remove_id);
					System.out.println("################################################################");
					break;
					
				case 6:  
					System.out.println("Enter reservation ID ");
					int info2 = in.nextInt(); 
					System.out.println(rsv.getShowName(info2));
					//System.out.println("Reservation Cancelled for ID" + remove_id);
					System.out.println("################################################################");
				break;
				
				case 7: 
					
					System.out.println(rsv.deleteAccount(c));
			
					System.out.println("################################################################");
					break;

				case 8 :
					System.exit(0);
					break;

			}
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void menu() 
	{
		System.out.println("Welcome to Reservation Agency :");
		System.out.println("1. Make Reservation");		
		System.out.println("2. Cancel Reservation");
		System.out.println("3. Show all reservation");
		System.out.println("4. View Show Name");
		System.out.println("5. View Seats Remaining");
		System.out.println("6. View Showroom Name");
		System.out.println("7. Delete my account");
		System.out.println("8. QUIT");
		System.out.println("Choose an option : ");

	}
	

}
