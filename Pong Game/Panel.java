import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;

public class Panel extends JPanel implements KeyListener, ActionListener{

	private Timer timer = new Timer(5, this);
	private static final long serialVersionUID = 1L;
	private Ball ball = new Ball(390, 300, 20, 2, 5, Color.blue);
	private Rod userRod = new Rod(20, 300, 10, 100, 10, Color.white);
	private Rod enemyRod = new Rod(750, 300, 5, 100, 6, Color.white);
	
	public Panel() {
		
		timer.start();
		
		setBackground(Color.BLACK);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		requestFocus();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(ball.getColor());
		g.fillOval(ball.getX(), ball.getY(), ball.getSize(), ball.getSize());
		g.setColor(userRod.getColor());
		g.fillRect(userRod.getX(), userRod.getY(), userRod.getWidth(), userRod.getHeight());
		g.setColor(enemyRod.getColor());
		g.fillRect(enemyRod.getX(), enemyRod.getY(), enemyRod.getWidth(), enemyRod.getHeight());
	    
	    if(control()) {
	    	timer.stop();
	    	System.exit(0);
	    }
	}
	
	public boolean control() {
		
		if(ball.getX() < 0 || ball.getX() > 750) {
			return true;
		}
		return false;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		ball.setX(ball.getX() + ball.getDirX());
		ball.setY(ball.getY() + ball.getDirY());
		enemyRod.setY(ball.getY());
		
		if(ball.getY() < 0 || ball.getY() > 600) {
			ball.setDirY(ball.getDirY() * -1);
			enemyRod.setDir(enemyRod.getDir() * -1);
		}
		if(new Rectangle(userRod.getX(), userRod.getY(), userRod.getWidth(), userRod.getHeight())
				.intersects(new Rectangle(ball.getX(), ball.getY(), ball.getSize(), ball.getSize()))) {
			ball.setDirX(ball.getDirX() * -1);
		}
		if(new Rectangle(enemyRod.getX(), enemyRod.getY(), enemyRod.getWidth(), enemyRod.getHeight())
				.intersects(new Rectangle(ball.getX(), ball.getY(), ball.getSize(), ball.getSize()))){
			ball.setDirX(ball.getDirX() * -1);
		}
		
		repaint();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		int userDir = userRod.getDir();
		int userY = userRod.getY();
		
		if (code == KeyEvent.VK_UP) {
			if(userY > 0) {
				userRod.setY(userY - userDir);
			}
		}
		
		if (code == KeyEvent.VK_DOWN) {
			if(userY < 520) {
				userRod.setY(userY + userDir);
			}
		}
		
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
