package entity;

public class Miner {
	
	private String address;
	private String name;
	private String password; 
	private String email;
	
	
	
	public Miner(String address) {
		super();
		this.address = address;
	}

	public Miner(String address,String name, String password, String email) {
		super();
		this.address = address;
		this.name = name;
		this.password = password;
		this.email = email;
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

	public void setAddress(String address) {
		this.address = address;
	} 
	
	
	

}
