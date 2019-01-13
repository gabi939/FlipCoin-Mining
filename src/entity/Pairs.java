package entity;

public class Pairs {
	private String trans1;
	private String trans2;
	private double size;
	private double comission;
	
	
	
	public Pairs(String trans1, String trans2, double comission , double size) {
		super();
		this.trans1 = trans1;
		this.trans2 = trans2;
		this.size = size;
		this.comission = comission;
	}
	
	
	public String getTrans1() {
		return trans1;
	}
	public void setTrans1(String trans1) {
		this.trans1 = trans1;
	}
	public String getTrans2() {
		return trans2;
	}
	public void setTrans2(String trans2) {
		this.trans2 = trans2;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public double getComission() {
		return comission;
	}
	public void setComission(double comission) {
		this.comission = comission;
	}

	

}
