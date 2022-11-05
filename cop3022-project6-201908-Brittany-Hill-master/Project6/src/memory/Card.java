package memory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Card {

	private static Image[] Cards;
	private static String[] CardsNames = {
			"Ada-Lovelace.jpg", 
			"ADM-Grace-Hopper.jpg", 
			"Alan-Turing.jpg", 
			"Bjarne-Stroustrup.jpg",
			"Dennis-Ritchie.jpg",
			"Donald-Knuth.jpg",
			"Edsger-Dijkstra.jpg",
			"Grady-Booch.jpg"};
	
	private static Image backOfCards;
	public static final int SIZE = 100;
	public static final int PADDING = 3;
	
	
	private Image face; //front face
	private int x, y; //top-left corner of the card
	
	private boolean reaveal;
	private boolean matched;
	
	static{
		backOfCards = new ImageIcon("back-of-card.png").getImage();
		Cards = new Image[CardsNames.length];
		for(int i=0; i<CardsNames.length; i++){
			Cards[i] = new ImageIcon(CardsNames[i]).getImage();
		}
	}
	public Card(int Index, int x, int y){
		face = Cards[Index];
		this.x = x;
		this.y = y;
	}
	
	public boolean matchWith(Card card){
		return face == card.face;
	}
	public void swapImageWith(Card card){
		//just like swapping number within an array
		Image temp = face;
		face = card.face;
		card.face = temp;
	}
	public void show()
	{
		reaveal=true;
		}
	public void hide()
	{
		reaveal=false;
		}
	public void setMatched(){
		matched=true;
		}
	public boolean hasBeenMatched()
	{
		return matched;
		}
	
	public boolean isSelected(int xAxis, int yAxis){
		return xAxis>x && xAxis<x+SIZE && yAxis>y && yAxis<y+SIZE;
	}
	
	public void draw(Graphics g){
		if (matched)
			//to not draw the image again
			return; 
		if (reaveal) 
			//assistance with this a form
			g.drawImage(face, x+10, y+10, SIZE-20, SIZE-20, null);
		else 
			g.drawImage(backOfCards, x+10, y+10, SIZE-20, SIZE-20, null);
		//setting colors 
		g.setColor(Color.gray);
		//draw here
		g.drawRect(x, y, SIZE, SIZE);
		g.setColor(Color.white);
		//to place a boarder around the cards
		g.drawRect(x+PADDING, y+PADDING, SIZE-2*PADDING, SIZE-2*PADDING);
	}
}