
import processing.core.PApplet;

public class MainGame extends PApplet {
	
	Bird bird;
	Block[] blocks;
	boolean gameOver;
	
	public static void main(String[] args) {
		PApplet.main("MainGame");
	}
	
	public void setup() {
		gameOver = false;
		blocks = new Block[3];
		bird = new Bird(20, height, 35, this);
		blocks[0] = new Block(300, 100, 120, this);
		blocks[1] = new Block(550, 100, 120, this);
		blocks[2] = new Block(800, 100, 120, this);
	}
	
	public void settings() {
		size(600, 700);
	}
	
	public void draw() {
		background(0);
		
		if (!gameOver) {
			for (Block block : blocks) {
				block.draw();
				block.slide();
				gameOver = intersect(bird, block);
				if (gameOver)
					break;
			}
			
			bird.draw();
			bird.fly();
		}
	}
	
	public boolean intersect(Bird bird, Block block) {
		int x = bird.getX() + (bird.getR() / 2);
		int y = bird.getY() + (bird.getR() / 2);
		if (x > block.getX() && x < block.getX() + 50) {
			if(y > block.getY() && y < block.getY() + block.getH())
				return false;
			else
				return true;
		}
		return false;
	}
	
	public void keyPressed() {
		if (key == 'W' || key == 'w') {
			bird.up();
		}
	}

}
