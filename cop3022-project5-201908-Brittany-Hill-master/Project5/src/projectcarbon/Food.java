package projectcarbon;

import java.text.DecimalFormat;

public class Food implements CarbonFootprint {

	private String typeOfFood;
	private int amount;
	private double conversion = 0.0022;
	private int factor;
	Food(String type, int a){
		setTypeOfFood(type);
		setAmount(a);
	}
	private void setTypeOfFood(String a) {
		this.typeOfFood = a;
	}
	private void setAmount(int b) {
		this.amount = b;
	}
	private int selectTypeOfFood(String a) {
		String food = a;
		if (food.equalsIgnoreCase("meat") || food.equalsIgnoreCase("fish") || food.equalsIgnoreCase("eggs")) 
			factor = 1452; 
		else if(food.equalsIgnoreCase("cereals") || food.equalsIgnoreCase("bakery") )
				factor = 741;
		else if (food.equalsIgnoreCase("dairy"))
			factor = 1911;
		else if (food.equalsIgnoreCase("fruits")||food.equalsIgnoreCase("vegetables"))
			factor = 1176;
		else if (food.equalsIgnoreCase("out"))
			factor = 368;
		else
			factor = 467;
		
		return factor;
	}
	public String getTypeOfFood() {
		return typeOfFood;
	}
	public int getAmount() {
		return amount;
	}
	@Override
	public double getCarbonFootprint() {
		// TODO Auto-generated method stub
		DecimalFormat form = new DecimalFormat("#.##");
		double carbon = (amount * this.selectTypeOfFood(typeOfFood)* 12) * conversion;
		return Double.valueOf(form.format(carbon));
	}
	public String toString() {
		return "Spending $" + this.getAmount() + " per month on " + this.getTypeOfFood().toUpperCase() + " creates " 
				+ this.getCarbonFootprint() + "pounds of CO2 per year"; 
	}

}
