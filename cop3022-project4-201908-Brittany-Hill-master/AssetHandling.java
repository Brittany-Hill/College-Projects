
public abstract class AssetHandling {

	public String assetName;
	public String assetID;
	public double fiveYears;
	public double oneYear;
	public double ninetyDays;
	
	public AssetHandling() {
		assetID = "";
		assetName = "";
		fiveYears = 0;
		oneYear = 0;
		ninetyDays = 0;
	}
	public AssetHandling(String ID, String AN) {
		setID(ID);
		setName(AN);
	}
	
	public void setID(String ID) {
		this.assetID = ID;
	}
	public void setName(String AN) {
		this.assetName = AN;
	}
	public String getID() {
		return assetID;
	}
	public String getName() {
		return assetName;
	}
	public String toString() {
		return  "-------------+---------------+-------------------\n"+
				"|Asset Symbol|Amount Invested|Value in Ten Years|\n"+
				"-------------+---------------+-------------------\n";
	}
}
