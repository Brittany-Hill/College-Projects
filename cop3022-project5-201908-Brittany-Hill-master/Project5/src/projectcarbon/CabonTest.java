package projectcarbon;

import java.io.*;
import java.util.*;


public class CabonTest {
	
	private static final String NAME = "input.txt";
	public static void main(String[]args) {
		ArrayList<CarbonFootprint> CList = new ArrayList<CarbonFootprint>();
		BufferedReader hermes = null;
		FileReader hera = null;
		try {
			hera = new FileReader(NAME);
			hermes = new BufferedReader(hera);
			String line;
			hermes = new BufferedReader(new FileReader(NAME));
			while((line = hermes.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(line,",");
				ArrayList<Object> obj = new ArrayList<Object>();
				while(token.hasMoreTokens()) {
					obj.add(token.nextToken());
				}
					if (obj.get(0).equals("building")) {
						CList.add(new Building(obj.get(1).toString(), Integer.parseInt(obj.get(2).toString())));
					}
					if (obj.get(0).equals("auto")) {
						CList.add(new Auto (obj.get(1).toString(), Integer.parseInt(obj.get(2).toString()), Integer.parseInt(obj.get(3).toString())));
					}
				    if (obj.get(0).equals("food")) {
						CList.add(new Food (obj.get(1).toString(), Integer.parseInt(obj.get(2).toString())));
					}
				}
				for (int i = 0; i < CList.size(); i++) {
					if(CList.get(i) instanceof Building) {
						Building build1 = (Building) CList.get(i);
						System.out.println(build1.toString());
					}
					else if (CList.get(i) instanceof Auto) {
						Auto auto1 = (Auto) CList.get(i);
						System.out.println(auto1.toString());
					}
					else if (CList.get(i) instanceof Food) {
						Food food1 = (Food) CList.get(i);
						System.out.println(food1.toString());
					}
				}
				
			}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try { 
				if (hermes != null)
					hermes.close();
				if (hera != null)
					hera.close();
			}
			catch(IOException exit) {
				exit.printStackTrace();
			}
		}
			
		}
	}
			
