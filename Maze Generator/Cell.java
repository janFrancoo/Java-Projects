import processing.core.PApplet;

public class Cell {

	int i, j, w = 40;
	boolean visited = false;
	boolean[] walls = {true, true, true, true};
	PApplet parent;

	public Cell(PApplet p, int i, int j) {
		parent = p;
		this.i = i;
		this.j = j;
	}
	
	public void show() {
		parent.stroke(0);
		int x = i * w, y = j * w;
		if(walls[0]) {
			parent.line(x, y, x + w, y);
		}
		if(walls[1]) {
			parent.line(x + w, y, x + w, y + w);
		}
		if(walls[2]) {
			parent.line(x + w, y + w, x, y + w);
		}
		if(walls[3]) {
			parent.line(x, y + w, x, y);
		}
		
		if(visited) {
			parent.fill(255, 0, 255, 100);
			parent.noStroke();
			parent.rect(x, y, w, w);
		}
	}
	
}
