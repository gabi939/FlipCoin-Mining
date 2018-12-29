package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Utils.Consts;
import Utils.Type;
import entity.Transaction;

public abstract class TransactionLogic {
	
	


	public static ArrayList<Transaction> getAllNotExecutedTransactions() {
		ArrayList<Transaction> results = new ArrayList<Transaction>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.GET_ALL_NOT_CHOSEN);
					ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {
					int i = 1;
					results.add(new Transaction(rs.getString(i++),rs.getInt(i++),Type.valueOf(rs.getString(i++)),
							rs.getString(i++),rs.getInt(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
		
		
		
		
		
	}
	
	
	

}
