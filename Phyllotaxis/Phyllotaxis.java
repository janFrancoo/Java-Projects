import processing.core.PApplet;

public class Phyllotaxis extends PApplet{

	private int i = 0, colorLimit = 300, j = -50;
	private float c = 4, specialAngle = 134.5f, alpha = 255, red, green, blue;
	
	public static void main(String[] args) {
		PApplet.main("Phyllotaxis");
	}
	
	public void setup() {
		background(0);
		red = random(55, 255);
		green = random(55, 255);
		blue = random(55, 255);
	}
	
	public void settings() {
		size(800, 500);
	}
	
	public void draw() {
		translate(width / 2, height / 2);
		float theta = calcTheta(i);
		float r = calcRadius(i);
		float x = cos(theta) * r;
		float y = sin(theta) * r;
		System.out.println(x + " " + y + " " + alpha);
		fill(red, green, blue, alpha);
		circle(x, y, 10);
		i = (i + 1);
		alpha = (float) ((alpha - 0.498) % 255);
		if (alpha < 0) alpha = 255;
		j++;
		green += 1 % 255;
		blue += 1 % 255;
		if (j == colorLimit) {
			j = 0;
			red = random(55, 255);
			green = random(55, 255);
			blue = random(55, 255);
		}
	}
	
	public float calcRadius(int i) {
		return (float) (c * Math.sqrt(i));
	}
	
	public float calcTheta(int i) {
		return (float) i * specialAngle;
	}

}
