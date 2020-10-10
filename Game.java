import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {


	private static final long serialVersionUID = 1L;
	
	private boolean running = false;

	private Thread thread;
	
	private final int WIDTH = 600;
	private final int HEIGHT = 800;
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game() {
		Display.getInstance(WIDTH, HEIGHT, this, "Color Game");
		Player.getInstance(300, 700, Color.RED);
		Handler.getInstance(); 
	}

	public synchronized void start() { //starts in display class 
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
	}
	
	public synchronized void stop() {
		running = false; 
		
		try {
			this.thread.join(); //waits for thread to die 
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		
		stop();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy(); 
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics(); 
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		Player.render(g); //Render Player Object
		Handler.render(g); //Render ColorLines and its associated objects 
		
		g.dispose();
		bs.show();
	}


	private void tick() {
		Player.tick();
		Handler.tick();
		
		if(Handler.gameOver) {
			System.out.println("GAME OVER -- SCORE: " + Handler.score);
			running = false;
			Handler.gameOver();
			running = true; 
		}
	}

}
