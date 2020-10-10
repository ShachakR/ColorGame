import java.awt.Color;
import java.awt.Graphics;

public class Object {

	private int posX;
	private int posY; 
	
	private int velY = 0;
	
	private Color color; 
	
	private final int WIDTH = 60;
	private final int HEIGHT = 60;
	
	public Object(int posX, int posY, Color color) {
		this.posX = posX;
		this.posY = posY; 
		this.color = color; 
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(posX, posY, WIDTH, HEIGHT);
	}
	
	public void tick() {
		 posY += velY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	

}
