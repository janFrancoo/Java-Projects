import peasy.PeasyCam;
import processing.core.PApplet;
import processing.core.PVector;

public class Supershape extends PApplet {
	
	int total = 75;
	float r = 175, a = 1, b = 1;
	PeasyCam cam;
	PVector[][] globe;

	public static void main(String[] args) {
		PApplet.main("Supershape");
	}
	
	public void settings() {
		size(900, 900, P3D);
	}
	
	public void setup() {
		cam = new PeasyCam(this, 400);
		globe = new PVector[total+1][total+1];
		
		for(int i=0; i<total+1; i++) {
			float lat = map(i, 0, total, -HALF_PI, HALF_PI);
			float r1 = supershape(lat, 7, 0.2f, 1.7f, 1.7f);
			for(int j=0; j<total+1; j++) {
				float lon = map(j, 0, total, -PI, PI);
				float r2 = supershape(lon, 7, 0.2f, 1.7f, 1.7f);
				float x = r * r1 * cos(lon) * r2 * cos(lat);
				float y = r * r1 * sin(lon) * r2 * cos(lat);
				float z = r * r2 * sin(lat);
				globe[i][j] = new PVector(x, y, z);
			}
		}
	}
	
	public void draw() {
		background(0);
		lights();

		for(int i=0; i<total; i++) {
			float c = map(i, 0, total, 0, 255);
			fill(c, 255, 255);
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
	
	public float supershape(float theta, float m, float n1, float n2, float n3) {
		float t1 = pow(abs((1 / a) * cos(m * theta / 4)), n2);
		float t2 = pow(abs((1 / b) * sin(m * theta / 4)), n3);
		float r = pow(t1 + t2, -1 / n1);
		return r;
	}

}
