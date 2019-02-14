package entity;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Utils.Consts;

public final class PartData {
	private String address;
	private Integer RaffleId;
	private Boolean win;

	public PartData( Integer RaffleId, String address,Boolean win) {
		super();
		this.address = address;
		this.RaffleId = RaffleId;
		this.win = win;
	}

	public String getAddress() {
		return address;
	}

	public Integer getRaffleId() {
		return RaffleId;
	}

	public Boolean getWin() {
		return win;
	}

	public void setWin(Boolean win) {
		if (win != null) {
			this.win = win;
			
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try {
					Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall("UPDATE tblParticipation SET Won = ?\r\n"
							+ "WHERE RaffleId = ? AND Address = ? ");

					stmt.setBoolean(1, win);
					stmt.setInt(2, RaffleId);
					stmt.setString(3, address);
					stmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			

		}
	}
}
