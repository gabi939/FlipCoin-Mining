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

	
	/**
	 * gets all executed transaction from DB 
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
	public static ArrayList<Transaction> getBestTrans() {
		ArrayList<Transaction> temp = getAllNotExecutedTransactions();

		System.err.println(temp);
		Collections.sort(temp, new Comparator<Transaction>() {

			@Override
			public int compare(Transaction a, Transaction b) {

				if (a.getCommission() > b.getCommission())
					return 1;
				else
					return -1;
			}
		});

		ArrayList<Transaction> temp2 = new ArrayList<>();
		for (int i = 0; i < temp.size(); i++)
			if (i < temp.size() && temp.get(i) != null)
				temp2.add(temp.get(i));

		return temp2;

	}

	/**
	 * adds a transaction to a specific block
	 * @param block
	 * @param a
	 * @return
	 */
	public static boolean addToBlock(Block block, Transaction a) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_UPD_TRANSACTION)) {

				a.wasAddedToBlock();

				stmt.setString(1, block.getBlockAddress());
				stmt.setDate(2, a.getAdditionTime());
				stmt.setString(3, a.getID());
				
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

	/**
	 * gets all executed transactions from the DB 
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
	 * adds all  received from flipCoin Transfer to DB  
	 * @return
	 */
	public static void insertNewTransactions(ArrayList<Transaction> transactions) {
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

}
