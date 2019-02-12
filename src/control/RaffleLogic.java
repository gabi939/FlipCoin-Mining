package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Utils.Consts;
import entity.Miner;
import entity.Raffle;

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
					results.add(new Raffle(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getInt(4),rs.getInt(5)));

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;

	}

	public static ArrayList<Raffle> getAllRafflesUserCanJoin(Miner miner){
		
		ArrayList<Raffle> raffles =getAllRaffles();
		ArrayList<Raffle> results = new ArrayList<>();
		

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("SELECT RaffleId FROM tblParticipation WHERE Address = ?");
			
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
}