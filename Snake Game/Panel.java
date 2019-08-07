import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Panel extends JPanel implements KeyListener, ActionListener{
	
	private Timer timer = new Timer(5, this);
	private static final long serialVersionUID = 1L;
	private ArrayList<SnakeBody> snake = new ArrayList<>();
	private ArrayList<Bend> bends = new ArrayList<>();
	private int RIGHT = 0, LEFT = 1, DOWN = 2, UP = 3;
	private int x = 150, y = 200, bodySize = 20, bodyLength = 3, speed = 1;
	private Food food;
	private Random random = new Random();
	private BufferedImage foodImg;
	
	public Panel() {
		
		timer.start();
		
		setBackground(Color.BLACK);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		requestFocus();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		try {
			foodImg = ImageIO.read(new FileImageInputStream(new File("C:\\Users\\ErenS\\Desktop\\food.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		snake.add(new SnakeBody(x, y, RIGHT));
		snake.add(new SnakeBody(x - bodySize - 5, y, RIGHT));
		snake.add(new SnakeBody(x - (2 * (bodySize + 5)), y, RIGHT));
		food = new Food(750, 60);
		
	}
	
	public boolean is_game_over() {
		
		SnakeBody head = snake.get(0);
		
		if(head.getX() < 0 || head.getY() < 0 || head.getX() > 980 || head.getY() > 530) {
			return true;
		}
		
		for(SnakeBody sb : snake) {
			if(snake.indexOf(sb) != 0) {
				for(int i=0; i<bodySize; i++) {
					if((head.getX() == sb.getX() + i && head.getY() == sb.getY() + i) ||
							(head.getX() == sb.getX() - i && head.getY() == sb.getY() - i)) {
								return true;
					}	
				}
			}
		}
		
		return false;
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.red);
		
		for(SnakeBody sb : snake) {
			g.fillOval(sb.getX(), sb.getY(), bodySize, bodySize);
		}
		
		if(is_game_over()) {
			timer.stop();
			System.exit(0);
		}
		
		g.drawImage(foodImg, food.getX(), food.getY(), foodImg.getWidth() / 10, 
				foodImg.getHeight() / 10, this);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		for(SnakeBody sb : snake) {
			
			for(Bend b : bends) {
				if(b.getEnabled() == 1) {
					if((sb.getX() == b.getX() && sb.getY() == b.getY()) || 
							(sb.getX() == (b.getX() + 1) && sb.getY() == (b.getY() + 1)) ||
							(sb.getX() == (b.getX() - 1) && sb.getY() == (b.getY() - 1)) ||
							(sb.getX() == (b.getX() + 2) && sb.getY() == (b.getY() + 2)) ||
							(sb.getX() == (b.getX() - 2) && sb.getY() == (b.getY() - 2)) ||
							(sb.getX() == (b.getX() + 3) && sb.getY() == (b.getY() + 3)) ||
							(sb.getX() == (b.getX() - 3) && sb.getY() == (b.getY() - 3))) {
						
						sb.setDir(b.getDir());
						if(snake.indexOf(sb) == bodyLength - 1) {
							b.setEnabled(0);
						}
						
					}
				}
			}
			
			if(sb.getDir() == RIGHT) {
				sb.setX(sb.getX() + speed);
			}
			if(sb.getDir() == LEFT) {
				sb.setX(sb.getX() - speed);
			}
			if(sb.getDir() == UP) {
				sb.setY(sb.getY() - speed);
			}
			if(sb.getDir() == DOWN) {
				sb.setY(sb.getY() + speed);
			}
		}
		
		SnakeBody head = snake.get(0);
				
		if(new Rectangle(head.getX(), head.getY(), bodySize, bodySize).
				intersects(new Rectangle(food.getX(), food.getY(), bodySize, bodySize))) {
			if(snake.get(bodyLength - 1).getDir() == RIGHT) {
				snake.add(new SnakeBody(snake.get(bodyLength - 1).getX() - 7 - bodySize, 
						snake.get(bodyLength - 1).getY(), snake.get(bodyLength - 1).getDir()));
			}
			if(snake.get(bodyLength - 1).getDir() == LEFT) {
				snake.add(new SnakeBody(snake.get(bodyLength - 1).getX() + 7 + bodySize, 
						snake.get(bodyLength - 1).getY(), snake.get(bodyLength - 1).getDir()));
			}
			if(snake.get(bodyLength - 1).getDir() == DOWN) {
				snake.add(new SnakeBody(snake.get(bodyLength - 1).getX(), snake.get(bodyLength - 1).getY() 
						- 7 - bodySize, snake.get(bodyLength - 1).getDir()));
			}
			if(snake.get(bodyLength - 1).getDir() == UP) {
				snake.add(new SnakeBody(snake.get(bodyLength - 1).getX(), snake.get(bodyLength - 1).getY() 
						+ 7 + bodySize, snake.get(bodyLength - 1).getDir()));
				
				bodyLength++;
				food.setX(random.nextInt(951));
				food.setY(random.nextInt(501));
			} else {
				timer.stop();
				System.exit(0);
			}
		}
		
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		int headX = snake.get(0).getX();
		int headY = snake.get(0).getY();
		int dir = snake.get(0).getDir();
		
		if (code == KeyEvent.VK_RIGHT) {
			if(dir != RIGHT && dir != LEFT) {
				bends.add(new Bend(headX, headY, RIGHT, 1));
			}
		}
		if (code == KeyEvent.VK_LEFT) {
			if(dir != RIGHT && dir != LEFT) {
				bends.add(new Bend(headX, headY, LEFT, 1));
			}
		}
		if (code == KeyEvent.VK_UP) {
			if(dir != UP && dir != DOWN) {
				bends.add(new Bend(headX, headY, UP, 1));
			}
		}
		if (code == KeyEvent.VK_DOWN) {
			if(dir != UP && dir != DOWN) {
				bends.add(new Bend(headX, headY, DOWN, 1));
			}
		}
		if (code == KeyEvent.VK_ESCAPE) {
			timer.stop();
			System.exit(0);
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
