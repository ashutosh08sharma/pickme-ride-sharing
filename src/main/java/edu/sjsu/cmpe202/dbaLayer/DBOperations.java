package edu.sjsu.cmpe202.dbaLayer;

import edu.sjsu.cmpe202.cli.Membership;
import edu.sjsu.cmpe202.cli.Vehicle;

import java.util.List;

import org.sql2o.Connection;

import java.util.List;

public class DBOperations {
    public static void createRider(Membership membership) {
        String sql =
                "INSERT INTO member (first_name, last_name, dob, address, contact, email, is_driver) " +
                        "VALUES (:first_name, :last_name, :dob, :address, :contact, :email, :is_driver)";

        try (Connection con = (new SQLConnection()).getConnection()) {
            con.createQuery(sql)
                    .addParameter("first_name", membership.getFirstName())
                    .addParameter("last_name", membership.getLastName())
                    .addParameter("dob", membership.getDob())
                    .addParameter("address", membership.getAddress())
                    .addParameter("contact", membership.getPhone())
                    .addParameter("email", membership.getEmail())
                    .addParameter("is_driver", "N")
                    .executeUpdate();
        }

    }

    public static void createDriver(Membership membership) {
        String memberSql =
                "INSERT INTO member (first_name, last_name, dob, address, contact, email, is_driver) " +
                        "VALUES (:first_name, :last_name, :dob, :address, :contact, :email, :is_driver)";

        String fetchMemberSql = "SELECT member_id FROM member WHERE email = :email";

        String driverSql = "INSERT INTO driver_details (member_id, license_number, expiry_date) " +
                "VALUES (:member_id, :license_number, :expiry_date)";

        try (Connection con = (new SQLConnection()).getConnection()) {
             con.createQuery(memberSql)
                    .addParameter("first_name", membership.getFirstName())
                    .addParameter("last_name", membership.getLastName())
                    .addParameter("dob", membership.getDob())
                    .addParameter("address", membership.getAddress())
                    .addParameter("contact", membership.getPhone())
                    .addParameter("email", membership.getEmail())
                    .addParameter("is_driver", "Y")
                    .executeUpdate();

            List<Member> members = con.createQuery(fetchMemberSql)
                    .addParameter("email", membership.getEmail())
                    .executeAndFetch(Member.class);

            Member m = members.get(0);

            con.createQuery(driverSql)
                    .addParameter("member_id", m.getMemberId())
                    .addParameter("license_number", membership.getDriverLicence())
                    .addParameter("expiry_date", membership.getExpiration())
                    .executeUpdate();
        }

    }
    
    public static void createVehicle(Vehicle vehicle) {
    	
    	String vehicleinfo =
    			"INSERT INTO vehicle (owner_id,firstname,capacity)" +
    	                   "VALUES(:owner_id, :firstname, :capacity)";
        try (Connection con = (new SQLConnection()).getConnection()) {
            con.createQuery(vehicleinfo)
                    .addParameter("owner_id",vehicle.getOwnerID())
                    .addParameter("firstname", vehicle.getFirstname())
                    .addParameter("capacity", vehicle.getCapacity())
                    .executeUpdate();
        }
    	
    }
    
    public static void deleteVehicle(int vehicleID)
    {
    	String deleteVehicle = " DELETE * from vehicle where vehicle_id = :vehicle_id" ;
    	try (Connection con = (new SQLConnection()).getConnection()) {
            con.createQuery(deleteVehicle)
                    .addParameter("vehicle_id",vehicleID)
                    .executeUpdate();
        }
    }
    
    public static List<Vehicle> showVehiclesOfOwner(int OwnerID)
    {
    	String vehicles = "SELECT * FROM vehicle where owner_id = :owner_id";
    	List<Vehicle> vehiclers;
    	try (Connection con = (new SQLConnection()).getConnection()) {
           vehiclers =  con.createQuery(vehicles)
                    .addParameter("owner_id",OwnerID)
                    .executeAndFetch(Vehicle.class);
        }
    	
    	return vehiclers;
    }
    
    

}
