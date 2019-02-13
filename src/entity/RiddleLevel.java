package entity;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Exceptions.RaffleException;
import Utils.Consts;

/**
 * 
 * Class that represent a level of a riddle
 * 
 * 
 * every set method effect the DB
 * 
 * 
 * @author Gabi Malin
 *
 */
public final class RiddleLevel {

	private String name;
	private int difficulty;
	private int blockSize;

	public RiddleLevel(String name) {
		super();
		this.name = name;
	}

	public RiddleLevel(String name, int difficulty, int blockSize) {
		super();
		this.name = name;
		this.difficulty = difficulty;
		this.blockSize = blockSize;
	}

	public String getName() {
		return name;
	}

	public int getDifficulty() {
		return difficulty;
	}

	/**
	 * sets the number of the difficulty level
	 * @param difficulty
	 * @throws RaffleException 
	 */
	public void setDifficulty(int difficulty) throws RaffleException {

		if (difficulty > 0 && difficulty < 11) {
			this.difficulty = difficulty;

			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try {
					Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn
							.prepareCall("UPDATE tblDifficulty SET tblDifficulty.LevelNumber = ?\r\n"
									+ "WHERE (((tblDifficulty.LevelName)=?));\r\n" + "");

					stmt.setInt(1, difficulty);
					stmt.setString(2, name);
					stmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}else
			throw new RaffleException("Invalid Difficulty , needs to be between 1- 10");
	}

	public int getBlockSize() {
		return blockSize;
	}

	
	
	/**
	 * sets the block size to be given case riddle is solved
	 * 
	 * @param blockSize
	 * @throws RaffleException 
	 */
	public void setBlockSize(int blockSize) throws RaffleException {

		if (blockSize > 0) {
			this.blockSize = blockSize;
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try {
					Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall("UPDATE tblDifficulty SET tblDifficulty.blockSize = ?\r\n"
							+ "WHERE (((tblDifficulty.LevelName)=?));\r\n" + "");

					stmt.setInt(1, blockSize);
					stmt.setString(2, name);
					stmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}else
			throw new RaffleException("Invalid size , must be positive number");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		RiddleLevel other = (RiddleLevel) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
