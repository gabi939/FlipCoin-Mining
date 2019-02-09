package control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Utils.Consts;
import Utils.Type;
import entity.Company;
import entity.Miner;
import entity.Transaction;

/**
 * 
 * A class to manage everything that demands miner details 
 * 
 * 
 * @author Gabi Malin
 *
 */
public abstract class MinerLogic {
	
	
	
	/**
	 * 
	 * check if entity exists in the DB 
	 * 
	 * @param name
	 * @return
	 */
	public static boolean checkIfMinerExists(String name) {

		
		
		// count the amount of users with this name more then 0 return false
		
		int amount = 0;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try { 
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement("SELECT Count(*) FROM tblMiner WHERE MinerName = ? ");
					stmt.setString(1, name);
					ResultSet rs = stmt.executeQuery();
					rs.next();
					amount = rs.getInt(1);
					
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return amount > 0 ;
		}
	

	/**
	 * 
	 * adds company to the system 
	 * 
	 * @param company
	 * @return
	 */
	public static boolean addingCompany(Company company) {
		
		
		
		
		// adding to tblminer
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO tblminer VALUES (?, ?, ?,?,? )");
				
				stmt.setString(1, company.getAddress());
				stmt.setString(2, company.getName());
				stmt.setString(3, company.getPassword());
				stmt.setString(4, company.getEmail());
				stmt.setDouble(5, company.getDigitalProfit());

				stmt.executeUpdate();
				

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;

		}
		
		//adding to tblCompany
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO tblCompany VALUES (?, ?, ?,?,? )");
				
				stmt.setString(1, company.getAddress());
				stmt.setString(2, company.getContactFirstName());
				stmt.setString(3, company.getContactFamilyName());
				stmt.setString(4, company.getContactPhone());
				stmt.setString(5, company.getContactEmail());

				stmt.executeUpdate();
				

			} catch (SQLException e) {
				e.printStackTrace();
				return false;

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;

		}
		
		return true;
		
		
	}
	
	/*
	 *adds miner to the system  
	 */
	public static boolean addingMiner(Miner miner) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO tblminer VALUES (?, ?, ?,?,? )");
				
				stmt.setString(1, miner.getAddress());
				stmt.setString(2, miner.getName());
				stmt.setString(3, miner.getPassword());
				stmt.setString(4, miner.getEmail());
				stmt.setDouble(5, miner.getDigitalProfit());

				stmt.executeUpdate();
				

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;

		}
		return true;
	}
	
	
	
	

}
