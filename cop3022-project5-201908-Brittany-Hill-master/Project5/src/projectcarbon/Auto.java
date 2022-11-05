package projectcarbon;

import java.text.DecimalFormat;

public class Auto implements CarbonFootprint {

	private String car;
	private int milage;
	private int distance;
	
	Auto(String car, int distance, int mpg){
		setCar(car);
		setDistance(distance);
		setMPG(mpg);
	}
	private void setCar(String c) {
		this.car = c;
	}
	private void setDistance(int d) {
		this.distance = d;
	}
	private void setMPG(int g) {
		this.milage = g;
	}
	public String getCar() {
		return car;
	}
	public int getDistance() {
		return distance;
	}
	public int getMPG() {
		return milage;
	}
	public String toString() {
		return "Driving a " + this.getCar() + " " + this.getDistance() +" miles per week creates " 
				+ this.getCarbonFootprint() + " pounds of CO2 per year";
	}
	public double getCarbonFootprint() {
		DecimalFormat form = new DecimalFormat("#.##");
		double carbon = (distance * 52) / milage * 19.4 * (100/95);
		return Double.valueOf(form.format(carbon));
	}

}

