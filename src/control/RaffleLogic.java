package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Utils.Consts;
import entity.Benefit;
import entity.Miner;
import entity.Raffle;
import entity.RiddleLevel;

public abstract class RaffleLogic {

	public static ArrayList<Raffle> getAllRaffles() {
		ArrayList<Raffle> results = new ArrayList<>();

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tblRaffle");
				ResultSet rs = stmt.executeQuery();

				while (rs.next())
					results.add(new Raffle(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)));

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;

	}

	public static ArrayList<Raffle> getAllRafflesUserCanJoin(Miner miner) {

		ArrayList<Raffle> raffles = getAllRaffles();
		ArrayList<Raffle> results = new ArrayList<>();

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn
						.prepareStatement("SELECT RaffleId FROM tblParticipation WHERE Address = ?");

				stmt.setString(1, miner.getAddress());
				ResultSet rs = stmt.executeQuery();

				while (rs.next())
					results.add(new Raffle(rs.getInt(1)));

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		raffles.removeAll(results);
		return raffles;

	}

	public static ArrayList<Benefit> getAllBenefits() {
		ArrayList<Benefit> results = new ArrayList<>();

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tblBenefit");
				ResultSet rs = stmt.executeQuery();

				while (rs.next())
					results.add(new Benefit(rs.getInt(1), rs.getString(2)));

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;

	}

	public static void addToRaffle(Miner miner, Raffle raffle) {

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO tblParticipation VALUES (?, ?, ?)");

				stmt.setBoolean(3, false);
				stmt.setString(2, miner.getAddress());
				stmt.setInt(1, raffle.getRaffleId());

				stmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}

	}

	public static ArrayList<Raffle> getAllRafflesUserJoined(Miner miner) {

		ArrayList<Raffle> results = new ArrayList<>();

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn
						.prepareStatement("SELECT * FROM tblRaffle INNER JOIN tblParticipation ON tblRaffle.RaffleId = tblParticipation.RaffleId WHERE tblParticipation.Address = ? AND  won = false");

				stmt.setString(1, miner.getAddress());
				ResultSet rs = stmt.executeQuery();

				while (rs.next())
					results.add(new Raffle(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)));

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return results;

	}

	public static void removeFromRaffle(Miner user, Raffle raf) {

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn
						.prepareStatement("DELETE FROM tblParticipation WHERE Address = ? AND RaffleId = ?  ");

				stmt.setString(1, user.getAddress());
				stmt.setInt(2, raf.getRaffleId());
				stmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public static int getBenfitCount() {
		int count = 0;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn
						.prepareStatement("SELECT Count(*) FROM tblBenefit");
			ResultSet rs =	stmt.executeQuery();
			rs.next();
			count = rs.getInt(1);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return count;
	}

	public static boolean addBenefit(String answer) {
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO tblBenefit VALUES (?,?)");

				stmt.setString(2, answer);
				stmt.setString(1, null);

				stmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();


		}
		return true;

	}

	public static void removeBenefit(Benefit ben) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("DELETE FROM tblBenefit WHERE BenefitId = ?");

				stmt.setInt(1, ben.getBenefitId());

				stmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();


		}
		
		
	}

	public static ArrayList<RiddleLevel> getAllLevels() {
		ArrayList<RiddleLevel> results = new ArrayList<>();

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tblDifficulty");
				ResultSet rs = stmt.executeQuery();

				while (rs.next())
					results.add(new RiddleLevel(rs.getString(1),rs.getInt(2),rs.getInt(3)));

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}

	public static void addLevel(RiddleLevel toUpdate) {
		

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO tblDifficulty VALUES (?, ?, ?)");

				stmt.setInt(3, toUpdate.getBlockSize());
				stmt.setInt(2, toUpdate.getDifficulty());
				stmt.setString(1, toUpdate.getName());

				stmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}

	}

	public static void removeLevel(RiddleLevel rid) {

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("DELETE FROM tblDifficulty WHERE LevelName = ?");

				stmt.setString(1, rid.getName());

				stmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();


		}
	}

	public static Raffle addRaffle(Raffle raf) {

		int index = getLastRaffleRecord();
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO tblRaffle VALUES (?,?,?,?,?)");
				
				stmt.setInt(5, raf.getNumBenefits());
				stmt.setInt(4, raf.getNumWinners());
				stmt.setInt(3, raf.getMaxMiners());
				stmt.setDate(2,new Date(raf.getRaffleDate().getTime()));
				stmt.setInt(1,index);
				stmt.executeUpdate();

				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
		
		
		return new Raffle(index, raf.getRaffleDate(),  raf.getMaxMiners(), raf.getNumWinners(), raf.getNumBenefits());
		
	}
	
	
	
	public static int getLastRaffleRecord() {
		int amount = 0;
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("SELECT TOP 1 RaffleId FROM tblRaffle ORDER BY RaffleId DESC");
			
				ResultSet rs = stmt.executeQuery();
				rs.next();

				amount = rs.getInt(1);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
		
		return amount+1;
		
		
	}

	public static void removeRaffle(Raffle raf) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("DELETE FROM tblRaffle WHERE RaffleId = ?");

				stmt.setInt(1, raf.getRaffleId());

				stmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();


		}
		
	}
	
	
	
	
	
	
}
