package entity;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Utils.Consts;

public class Miner {
	
	private String address;
	private String name;
	private String password; 
	private String email;
	private double digitalProfit;
	
	
	
	public Miner(String address) {
		super();
		this.address = address;
	}

	public Miner(String address,String name, String password, String email , double digitalProfit) {
		super();
		this.address = address;
		this.name = name;
		this.password = password;
		this.email = email;
		this.digitalProfit = digitalProfit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	

	public double getDigitalProfit() {
		return digitalProfit;
	}

	public void setDigitalProfit(double digitalProfit) {
		this.digitalProfit = digitalProfit;
	} 
	
	
	

}
