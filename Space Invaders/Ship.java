import processing.core.PApplet;

public class Ship {
	
	PApplet parent;
	public int sx, sy, x, y;
	
	public Ship(PApplet p) {
		parent = p;
		sx = 15;
		sy = 50;
		x = 300;
		y = 350;
	}
	
	public void show() {
		parent.fill(9, 3, 171);
		parent.noStroke();
		parent.rect(x, y, sx, sy);
	}
	
	public void update(int direct) {
		if(direct == 0) {
			if(x > 0) {
				x -= 5;
			}
		} else {
			if(x < parent.width - sx) {
				x += 5;
			}
		}
	}

}
