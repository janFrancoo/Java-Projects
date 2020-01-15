import processing.core.PApplet;

public class SEllipse extends PApplet {

	public static void main(String[] args) {
		PApplet.main("SEllipse");
	}
	
	public void settings() {
		size(600, 600);
	}
	
	public void draw() {
		background(51);
		translate(width/2, height/2);
		
		float a = 100, b = 100, n = 4;
		stroke(255);
		noFill();
		
		beginShape();
		for(float angle=0; angle<TWO_PI; angle+=0.1) {
			float na = 2/n;
			float x = pow(abs(cos(angle)), na) * a * sgn(cos(angle));
			float y = pow(abs(sin(angle)), na) * b * sgn(sin(angle));
			vertex(x, y);
		}
		endShape(CLOSE);
	}
	
	public float sgn(float val) {
		if (val > 0)
			return 1;
		else if (val < 0)
			return -1;
		else
			return 0;
	}

}
