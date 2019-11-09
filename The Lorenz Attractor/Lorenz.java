import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PVector;

public class Lorenz extends PApplet {

	double x = 0.01, y = 0, z = 0;
	double a = 10, b = 28, c = 8/3;
	ArrayList<PVector> points = new ArrayList<>(); 
	
	public static void main(String[] args) {
		PApplet.main("Lorenz");
	}
	
	public void setup() {
		
	}
	
	public void settings() {
		size(600, 600, P3D);
	}
	
	public void draw() {
		double dt = 0.01;
		double dx = (a * (y - x)) * dt;
		double dy = (x * (b - z) - y) * dt;
		double dz = ((x * y) - (c * z)) * dt;
		x += dx;
		y += dy;
		z += dz;
		points.add(new PVector((float)x, (float)y, (float)z));
		background(0);
		translate(width / 2, height / 2);
		scale(4);
		stroke(random(255), random(255), random(255));
		for(int i=1; i<points.size(); i++) {
			line(points.get(i).x, points.get(i).y, points.get(i).z, points.get(i-1).x, points.get(i-1).y, points.get(i-1).z);
		}
	}

}
