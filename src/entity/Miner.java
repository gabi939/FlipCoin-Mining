package entity;

public class Miner {
	
	private String address;
	private String name;
	private String password; 
	private String email;
	private double digitalProfit;
	
	
	
	public Miner(String address) {
		super();
		this.address = address;
	}

	public Miner(String address,String name, String password, String email , double digitalProfit) {
		super();
		this.address = address;
		this.name = name;
		this.password = password;
		this.email = email;
		this.digitalProfit = digitalProfit;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	

	public double getDigitalProfit() {
		return digitalProfit;
	}

	public void setDigitalProfit(double digitalProfit) {
		this.digitalProfit = digitalProfit;
	} 
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
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
		Miner other = (Miner) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		return true;
	}
	

}
