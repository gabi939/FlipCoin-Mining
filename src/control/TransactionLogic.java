package control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Utils.Consts;
import Utils.Type;
import entity.Block;
import entity.Miner;
import entity.Pairs;
import entity.Transaction;

public abstract class TransactionLogic {

	/**
	 * gets all executed transaction from DB
	 * 
	 * @return
	 */
	public static ArrayList<Transaction> getAllexecuted() {
		ArrayList<Transaction> results = new ArrayList<Transaction>();

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_EXECUTED_TRANSACTIONS)) {

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {

					results.add(new Transaction(rs.getString(1), rs.getInt(2), Type.valueOf(rs.getString(3)),
							rs.getInt(4), rs.getString(5)));
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

	/**
	 * gets miner blocks
	 * 
	 * @param miner
	 * @return
	 */
	public static ArrayList<Block> getMinerBlocks(Miner miner) {
		ArrayList<Block> results = new ArrayList<Block>();

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall("{ call getMinersBlocks(?) }")) {

				stmt.setString(1, miner.getAddress());

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					int i = 1;
					results.add(new Block(rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getDate(i++),
							rs.getInt(i)));
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

	/**
	 * gets best transactions 
	 * @return
	 */
	public static ArrayList<Pairs> getBestTrans(Block block) {
		ArrayList<Pairs> results = new ArrayList<>();
	
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			
					CallableStatement stmt = conn.prepareCall(Consts.SQL_GET_PAIRS);
					stmt.setInt(1, block != null ? block.getSize() : Integer.MAX_VALUE);
					ResultSet rs = stmt.executeQuery(); 

				while (rs.next()) 
					results.add(new Pairs(rs.getString(1) , rs.getString(2),rs.getDouble(3) , rs.getDouble(4)));
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}

	public static void updateBlock(String blockId , int size) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				CallableStatement stmt = conn.prepareCall(Consts.SQL_UPDATE_BLOCK);
				
				stmt.setInt(1, size);
				stmt.setString(2, blockId);
				stmt.executeUpdate();
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		
		
		
		
	}
	
	
	

	/**
	 * adds a transaction to a specific block
	 * 
	 * @param block
	 * @param a
	 * @return
	 */
	public static boolean addToBlock(Block block, Transaction a) {
		update(block, a);
		update1(block, a);
		addProfit(a, block);
		return true;

	}

	/**
	 * gets all executed transactions from the DB
	 * 
	 * @return
	 */
	public static ArrayList<Transaction> getAllNotExecutedTransactions() {
		ArrayList<Transaction> results = new ArrayList<Transaction>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.GET_ALL_NOT_CHOSEN);
					ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {
					int i = 1;
					results.add(new Transaction(rs.getString(i++), rs.getInt(i++), Type.valueOf(rs.getString(i++)),
							rs.getInt(i++), rs.getString(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * adds all received from flipCoin Transfer to DB
	 * 
	 * @return
	 */
	public static void insertNewTransactions(ArrayList<Transaction> transactions) {
		newTransaction(transactions);
		newTransaction1(transactions);

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// ================================== help methods ====================================================================================
	private static void newTransaction1(ArrayList<Transaction> transactions) {// used for  insertNewTransactions

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				CallableStatement stmt = conn.prepareCall(Consts.SQL_INSET_NEW_TRANSACTIONS1);

				for (Transaction temp : transactions) {

					stmt.setString(1, temp.getID());
					stmt.setDouble(2, temp.getSize());
					stmt.setString(3, temp.getType().toString());
					stmt.setDouble(4, temp.getCommission());
					stmt.setString(5, temp.getBlockAddress());

					stmt.executeUpdate();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static void newTransaction(ArrayList<Transaction> transactions) {// used for  insertNewTransactions

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				CallableStatement stmt = conn.prepareCall(Consts.SQL_INSET_NEW_TRANSACTIONS);

				for (Transaction temp : transactions) {

					stmt.setString(1, temp.getID());
					stmt.setDouble(2, temp.getSize());
					stmt.setString(3, temp.getType().toString());
					stmt.setDouble(4, temp.getCommission());
					stmt.setString(5, temp.getBlockAddress());

					stmt.executeUpdate();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static void update(Block block, Transaction a) { // used for addToBlock
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_UPD_TRANSACTION)) {

				a.wasAddedToBlock();

				stmt.setString(1, block.getBlockAddress());
				stmt.setDate(2, a.getAdditionTime());
				stmt.setString(3, a.getID());

				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static void update1(Block block, Transaction a) {// used for addToBlock
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_UPD_TRANSACTION1)) {

				a.wasAddedToBlock();

				stmt.setString(1, block.getBlockAddress());
				stmt.setDate(2, a.getAdditionTime());
				stmt.setString(3, a.getID());

				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	private static void addProfit(Transaction a , Block b) {// used for addToBlock
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.UPDATE_PROFIT)) {

				a.wasAddedToBlock();

				stmt.setDouble(1, a.getCommission());
				stmt.setString(2, b.getOwner());

				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
}
