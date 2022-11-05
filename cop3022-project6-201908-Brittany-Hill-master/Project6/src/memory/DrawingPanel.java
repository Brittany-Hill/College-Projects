package memory;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class DrawingPanel extends JPanel implements MouseListener{
	//int variables and JFrames 
	private int count = 8;
	private JFrame frame;
	private Card[] cards;
	private long selectTime;
	//booleans to control the selectors and disappearing of the 
	private boolean notSelected = true;
	private boolean singleSelect = false;
	private boolean doubleSelect = false;
	//Card classes to check if they match
	private Card firstSelected;
	private Card secondSelected;

	public DrawingPanel(){
		//i got the margins through trial and error and some simple math.
		//The size of the margin(10)*2 (for both sides)
		//+ the number of columns (4)* Card.size to get the size of the panel
		//since we want the height and width to be the same we can just use 
		//size in both the Dimensions required fields
		int size = 20 + 4*Card.SIZE;
		//makes a new Jframe and sets the bounds to be a reasonable size 
		frame = new JFrame(" ");
		frame.setBounds(200, 50, 0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setPreferredSize(new Dimension(size,size));
		frame.getContentPane().add(this);
		cards = new Card[16];
		//Places the cards into an array
		for(int i=0; i<cards.length; i++){
			cards[i] = new Card(i/2, 10 + i % 4*Card.SIZE, 10 + i/4*Card.SIZE);
		}
		//randomizing the images in the array
		for(int i=0; i<cards.length; i++){
			cards[i].swapImageWith(cards[(int)(Math.random()*cards.length)]);
		}
		//packing and showing the package
		frame.pack();
		frame.setVisible(true);
		this.addMouseListener(this);
	}

	public void paintComponent(Graphics g){
		//make the cards render I had a hard time with this and found this worked
		//I had to seek assistance to get this to work.
		Graphics2D graphic = (Graphics2D) g;
		graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphic.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());

		for(int i=0; i<cards.length; i++){
			cards[i].draw(g);
		}
		
	}
	
	//sets up the logic of if the buttons are pressed
	public void mousePressed(MouseEvent e){
		for(int i=0; i<cards.length; i++){
			if(!cards[i].hasBeenMatched() && cards[i].isSelected(e.getX(), e.getY())){
				if(notSelected){
					firstSelected = cards[i];
					firstSelected.show();
					notSelected = false;
					singleSelect = true;
				}
				else if (singleSelect){
					if(cards[i]==firstSelected) return;
					secondSelected = cards[i];
					secondSelected.show();
					singleSelect = false;
					doubleSelect = true;
					selectTime = System.currentTimeMillis();
				}
				//must repaint 
				this.repaint();
				break;
			}
		}
	}
	//logic to check if the images match
	public void match(){
		if(!doubleSelect || System.currentTimeMillis()-selectTime < 200) return;
		if (firstSelected.matchWith(secondSelected)){
			firstSelected.setMatched();
			secondSelected.setMatched();
			JOptionPane.showMessageDialog(null, "Match found");
			count -= 1;
		}
		else{
			
			firstSelected.hide();
			secondSelected.hide();
		
		}
		//have to repaint
		this.repaint();
		doubleSelect = false;
		notSelected = true;
		firstSelected = null;
		secondSelected = null;
		if (count == 0)
			JOptionPane.showMessageDialog(null, "You won!");
	}

	public void hideAll(){
		for(int i=0; i<cards.length; i++) 
			cards[i].hide();
	}
	
	public static void main(String[] args) {
		DrawingPanel game = new DrawingPanel();
		game.repaint();
		try {
			Thread.sleep(200);
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		game.hideAll();
		game.repaint();
		while(true){
			game.match();
			
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {}
		}
		

	}

	//unused things
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}