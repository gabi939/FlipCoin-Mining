package entity;
/**
 *  Class for transaction
 * @author Gabi Malin
 *
 */
public class Transaction {
	private String ID;
	private int size;
	private Type type;
	private int commission;
	
	
	
	public Transaction(String ID) {
		this.ID = ID;
		
	}
	
	public Transaction(String ID , int comission) {
		this.ID = ID;
		this.commission = comission;
	}
	
	public Transaction(String ID, int size, Type type, int commission) {
		super();
		this.ID = ID;
		this.size = size;
		this.type = type;
		this.commission = commission;
	}

	public String getID() {
		return ID;
	}

	public int getSize() {
		return size;
	}

	public Type getType() {
		return type;
	}

	public int getCommission() {
		return commission;
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
