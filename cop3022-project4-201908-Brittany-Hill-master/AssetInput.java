import java.util.*;
import java.io.*;
public class AssetInput {
    private double currentInvestment;
    private double futureInvestment;
    public FileOutputStream fO;

    Assets[] validAssets = new Assets[20];
    int k = 0; 	

    public AssetInput() {
        String assets = "";
        Scanner reader = null;
		try {
			FileReader file = new FileReader("assetData.txt");
			reader = new Scanner(file);
		} catch (IOException e) {
			e.printStackTrace();
		} 
        while (reader.hasNext()) {
            assets = reader.nextLine();
            String[] parsedAssets = assets.split(",");
            System.out.println(parsedAssets.length);
            try {
            	
            if (parsedAssets.length == 3) 
                validAssets[k++] = new Assets(parsedAssets[0], parsedAssets[1], Double.parseDouble(parsedAssets[2]));
            else if (parsedAssets.length == 5) {
                if (parsedAssets[2].isEmpty())
                    parsedAssets[2] = "0";
                validAssets[k++] = new Assets(parsedAssets[0], parsedAssets[1], Double.parseDouble(parsedAssets[2]), Double.parseDouble(parsedAssets[3]), Double.parseDouble(parsedAssets[4]));
            }
            }
            catch (NumberFormatException e) {
                System.out.println("This asset cannot be added due to insufficent data.");
                System.out.println(assets);
            }
        }
        for (int x = 0; x < validAssets.length; x++)
            System.out.println(validAssets[x]);
    }

    
    public double getCurrentInvestment() {
    	return currentInvestment;
    	}
    public double getFutureInvestment() {
    	return futureInvestment;
    	}
    public void setInvestment(double investment) {
    	this.currentInvestment = investment;
    	}
 


	



}
