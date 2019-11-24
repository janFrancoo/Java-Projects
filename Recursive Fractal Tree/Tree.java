import processing.core.PApplet;

public class FractalTree extends PApplet {
			
	public static void main(String[] args) {
		PApplet.main("FractalTree");
	}
	
	public void settings() {
		size(600, 600);
	}
	
	public void draw() {
		background(51);
		stroke(255);
		strokeWeight(2);
		translate(300, height);
		branch(200);
	}
	
	public void branch(float len) {
		line(0, 0, 0, -len);
		translate(0, -len);
		if(len > 4) {
			push();
			rotate(PI / 7);
			branch((float)(len * 0.67));
			pop();
			push();
			rotate(-PI / 7);
			branch((float)(len * 0.67));
			pop();
		}
	}
	
}
