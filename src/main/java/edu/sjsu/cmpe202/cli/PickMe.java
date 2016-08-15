package edu.sjsu.cmpe202.cli;


import edu.sjsu.cmpe202.graph.Graph;
import edu.sjsu.cmpe202.route.RouteMapGraph;

import java.util.Scanner;

/**
 * PickMe is the name of our application for carpooling. It provides a menu based interface.
 [1] Membership
 [2] Vehicle Registration
 [3] Ride
 [4] Payment
 [5] Notifications
 [6] Parking
 [6] Quit
 User can register as rider or driver, can reserve, cancel or track a carpool ride,
 make or receive payments and check notifications sent by application to the user at
 various stages.
 */
public class PickMe
{
    public static final Graph routeMapGraph = RouteMapGraph.loadRouteMap();

    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            printMainMenu();
            String menuOptionSelected = scanner.nextLine();
            switch (menuOptionSelected.trim()) {
                case "1":
                    handleMembership();
                    break;
                case "2":
                    handleVehicleRegistration();
                    break;
                case "3":
                    handleRides();
                    break;
                case "4":
                    handlePayment();
                    break;
                case "5":
                    handleNotifications();
                    break;
                case "6":
                    handleParking();
                case "7":
                    System.exit(0);
                default:
                    System.out.println("ERROR: Unknown menu option. Please retry.");
                    break;
            }
        }
    }

    private static void handleVehicleRegistration() {
        Scanner scanner= new Scanner(System.in);
        VehicleRegistration vehicle = new VehicleRegistration();

        loop: while(true){
            vehicle.printVehicleMenu();
            String menuSelected = scanner.nextLine();
            switch (menuSelected.trim()) {
                case "1":
                    vehicle.handleVehicleRegistration();
                    break;
                case "2":
                    vehicle.handleDeleteVehicle();;
                    break;
                case "3":
                    break loop;
                default:
                    System.out.println("ERROR: Unknown menu option. Please retry.");
                    break;
            }
        }
    }

    private static void handleParking() {
        Scanner scanner = new Scanner(System.in);
        ParkingDetails pDetails= new ParkingDetails();

        loop:while(true) {
            pDetails.printReserveParkingMenu();
            String menuSelected = scanner.nextLine();
            switch (menuSelected.trim()) {
                case "1":
                    pDetails.handleParkingReservation();
                    break;
                case "2":
                    pDetails.handleParkingCancellation();
                    break;
                case "3":
                    break loop;
                default:
                    System.out.println("ERROR: Unknown menu option. Please retry.");
                    break;
            }
        }
    }


    private static void handlePayment() {
        Scanner scanner = new Scanner(System.in);
        Payment payment = new Payment();
        loop:
        while (true) {
            payment.printPaymentMenu();
            String menuSelected = scanner.nextLine();
            switch (menuSelected.trim()) {
                case "1":
                    payment.addCard();
                case "2":
                    payment.handleRidePayment();
                    break;
                case "3":
                    payment.handleParkingPayment();
                    break;
                case "4":
                    payment.handlePaymentDetails();
                case "5":
                    break loop;
                default:
                    System.out.println("ERROR: Unknown menu option. Please retry.");
                    break;
            }
        }

    }

    private static void handleNotifications() {
        Scanner scanner = new Scanner(System.in);
        Notifier notifier = new Notifier();

        loop: while(true) {
            notifier.printNotificationMenu();
            String menuSelected = scanner.nextLine();
            switch ((menuSelected.trim())) {
                case "1":
                    notifier.showNotification();
                    break;
                case "2":
                    break loop;
                case "3":
                    System.out.println("ERROR: Unknown menu option. Please retry.");
            }
        }

    }

    private static void handleRides() {
        Scanner scanner= new Scanner(System.in);
        Ride ride = new Ride();

        loop: while(true){
            ride.printReserveRideMenu();
            String menuSelected = scanner.nextLine();
            switch (menuSelected.trim()) {
                case "1":
                    ride.handleRideReservation();
                    break;
                case "2":
                    ride.handleRideCancelation();
                    break;
                case "3":
                    ride.handleDispatch();
                    break;
                case "4":
                    ride.handleRideTracking();
                    break;
                case "5":
                    break loop;
                default:
                    System.out.println("ERROR: Unknown menu option. Please retry.");
                    break;
            }
        }
    }

    private static void handleMembership() {
        Scanner scanner = new Scanner(System.in);
        Membership membership = new Membership();
        loop: while (true) {
            membership.printMembershipMenu();
            String menuSelected = scanner.nextLine();

            switch (menuSelected.trim()) {
                case "1":
                    membership.handleRiderSignup();
                    break;
                case "2":
                    membership.handleDriverSignup();
                    break;
                case "3":
                    break loop;
                default:
                    System.out.println("ERROR: Unknown menu option. Please retry.");
                    break;
            }
        }

    }

    private static void printMainMenu() {
        System.out.println("[1] Membership");
        System.out.println("[2] Vehicle Registration");
        System.out.println("[3] Ride");
        System.out.println("[4] Payment");
        System.out.println("[5] Notifications");
        System.out.println("[6] Parking");
        System.out.println("[7] Quit");
        System.out.println();
        System.out.println("Enter your choice: ");
    }
}