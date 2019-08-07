
public class SnakeBody extends Snake{
	
	private int dir;

	public SnakeBody(int x, int y, int dir) {
		super(x, y);
		this.dir = dir;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}
	
}
