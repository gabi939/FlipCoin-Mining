package entity;

import java.util.Date;

public class Block {
	private String blockAddress;
	private String previesblock;
	private String owner; 
	private Date DateOfCreation;
	private int size;
	
	public Block(String blockAddress) {
		this.blockAddress =blockAddress;
	}
	
	public Block(String blockAddress, String previesblock, String owner, Date dateOfCreation, int size) {
		super();
		this.blockAddress = blockAddress;
		this.previesblock = previesblock;
		this.owner = owner;
		DateOfCreation = dateOfCreation;
		this.size = size;
	}
	
	
	public void setPreviesblock(String previesblock) {
		this.previesblock = previesblock;
	}

	public String getBlockAddress() {
		return blockAddress;
	}

	public String getPreviesblock() {
		return previesblock;
	}

	public String getOwner() {
		return owner;
	}

	public Date getDateOfCreation() {
		return DateOfCreation;
	}

	public int getSize() {
		return size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blockAddress == null) ? 0 : blockAddress.hashCode());
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
		Block other = (Block) obj;
		if (blockAddress == null) {
			if (other.blockAddress != null)
				return false;
		} else if (!blockAddress.equals(other.blockAddress))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Block [blockAddress=" + blockAddress + ", previesblock=" + previesblock + ", owner=" + owner
				+ ", DateOfCreation=" + DateOfCreation + ", size=" + size + "]";
	}

	
	
	
	
		
}
