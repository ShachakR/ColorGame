import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.JOptionPane;

public class Handler {
	private static boolean handlerCreated = false; 
	private static ArrayList<ColorLine> colorLineList; 
	private static long timer; 
	
	private static int dropSpeed = 4;  //MAX IS 8
	private static int spawnSpeed = 2000; // MIN IS 1200
	
	public static boolean gameOver = false; 
	public static int score = 0; 
	
	private Handler() {
		handlerCreated = true; 
		colorLineList = new ArrayList<ColorLine>();
		timer = System.currentTimeMillis();
	}
	
	public static Handler getInstance() {
		 if(handlerCreated) {
			 throw new NullPointerException("Hanlder Object Already Created"); 
		 }
		 
		 dropSpeed = 4;
		 spawnSpeed = 2000;
		 
		 return new Handler(); 
	}
	
	public static void render(Graphics g){
		for(int i = 0; i < colorLineList.size(); i++) {
			colorLineList.get(i).render(g);
		}
	}
	
	public static void tick() {
		long currentTimer = System.currentTimeMillis();
		
		if(currentTimer - timer > spawnSpeed) {
			createColorLine(dropSpeed);
			timer = currentTimer; 
			
			//System.out.println(spawnSpeed + "  " + dropSpeed);
			
			if(spawnSpeed > 1200) {
				spawnSpeed -= 10;
			}
		}
		
		if(colorLineList.size() >= 1 && colorLineList.get(0).getY() >= Player.getPosY()) {
			checkCollision(colorLineList.get(0)); 
		}
		
		for(int i = 0; i < colorLineList.size(); i++) {
			colorLineList.get(i).tick();
		}
		
	}
	
	private static TimerTask createColorLine(int speed) {
		ColorLine temp = new ColorLine(speed);
		colorLineList.add(temp);
		return null;
	}
	
	
	private static void checkCollision(ColorLine colorLine) {
		ArrayList<Object> temp = colorLine.getObjectList();
		boolean success = false;
		for(int i = 0; i < temp.size(); i++) {
			if(correctCollision(temp.get(i))) {
				score++; 
				
				//System.out.println("Score: " + score);
				
				if(score % 10 == 0) {
					increaseDifficulty();
				}
				success = true;
				break; 
			}
		}
		
		if(!success) {
			gameOver = true; 
			gameOver();
		}
		
		colorLineList.remove(colorLine);
	}
	
	private static boolean correctCollision(Object object) {
		Rectangle r1 = new Rectangle(Player.getPosX(), Player.getPosY(), 50, 50);
		Rectangle r2 = new Rectangle(object.getPosX(), object.getPosY(), 60, 60);
		
		if (r1.intersects(r2) && Player.getColor() == object.getColor()) {
			Player.setRandomColor();
			return true;
		}
		else {
			return false; 
		}
	}
	
	private static void increaseDifficulty() {
		if(dropSpeed < 8) {
			dropSpeed++;
		}
	}
	
	
	public static void gameOver() {
		int dialogButton = JOptionPane.showConfirmDialog (null, "GAME OVER -- SCORE: " + Handler.score + " Restart?", "GAME OVER", JOptionPane.YES_NO_OPTION);
		
		if(dialogButton == JOptionPane.YES_OPTION) {
			
			//RESET PLAYER POSITION
			Player.setPosX(300);
			
			//RESET HANDLER 
			score = 0;
			colorLineList = new ArrayList<ColorLine>();
			timer = System.currentTimeMillis();
			gameOver = false;
			
			dropSpeed = 4;
			spawnSpeed = 2000;
		}
		if(dialogButton == JOptionPane.NO_OPTION){
			System.exit(0);
		}
		
	}
}
