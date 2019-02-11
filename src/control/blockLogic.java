package control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Utils.Consts;
import entity.Block;
import entity.Miner;

public class blockLogic {

	
	
	
	//
	public static void addNewBlockToMiner(Miner miner , int blockSize) {
	
		Block block = new Block("block"+(blockAmount()+2), "block"+(blockAmount()+1), miner.getAddress(), new Date(), blockSize);
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO tblBlock VALUES (?, ?, ?,?,? )");
				
				stmt.setString(1, block.getBlockAddress());//BlockAddress
				stmt.setString(2, block.getPreviesblock());//PreviousBlockAddress
				stmt.setString(3, block.getOwner());//ownerAddress
				stmt.setDate(4,new java.sql.Date( block.getDateOfCreation().getTime()));//DateOfCreation
				stmt.setInt(5, block.getSize());//Size

				stmt.executeUpdate();
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
		
		
		
		
		
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
	
	
	
	// count blocks just to build the index for blocks
	private static int blockAmount() {
		int amount = 0;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try { 
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement("SELECT Count(*) FROM tblBlock ");
					ResultSet rs = stmt.executeQuery();
					rs.next();
					amount = rs.getInt(1);
					
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return amount; 
		
		
	}
	
	
	
	
	
	
}
