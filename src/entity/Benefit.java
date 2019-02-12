package entity;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Utils.Consts;

public final class Benefit {

	private int benefitId;
	private String description;
	
	
	
	
	public Benefit(int benefitId) {
		super();
		this.benefitId = benefitId;
	}




	public Benefit(int benefitId, String description) {
		super();
		this.benefitId = benefitId;
		this.description = description;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		if(description != null) {
			this.description = description;
			
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try {
					Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall("UPDATE tblBenefit SET tblBenefit.Description = ?\r\n"
							+ "WHERE (((tblBenefit.BenefitId)=?));\r\n" + "");

					stmt.setString(1, description);
					stmt.setInt(2, benefitId);
					stmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			
		}
	}




	public int getBenefitId() {
		return benefitId;
	}




	@Override
	public String toString() {
		return description;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + benefitId;
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Benefit other = (Benefit) obj;
		if (benefitId != other.benefitId)
			return false;
		return true;
	}
	
	
	
	
}
