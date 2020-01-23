import peasy.PeasyCam;
import processing.core.PApplet;
import processing.core.PVector;

public class Spherical extends PApplet {
	
	int total = 100;
	float r = 150;
	PeasyCam cam;
	PVector[][] globe;

	public static void main(String[] args) {
		PApplet.main("Spherical");
	}
	
	public void settings() {
		size(900, 800, P3D);
	}
	
	public void setup() {
		cam = new PeasyCam(this, 400);
		globe = new PVector[total+1][total+1];
	}
	
	public void draw() {
		background(0);
		fill(255);
		lights();
		
		for(int i=0; i<total+1; i++) {
			float lat = map(i, 0, total, 0, PI);
			for(int j=0; j<total+1; j++) {
				float lon = map(j, 0, total, 0, TWO_PI);
				float x = r * sin(lat) * cos(lon);
				float y = r * sin(lat) * sin(lon);
				float z = r * cos(lat);
				globe[i][j] = new PVector(x, y, z);
			}
		}
		
		stroke(255);
		strokeWeight(2);
		for(int i=0; i<total; i++) {
			beginShape(TRIANGLE_STRIP);
			for(int j=0; j<total+1; j++) {
				PVector v1 = globe[i][j];
				vertex(v1.x, v1.y, v1.z);
				PVector v2 = globe[i+1][j];
				vertex(v2.x, v2.y, v2.z);
			}
			endShape();
		}
	}

}
