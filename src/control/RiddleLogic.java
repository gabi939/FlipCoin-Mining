package control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Utils.Consts;
import Utils.RiddleStatus;
import entity.Miner;
import entity.Riddle;
import entity.RiddleLevel;

public abstract class RiddleLogic {

	/*
	 * 8 get all riddle that can be solved
	 */
	public static ArrayList<Riddle> getSolvableRiddles() {
		ArrayList<Riddle> results = new ArrayList<>();

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn
						.prepareStatement("SELECT * FROM tblRiddle WHERE tblRiddle.Status = 'solvable'");
				ResultSet rs = stmt.executeQuery();

				while (rs.next())
					results.add(new Riddle(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getDate(4),
							RiddleStatus.valueOf(rs.getString(5)), rs.getString(6)));

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}

	
	/**
	 * updates the status of all riddles 
	 */
	public static void irreleventCheck() {


			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try {
					Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(
							"UPDATE tblRiddle SET Status ='irrelevent' WHERE SolutionFinishTime < ? AND  Status = 'solved' ");
					stmt.setDate(1, new Date(new java.util.Date().getTime()));
					stmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}

	/**
	 * 
	 * checks the answer of the user
	 * 
	 * @param riddle
	 * @param answer
	 */
	public static boolean checkAnswer(Riddle riddle , String answer) {

		int amount = 0;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				CallableStatement stmt = conn.prepareCall("SELECT Count(*) FROM tbSolution WHERE RiddleId = ? AND answer = ? ");
				stmt.setInt(1, riddle.getRiddleId());
				stmt.setString(2, answer);

				
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
	 * adds the first miner to solve the riddle 
	 * 
	 * @param miner
	 * @param riddle
	 */
	public static void addFirstToSolve(Miner miner , Riddle riddle) {
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO tblSolved VALUES (?, ?, ? )");
				
				stmt.setString(2, miner.getAddress());
				stmt.setInt(1, riddle.getRiddleId());
				stmt.setDate(3, new Date(new java.util.Date().getTime()));
			

				stmt.executeUpdate();
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
		
		
	}


	public static ArrayList<Riddle> getAllRiddle() {
		ArrayList<Riddle> results = new ArrayList<>();

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn
						.prepareStatement("SELECT * FROM tblRiddle");
				ResultSet rs = stmt.executeQuery();

				while (rs.next())
					results.add(new Riddle(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getDate(4),
							RiddleStatus.valueOf(rs.getString(5)), rs.getString(6)));

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}


	public static void removeRiddle(Riddle rid) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("DELETE FROM tblRiddle WHERE riddleId = ?");

				stmt.setInt(1, rid.getRiddleId());

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
				PreparedStatement stmt = conn
						.prepareStatement("SELECT * FROM tblDifficulty");
				ResultSet rs = stmt.executeQuery();

				while (rs.next())
					results.add(new RiddleLevel(rs.getString(1), rs.getInt(2), rs.getInt(3)));

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}


	public static Integer getLastRiddle() {
	int amount = 0;
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("SELECT TOP 1 tblRiddle FROM tblRaffle ORDER BY riddleId DESC");
			
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


	public static void addRiddle(Riddle rid) {
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO tblRiddle VALUES (?,?,?,?,?,?)");
				
				stmt.setInt(1, rid.getRiddleId());
				stmt.setString(3, rid.getDescription());
				stmt.setString(5, rid.getStatus().toString());
				stmt.setString(6, rid.getLevel());
				stmt.setDate(2,new Date(rid.getPublishDate().getTime()) );
				stmt.setDate(4, new Date(rid.getSolutionFinishTime().getTime()));


				stmt.executeUpdate();
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}		
	}



}
