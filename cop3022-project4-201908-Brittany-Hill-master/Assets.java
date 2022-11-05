import java.text.DecimalFormat;

public class Assets extends AssetHandling{
	private double fiveYears;
	private double oneYear;
	private double ninetyDays;
	private double expectedReturn;
	public Assets() {
		fiveYears = 0;
		oneYear=0;
		ninetyDays = 0;
		expectedReturn =0;
	}
	public Assets(String ID, String AN, double FY, double OY, double ND) {
		super(ID,AN);
		setFiveYear(FY);
		setOneYear(OY);
		setNinetyDays(ND);
	}
	public Assets(String ID, String AN, double OY, double ND) {
		super(ID,AN);
		setOneYear(OY);
		setNinetyDays(ND);
	}
	public Assets(String ID, String AN, double ER) {
		super(ID,AN);
		expectedReturn = ER;
	}
	
	public void setFiveYear(double FY) {
		this.fiveYears = FY;
	}
	public void setOneYear (double OY) {
		this.oneYear = OY;
	}
	public void setNinetyDays(double ND) {
		this.ninetyDays = ND;
	}
	public double getExpectedReturn() {
		return expectedReturn;
	}
	public double getFiveYear() {
		return fiveYears;
	}
	public double getOneYear() {
		return oneYear;
	}
	public double ninetyDays() {
		return ninetyDays;
	
    }
	public double yeild() {
		double formattedYeild;
		DecimalFormat format = new DecimalFormat("#.##");
		formattedYeild = Math.pow((expectedReturn + 1), 10 * 100);
		Double.valueOf(format.format(formattedYeild));
		return formattedYeild;
	}
	public void expectedReturn() {
		if (fiveYears > 0)
			expectedReturn = (0.6* fiveYears + 0.2 * oneYear + 0.2 * ninetyDays);
		else
			expectedReturn = (0.6 * oneYear + 0.4 * ninetyDays);
	}
    
	@Override
	public String toString() {
		return super.toString() + 
				"-------------+---------------+-------------------\"+\n" + 
				"|%15s|%15f|%20f| \n" + 
				"------------+---------------+-------------------\n";
	}
}
	
	
