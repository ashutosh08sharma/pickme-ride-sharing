package edu.sjsu.cmpe202.dbaLayer;

import lombok.Data;

import java.util.Date;

/**
 * @author rwatsh on 8/7/16.
 */
@Data
public class Member {
    private int memberId;
    private String firstName;
    private String lastName;
    private Date date;
    private String address;
    private int contact;
    private String email;
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
}
