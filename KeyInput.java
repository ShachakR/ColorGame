import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
	
	private static int movementSpeed = 8;
	
	public KeyInput() {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) { //start moving
		int c = e.getKeyCode();
		
		if (c == KeyEvent.VK_A) {
			Player.setVelX( movementSpeed * -1); //LEFT
		}
		if (c == KeyEvent.VK_D) {
			Player.setVelX(movementSpeed); //RIGHT
		}
	}

	@Override
	public void keyReleased(KeyEvent e) { //stop moving
		int c = e.getKeyCode();
		if (c == KeyEvent.VK_A) {
			Player.setVelX(0);
		}
		if (c == KeyEvent.VK_D) {
			Player.setVelX(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
