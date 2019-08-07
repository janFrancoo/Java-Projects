
public class Bend {

	private int x, y, dir, enabled;
	
	public Bend(int x, int y, int dir, int enabled) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.enabled = enabled;
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

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}
	
	public int getEnabled() {
		return enabled;
	}
	
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
}
