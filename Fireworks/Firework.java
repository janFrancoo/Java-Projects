import processing.core.PApplet;
import processing.core.PVector;

public class Firework {

	PApplet p;
	float r, g, b;
	int n, lifespan;
	Particle[] particle;
	boolean explode, dead;
	
	public Firework(PApplet parent) {
		p = parent;
		n = 200;
		explode = false;
		dead = false;
		lifespan = 255;
		particle = new Particle[n];
		r = p.random(0, 255); g = p.random(0, 255); b = p.random(0, 255);
		particle[0] = new Particle(p.random(p.width), p.height, new PVector(0, p.random(-15, -10)), p);
	}
	
	public void show() {
		if(!explode) {
			p.stroke(r, g, b);
			p.strokeWeight(4);
			particle[0].update();
			particle[0].show();
			if (particle[0].vel.y > 0) {
				for(int i=1; i<n; i++)
					particle[i] = new Particle(particle[0].pos.x, particle[0].pos.y, PVector.random2D().mult(p.random(1, 6)).mult(0.35f), p);
				explode = true;
			}
		}
		else {
			lifespan -= 3;
			p.stroke(r, g, b, lifespan);
			p.strokeWeight(3);
			for(int i=1; i<n; i++) {
				particle[i].update();
				particle[i].show();
			}
		}
		if (lifespan < 0)
			dead = true;
	}
	
}
