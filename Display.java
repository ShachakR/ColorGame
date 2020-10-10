import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Display{
	private static boolean displayCreated = false; 
	
	public static int WIDTH; 
	public static int HEIGHT; 
	private static JFrame frame;
	
	private Display(int w, int h, Game game, String title) {
		Display.WIDTH = w; 
		Display.HEIGHT = h; 
		
		game.setPreferredSize(new Dimension(w, h));
		game.setMaximumSize(new Dimension(w, h));
		game.setMinimumSize(new Dimension(w, h));
		
		game.setBackground(Color.BLACK);
		
		frame = new JFrame(title);
		frame.getContentPane().add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		KeyInput keyinput = new KeyInput();
		frame.addKeyListener(keyinput);
		game.start();
	}

	public static Display getInstance(int w, int h, Game game, String title) {
		 if(displayCreated) {
			 throw new NullPointerException("Display Object Already Created"); 
		 }
		 
		 return new Display( w, h, game, title);
	}
}
