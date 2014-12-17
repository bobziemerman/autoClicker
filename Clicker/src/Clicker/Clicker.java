package Clicker;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.*;

public class Clicker extends JFrame{
	//need because it extends JFrame
	private static final long serialVersionUID = 8941447401110909964L;
	
	//constructor that assembles the window
	public Clicker(){
		//create interface elements
		setTitle("Clicker");
		setSize(200,75);
		JButton b = new JButton("Start");
		b.addActionListener(new ButtonAction());
		add(b);
	}
	
	//main method that instantiates the window
	public static void main(String[] args) {
		JFrame c = new Clicker();
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setVisible(true);
	}
	
	//inner class
	public class ButtonAction extends JPanel implements ActionListener{
		//need because JPanel
		private static final long serialVersionUID = 5647320647737533286L;
		
		//coordinates of the cookie
		public static final int cookieX = 2200;
		public static final int cookieY = 500;
		
		//time between autoclicks
		public static final int milsBetweenClicks = 5;
		
		//how far you can move from the center of the cookie before the clicking stops
		public static final int wiggleRoom = 30;

		public void actionPerformed(ActionEvent e) {
			//make robot to control mouse
			Robot bad;
			try {
				bad = new Robot();

				//move mouse to location of cookie
				bad.mouseMove(cookieX, cookieY);
				
				//click cookie until mouse moves
				int x = (int)MouseInfo.getPointerInfo().getLocation().getX();
				int y = (int)MouseInfo.getPointerInfo().getLocation().getY();
				
				//timer
				long time = System.currentTimeMillis();
				
				while((x < cookieX+wiggleRoom && x > cookieX-wiggleRoom) 
						&& (y < cookieY+wiggleRoom && y > cookieY -wiggleRoom)){
					//make sure a certain amount of time since the last click has passed
					if(System.currentTimeMillis() > time+milsBetweenClicks){
						//set new time
						time = System.currentTimeMillis();
						
						//click
						bad.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						bad.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
					
						//get x and y again
						x = (int)MouseInfo.getPointerInfo().getLocation().getX();
						y = (int)MouseInfo.getPointerInfo().getLocation().getY();
					}
				}
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}
	}
}
