import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Panel extends JPanel implements KeyListener, ActionListener{
	
	private Timer timer = new Timer(5, this);
	private static final long serialVersionUID = 1L;
	private int elapsedTime = 0, usedFire = 0;
	private BufferedImage image;
	private ArrayList<Fire> fires = new ArrayList<>();
	private int spaceshipLoc = 0, spaceshipDir = 20, fireDir = 1, enemyLoc = 0, enemyDir = 2, enemySize = 30, fireWidth = 15, fireHeight = 30;
	
	public Panel() {
		
		timer.start();
		
		try {
			image = ImageIO.read(new FileImageInputStream(new File("C:\\Users\\ErenS\\Desktop\\spaceship.png")));
		} catch (FileNotFoundException e) {
			System.out.println("File Error");
		} catch (IOException e) {
			System.out.println("I/O Error");
		}
		
		setBackground(Color.BLACK);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		requestFocus();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
	}
	
	public boolean control() {
		
		for(Fire fire : fires) {
			
			if(new Rectangle(fire.getX(), fire.getY(), fireWidth, fireHeight).intersects(new Rectangle(enemyLoc, 0, enemySize, enemySize))) {
				return true;
			} else {
				return false;
			}
			
		}
		
		return false;
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		elapsedTime += 5;
		g.setColor(Color.red);
		g.fillOval(enemyLoc, 0, enemySize, enemySize);
	    g.drawImage(image, spaceshipLoc, 500, image.getWidth() / 10, image.getHeight() / 10, this);
	    
	    g.setColor(Color.blue);
	    
	    for(Fire fire : fires) {
			if(fire.getY() < 0) {
				fires.remove(fire);
			}
			else {
				g.fillRect(fire.getX(), fire.getY(), fireWidth, fireHeight);
			}
		}
	    
	    if(control()) {
	    
	    	timer.stop();
	    	String gameOver = "Finished!\n"
	    			+	"Elapsed Time = " + elapsedTime / 1000.0 + "s\n"
	    			+	"Fire Used = " + usedFire + "\n";
	    	JOptionPane.showMessageDialog(this, gameOver);
	    	System.exit(0);
	    }
	    
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		for(Fire fire : fires) {
			fire.setY(fire.getY() - fireDir);
		}
		
		enemyLoc += enemyDir;
		
		if(enemyLoc >= 880 - enemySize) {
			enemyDir *= -1;
		}
		if(enemyLoc <= 0) {
			enemyDir *= -1;
		}
		
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_RIGHT) {
			if(spaceshipLoc < 880 - image.getWidth() / 10) {
				spaceshipLoc += spaceshipDir;
			}
		}
		if (code == KeyEvent.VK_LEFT) {
			if(spaceshipLoc > 0) {
				spaceshipLoc -= spaceshipDir;
			}
		}
		if (code == KeyEvent.VK_CONTROL) {
			fires.add(new Fire(spaceshipLoc + 12, 500));
			usedFire++;
		}
		
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	
	
}
