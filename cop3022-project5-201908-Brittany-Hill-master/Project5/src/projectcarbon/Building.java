package projectcarbon;

import java.text.DecimalFormat;

public class Building implements CarbonFootprint {

	private String owner;
	private double bill;
	private int size;
	private double emmissions = 1.37;
	private double cost = 0.25;
	
	Building(String name, int size){
		setOwner(name);
		setSize(size);
		setBill(size);
	}
	public void setSize(int s) {
		this.size = s;
	}
	public void setOwner(String n) {
		this.owner = n; 
	}
	public void setBill(int distance) {
		this.bill = (size * 123) * 0.25;
	}
	public int getSize() {
		return size;
	}
	public String getOwner(){
		return owner;
	}
	public double getBill() {
		return bill;
	}
	public String toString() {
		return "Leasing the " + this.getSize() + " sqft. Owned by " + this.getOwner() 
				+ " will create " + this.getCarbonFootprint() + " pounds of CO2 per year based on \n a factor of 123 KW per sqft." +
				"\n The monthly electric bill would be approximately $" + this.getBill();
	}
	@Override
	public double getCarbonFootprint() {
		DecimalFormat form = new DecimalFormat("#.##");
		double carbon = (this.getBill() / this.cost)* emmissions * 12;
		return Double.valueOf(form.format(carbon));
	}
}
