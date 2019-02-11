package entity;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import Utils.Consts;
import Utils.RiddleStatus;

/**
 * 
 * A class represents a riddle 
 * 
 * 
 * every set method sets the data in DB 
 * 
 * level details can be  via  getRIddleObject()
 * 
 * @author Gabi Malin
 *
 */
public final class Riddle {

	private Integer riddleId;
	private Date publishDate;
	private String description;
	private Date solutionFinishTime;
	private RiddleStatus status;
	private String level;
	
	
	
	
	

	public Riddle(Integer riddleId) {
		super();
		this.riddleId = riddleId;
	}

	public Riddle(Integer riddleId, Date publishDate, String description, Date solutionFinishTime, RiddleStatus status,
			String level) {
		super();
		this.riddleId = riddleId;
		this.publishDate = publishDate;
		this.description = description;
		this.solutionFinishTime = solutionFinishTime;
		this.status = status;
		this.level = level;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	/**
	 * set the publish date of the riddle
	 * @param publishDate
	 */
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				CallableStatement stmt = conn
						.prepareCall("UPDATE tblRiddle SET tblRiddle.PublishDate = ? WHERE tblRiddle.riddleId = ? ");

				stmt.setInt(2, riddleId);
				stmt.setDate(1, new java.sql.Date(publishDate.getTime()));

				stmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getDescription() {
		return description;
	}

	/**
	 * set the description 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				CallableStatement stmt = conn
						.prepareCall("UPDATE tblRiddle SET tblRiddle.Description = ? WHERE tblRiddle.riddleId = ? ");

				stmt.setInt(2, riddleId);
				stmt.setString(1, description);

				stmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Date getSolutionFinishTime() {
		return solutionFinishTime;
	}

	/**
	 * 
	 * @param solutionFinishTime
	 */
	public void setSolutionFinishTime(Date solutionFinishTime) {
		this.solutionFinishTime = solutionFinishTime;
		

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				CallableStatement stmt = conn
						.prepareCall("UPDATE tblRiddle SET tblRiddle.SolutionFinishTime = ? WHERE tblRiddle.riddleId = ? ");

				stmt.setInt(2, riddleId);
				stmt.setDate(1, new java.sql.Date(solutionFinishTime.getTime()));

				stmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public RiddleStatus getStatus() {
		return status;
	}

	/**
	 * sets the status of the riddle
	 * 
	 * @param status
	 */
	public void setStatus(RiddleStatus status) {
		this.status = status;

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				CallableStatement stmt = conn
						.prepareCall("UPDATE tblRiddle SET tblRiddle.Status = ? WHERE tblRiddle.riddleId = ? ");

				stmt.setInt(2, riddleId);
				stmt.setString(1, status.toString());

				stmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public String getLevel() {
		return level;
	}

	/**
	 * gets the object level of the current Riddle
	 * 
	 * @return
	 */
	public RiddleLevel getLevelRIddleObject() {

		RiddleLevel levelToReturn = null;

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				CallableStatement stmt = conn
						.prepareCall("SELECT * FROM tblDifficulty WHERE LevelName = ? ");

				stmt.setString(1, level);
				ResultSet rs = stmt.executeQuery();
				rs.next();
				levelToReturn = new RiddleLevel(rs.getString(1), rs.getInt(2), rs.getInt(3));

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return levelToReturn;

	}

	/**
	 * 
	 * sets the level of the riddle
	 * 
	 * @param level
	 */
	public void setLevel(String level) {
		this.level = level;

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				CallableStatement stmt = conn
						.prepareCall("UPDATE tblRiddle SET tblRiddle.LevelName = ? WHERE tblRiddle.riddleId = ? ");

				stmt.setInt(2, riddleId);
				stmt.setString(1, level);

				stmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Integer getRiddleId() {
		return riddleId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((riddleId == null) ? 0 : riddleId.hashCode());
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
		Riddle other = (Riddle) obj;
		if (riddleId == null) {
			if (other.riddleId != null)
				return false;
		} else if (!riddleId.equals(other.riddleId))
			return false;
		return true;
	}

	
	
	
}
