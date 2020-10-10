import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player {
	
	private static boolean playerCreated = false; 
	
	private static final Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.WHITE, Color.ORANGE};
	private static Random rng = new Random();
	
	private static int posX;
	private static int posY; 
	
	private static int velX = 0;
	private static int velY = 0;
	
	private static Color color; 
	
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	

	private Player(int posX, int posY, Color color) {
		playerCreated = true; 
		Player.posX = posX;
		Player.posY = posY; 
		Player.color = color; 
	}

	 public static Player getInstance(int posX, int posY, Color color) {
		 if(playerCreated) {
			 throw new NullPointerException("Player Object Already Created"); 
		 }
		 
		 return new Player(posX, posY, color); 
	 }
	
	 public static void render(Graphics g){
		 g.setColor(color);
		 g.fillRect(posX, posY, WIDTH, HEIGHT);
	 }
	 
	 public static void tick() {
		 posX += velX;
		 posY += velY;
		 
		 //BORDER HANDLE 
		 if(posX > ( Display.WIDTH + 10)) {
			 posX = -10; 
		 }
		 if(posX < ( 0 - 10)) {
			 posX = Display.WIDTH + 10; 
		 }
	 }
	 
	 public static void setRandomColor() {
		 int index = rng.nextInt(colors.length);
		 
		 Player.setColor(colors[index]);
	 }
	 
		public static int getPosX() {
			return posX;
		}


		public static void setPosX(int posX) {
			Player.posX = posX;
		}


		public static int getPosY() {
			return posY;
		}


		public static void setPosY(int posY) {
			Player.posY = posY;
		}


		public static Color getColor() {
			return color;
		}


		public static void setColor(Color color) {
			Player.color = color;
		}
		
		public static void setVelX(int velX) {
			Player.velX = velX;
		}

		public static void setVelY(int velY) {
			Player.velY = velY;
		}


	
}
