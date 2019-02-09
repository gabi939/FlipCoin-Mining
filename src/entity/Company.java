package entity;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Utils.Consts;

/**
 * 
 * 
 * added the ability to set all company
 *  parameters throw the set methods 
  * @author Gabi Malin
 *
 */
public class Company extends Miner{

	private String contactFirstName;
	private String contactFamilyName;
	private String contactPhone;
	private String contactEmail;

	
	
	
	public Company(String address) {
		super(address);
		// TODO Auto-generated constructor stub
	}
	
	public Company(String address, String name, String password, String email ,double digitalProfit,String contactFirstName,String contactFamilyName, String contactPhone,String contactEmail) {
		super(address, name, password, email,digitalProfit);
		this.contactFirstName=contactFirstName;
		this.contactFamilyName=contactFamilyName;
		this.contactPhone=contactPhone;
		this.contactEmail=contactEmail;
	}

	
	public String getContactFirstName() {
		return contactFirstName;
	}

	
	
	
	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				CallableStatement stmt = conn.prepareCall("UPDATE tblCompany SET tblCompany.ContactFirstName = ?\r\n" + 
						"WHERE (((tblCompany.Address)=?));\r\n" + 
						"");
				
				stmt.setString(1, contactFirstName);
				stmt.setString(2, getAddress());
				stmt.executeUpdate();
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getContactFamilyName() {
		return contactFamilyName;
	}

	public void setContactFamilyName(String contactFamilyName) {
		this.contactFamilyName = contactFamilyName;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				CallableStatement stmt = conn.prepareCall("UPDATE tblCompany SET tblCompany.ContactLastName = ?\r\n" + 
						"WHERE (((tblCompany.Address)=?));\r\n" + 
						"");
				
				stmt.setString(1, contactFamilyName);
				stmt.setString(2, getAddress());
				stmt.executeUpdate();
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				CallableStatement stmt = conn.prepareCall("UPDATE tblCompany SET tblCompany.Phone = ?\r\n" + 
						"WHERE (((tblCompany.Address)=?));\r\n" + 
						"");
				
				stmt.setString(1, contactPhone);
				stmt.setString(2, getAddress());
				stmt.executeUpdate();
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
		
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				CallableStatement stmt = conn.prepareCall("UPDATE tblCompany SET tblCompany.contactEmail = ?\r\n" + 
						"WHERE (((tblCompany.Address)=?));\r\n" + 
						"");
				
				stmt.setString(1, contactEmail);
				stmt.setString(2, getAddress());
				stmt.executeUpdate();
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
