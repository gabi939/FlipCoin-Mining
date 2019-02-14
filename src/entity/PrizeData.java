package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utils.Consts;

public final class PrizeData {
	private int RaffleId;
	private String Address;
	private String BenefitId;
	private String prize;
	
	
	
	
	
	
	public int getRaffleId() {
		return RaffleId;
	}
	public String getAddress() {
		return Address;
	}
	public String getBenefitId() {
		return BenefitId;
	}
	public String getPrize() {
		
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("SELECT Description FROM tblBenefit WHERE BenefitId = ? ");
				ResultSet rs = stmt.executeQuery();
				prize = rs.getString(1);

			

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		return prize;
	}
	
	
	
}
