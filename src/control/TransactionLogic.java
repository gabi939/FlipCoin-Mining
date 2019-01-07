package control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Utils.Consts;
import Utils.Type;
import entity.Block;
import entity.Miner;
import entity.Transaction;

public abstract class TransactionLogic {
	
	
	public static ArrayList<Transaction> getAllexecuted(){
		ArrayList<Transaction> results = new ArrayList<Transaction>();

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_EXECUTED_TRANSACTIONS)) {

			
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					
					results.add(new Transaction(rs.getString(1) , rs.getInt(2) , Type.valueOf(rs.getString(3)),rs.getInt(4) , rs.getString(5)));
				}
				
				return results;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
		
		
	}
	
	
	public static ArrayList<Block> getMinerBlocks(Miner miner){
		ArrayList<Block> results = new ArrayList<Block>();

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall("{ call getMinersBlocks(?) }")) {

				stmt.setString(1, miner.getAddress());
			
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					int i = 1;
					results.add(new Block(rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getDate(i++), rs.getInt(i)));
				}
				
				return results;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
		
		
	}
	
	
	public static ArrayList<Transaction> getBestTrans(){
		ArrayList<Transaction> temp = getAllNotExecutedTransactions();
	
		System.err.println(temp);
		Collections.sort(temp, new Comparator<Transaction>() {

			@Override
			public int compare(Transaction a, Transaction b) {

				if(a.getCommission() > b.getCommission())
					return 1;
				else
					return -1;
			}
		});
		
		ArrayList<Transaction> temp2 = new ArrayList<>();
		for(int i = 0 ; i < temp.size() ; i++)
				if(i < temp.size() && temp.get(i) != null)
					temp2.add(temp.get(i));
					
		
		return temp2;

	
	}
	
	
	
	
	public static boolean addToBlock(Block block , Transaction a ) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_UPD_TRANSACTION)) {
				int i = 1;
				stmt.setString(i++,block.getBlockAddress());
				stmt.setString(i, a.getAddress());
				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
		
		
		
		
		
	}


/*	public static boolean addBlock(Block block) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.ADD_BLOCK)) {
				int i = 1;

				stmt.setString(i++, block.getBlockAddress());
				stmt.setString(i++, block.getPreviesblock());
				stmt.setString(i++, block.getOwner());
				stmt.setDate(i++, new java.sql.Date(block.getDateOfCreation().getTime()));
				stmt.setInt(i,block.getSize());
				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
		
	}*/
	
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
							rs.getInt(i++),rs.getString(i++)));
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
