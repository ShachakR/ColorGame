import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ColorLine {
	public ArrayList<Object> objectList;
	
	private final Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.WHITE, Color.ORANGE}; 
	private final int SIZE = 7;
	
	private final int X = 20;  
	private final int Y = 0;
	private Random rng = new Random();
	
	private int speed = 5; 
	
	public ColorLine(int speed) {
		objectList = new ArrayList<Object>();
		this.speed = speed; 
		makeColorLine();
	}
	
	private void makeColorLine() {
		Color newColor = null;
		
		ArrayList<Color> colorList = getNewColorList();
		
		for(int i = 0; i < SIZE; i++) {
			
			int x = X + (82 * i);
			newColor = getRandomColor(colorList); 
			
			Object temp = new Object(x, Y, newColor);
			
			temp.setVelY(speed);
			objectList.add(temp);
		}
	}
	
	private Color getRandomColor(ArrayList<Color> colorList) {
		int index = rng.nextInt(colorList.size()); 	 //RANGE[0 to 6] 
		
		Color c = colorList.get(index); // saves color from list
		colorList.remove(index); //removes color from list, so there will be no duplicates in the color line 
		
		return c; 
	}
	
	private ArrayList<Color> getNewColorList(){
		ArrayList<Color> colorList = new ArrayList<Color>();
		
		for(int i = 0; i < colors.length; i++) {
			colorList.add(colors[i]); 
		}
		
		return colorList; 
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < SIZE; i++) {
			objectList.get(i).render(g);
		}
	}
	
	public void tick() {	
		for(int i = 0; i < SIZE; i++) {
			objectList.get(i).tick();
		}
	}
	
	public int getY() {
		return objectList.get(0).getPosY();
	}
	
	public ArrayList<Object> getObjectList() {
		return objectList;
	}
}
