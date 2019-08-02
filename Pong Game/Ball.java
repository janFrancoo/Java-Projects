import java.awt.Color;

public class Ball {

	private int x, y, size, dirX, dirY;
	private Color color;
	
	public Ball(int x, int y, int size, int dirX, int dirY, Color color) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.dirX = dirX;
		this.dirY = dirY;
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getDirX() {
		return dirX;
	}
	
	public void setDirX(int dirX) {
		this.dirX = dirX;
	}
	
	public int getDirY() {
		return dirY;
	}
	
	public void setDirY(int dirY) {
		this.dirY = dirY;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
}
