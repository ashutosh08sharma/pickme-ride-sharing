package edu.sjsu.cmpe202.cli;

import edu.sjsu.cmpe202.db.dao.RideDao;
import edu.sjsu.cmpe202.graph.FastestTimeStrategy;
import edu.sjsu.cmpe202.graph.RoutingStrategy;
import edu.sjsu.cmpe202.graph.ShortestPathStrategy;
import edu.sjsu.cmpe202.scheduler.CarpoolScheduler;
import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author rwatsh on 8/6/16.
 */
@Data
public class Ride {
	String rideid ;
	int userid;
	String emailID;
	String sourceid;
	String destid;
	String createDate;
	String startDate;
	String status;
    public final static String DATE_FORMAT = "yyyy-MM-dd";
    public static DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    public void printReserveRideMenu(){
        System.out.println("\t [1] Reserve Ride ");
        System.out.println("\t [2] Cancel Ride");
        System.out.println("\t [3] Schedule Carpool Ride");
        System.out.println("\t [4] Dispatch Ride(s)");
        System.out.println("\t [5] Track Ride");
        System.out.println("\t [6] Go back to main menu");
    }

    public void handleRideReservation() {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t Rider mail ID: ");
		emailID = scanner.nextLine();
        System.out.println("\t\t Pickup Location: ");
        sourceid = scanner.nextLine();
        System.out.println("\t\t Destination Location: ");
        destid = scanner.nextLine();

        String pickupTime = "\t\t Pick up Time(yyyy-MM-dd HH:mm:ss:";
		startDate = Utilities.getDateTimeString(pickupTime);
        createDate = dateFormat.format(new Date());
        status = RideStatus.PENDING.name();
        userid = RideDao.getRiderID(emailID);
        RideDao.addRideRequest(this);
        System.out.println("\t\t We have recieved your Ride Request: " + this);
    }

    public void handleRideTracking() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("\t\t Provide Ride ID: ");
    	rideid = scanner.nextLine();
    	if(rideid != null) {
    		status = RideDao.getRideStatus(rideid);
    		System.out.println("Status of the your Ride with ID : " + this.getRideid() +
    				" is " + this.getStatus());
    	}
    	else
    		System.out.println("\t\t Please provide correct RideID");

    }

    public void handleRideCancelation() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("\t\t Provide Ride ID: ");
    	rideid = scanner.nextLine();
    	if(rideid != null) {
    		RideDao.deleteRequestedRide(rideid);
    		System.out.println("Your requested Ride is cancelled");
    	}
    	else
    		System.out.println("\t\t Please provide correct RideID");
    }

   /* public void getRideDetails(Ride r) {
        return "RideId" + r.getRideid() + "\t" + "RiderID" + r.getRideid() + "\t" +
    } */

    public void handleDispatch() {
        CarpoolScheduler.getInstance().dispatchCarpools();
    }

    public void handleSchedule() {
        Scanner scanner = new Scanner(System.in);
        RoutingStrategy strategy = null;

        loop:while(true) {
            printRouteSelectionMenu();
            String menuSelected = scanner.nextLine();
            switch (menuSelected.trim()) {
                case "1":
                    strategy = new ShortestPathStrategy(PickMe.algorithm);
                    break;
                case "2":
                    strategy = new FastestTimeStrategy(PickMe.algorithm);
                    break;
                case "3":
                    break loop;
                default:
                    System.out.println("ERROR: Unknown menu option. Please retry.");
                    break;
            }
        }

        CarpoolScheduler.getInstance().scheduleRides(strategy);
    }

    private void printRouteSelectionMenu() {
        System.out.println("\t\t Select Routing Strategy: ");
        System.out.println("\t\t [1] Shortest Path Route");
        System.out.println("\t\t [2] Fastest Time Route");
        System.out.println("\t\t [3] Go back to previous menu");
    }
}

