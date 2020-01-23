import processing.core.PApplet;
import processing.core.PVector;

public class PerlinFlowField extends PApplet {

	float inc = 0.1f, zoff = 0;
	int scale = 20, cols, rows, nParticle = 800;
	Particle[] particles;
	PVector[] flowfield;
	
	public static void main(String[] args) {
		PApplet.main("PerlinFlowField");
	}
	
	public void settings() {
		size(800, 600);
	}
	
	public void setup() {
		cols = width / scale;
		rows = height / scale;
		particles = new Particle[nParticle];
		for(int i=0; i<nParticle; i++)
			particles[i] = new Particle(this);
		flowfield = new PVector[rows * cols];
		background(255);
	}
	
	public void draw() {
		float yoff = 0;
		for(int y=0; y<rows; y++) {
			float xoff = 0;
			for(int x=0; x<cols; x++) {
				int index = x + y * cols;
				float angle = noise(xoff, yoff, zoff) * TWO_PI * 4;
				PVector v = PVector.fromAngle(angle);
				v.setMag(1);
				flowfield[index] = v;
				xoff += inc;
				stroke(0, 50);
			}
			yoff += inc;
			zoff += 0.03;
		}
		
		for(int i=0; i<nParticle; i++) {
			particles[i].follow(flowfield, scale, cols);
			particles[i].update();
			particles[i].edges();
			particles[i].show();
		}
	}

}
