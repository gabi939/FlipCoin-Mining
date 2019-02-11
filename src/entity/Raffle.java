package entity;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import Exceptions.RaffleException;
import Utils.Consts;

/**
 * 
 * 
 * 
 * 
 * @author Gabi Malin
 *
 */
public final class Raffle {

	private int RaffleId;
	private Date raffleDate;
	private int maxMiners;
	private int numWinners;
	private int numBenefits;

	
	
	
	
	public Raffle(int raffleId) {
		super();
		RaffleId = raffleId;
	}

	public Raffle(int raffleId, Date raffleDate, int maxMiners, int numWinners, int numBenefits) {
		super();
		RaffleId = raffleId;
		this.raffleDate = raffleDate;
		this.maxMiners = maxMiners;
		this.numWinners = numWinners;
		this.numBenefits = numBenefits;
	}

	public Date getRaffleDate() {
		return raffleDate;
	}

	public void setRaffleDate(Date raffleDate) throws RaffleException {

		try {

			if (raffleDate != null) {
				this.raffleDate = raffleDate;
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try {
					Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall("UPDATE tblRaffle SET tblRaffle.raffleDate = ?\r\n"
							+ "WHERE (((tblRaffle.RaffleId)=?));\r\n" + "");

					stmt.setDate(1, new java.sql.Date(raffleDate.getTime()));
					stmt.setInt(2, RaffleId);
					stmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else
				throw new RaffleException("Raffle date cant be empty");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public int getMaxMiners() {
		return maxMiners;
	}

	public void setMaxMiners(int maxMiners) throws RaffleException {

		if (maxMiners > 0) {
			try {
				this.maxMiners = maxMiners;
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try {
					Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall("UPDATE tblRaffle SET tblRaffle.MaxMiners = ?\r\n"
							+ "WHERE (((tblRaffle.RaffleId)=?));\r\n" + "");

					stmt.setInt(1, maxMiners);
					stmt.setInt(2, RaffleId);
					stmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else
			throw new RaffleException("Max miners must be positive amount");
	}

	public int getNumWinners() {
		return numWinners;
	}

	public void setNumWinners(int numWinners) throws RaffleException {

		if (numWinners > 0) {

			this.numWinners = numWinners;
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try {
					Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall("UPDATE tblRaffle SET tblRaffle.NumWinners = ?\r\n"
							+ "WHERE (((tblRaffle.RaffleId)=?));\r\n" + "");

					stmt.setInt(1, numWinners);
					stmt.setInt(2, RaffleId);
					stmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else
			throw new RaffleException("number of Winners must be positive amount");
	}

	public int getNumBenefits() {
		return numBenefits;
	}

	public void setNumBenefits(int numBenefits) throws RaffleException {
		if (numBenefits > 0) {
			this.numBenefits = numBenefits;
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try {
					Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall("UPDATE tblRaffle SET tblRaffle.NumBenefits = ?\r\n"
							+ "WHERE (((tblRaffle.RaffleId)=?));\r\n" + "");

					stmt.setInt(1, numBenefits);
					stmt.setInt(2, RaffleId);
					stmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else
			throw new RaffleException("number of Benefits must be positive amount");
	}

	public int getRaffleId() {
		return RaffleId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + RaffleId;
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
		Raffle other = (Raffle) obj;
		if (RaffleId != other.RaffleId)
			return false;
		return true;
	}

	
	
}
