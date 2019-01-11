package entity;


import java.sql.Date;

import Utils.Type;

/**
 *  Class for transaction
 * @author Gabi Malin
 *
 */
public class Transaction {
	private String ID;
	private double size;
	private Type type;
	private double commission;
	private String blockAddress;
	private Date additionTime; // SQL type!!! .... it mainly used for DB reasons so i did it this way
	
	
	
	public Transaction(String ID) {
		this.ID = ID;
		
	}
	
	public Transaction(String ID , int comission) {
		this.ID = ID;
		this.commission = comission;
	}
	
	

	public Transaction(String ID, double size, Type type ,double commission , String blockAddress) {
		super();
		this.ID = ID;
		this.size = size;
		this.type = type;
		this.commission = commission;
		this.blockAddress = blockAddress;
	}
	
	
	public void wasAddedToBlock() {
		additionTime = new Date( new java.util.Date().getTime());
	}
	
	public Date getAdditionTime() {
		return additionTime;
	}

	public String getID() {
		return ID;
	}

	public double getSize() {
		return size;
	}

	public Type getType() {
		return type;
	}

	public double getCommission() {
		return commission;
	}

	
	public String getBlockAddress() {
		return blockAddress;
	}

	public void setBlockAddress(String blockAddress) {
		this.blockAddress = blockAddress;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
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
		Transaction other = (Transaction) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [ID=" + ID + ", size=" + size + ", type=" + type + ", commission=" + commission + "]";
	}

	
	
	
	

}
