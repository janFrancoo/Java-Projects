import processing.core.PApplet;

public class SEllipse extends PApplet {

	float n1 = 0.3f, n2 = 0.3f, n3 = 0.3f, m = 5, a = 1, b = 1, osc = 0;
	
	public static void main(String[] args) {
		PApplet.main("SEllipse");
	}
	
	public void settings() {
		size(600, 600);
	}
	
	public void draw() {
		m = map(sin(osc), -1, 1, 0, 10);
		osc += 0.02;
		
		background(51);
		translate(width/2, height/2);
		
		stroke(255);
		noFill();
		
		beginShape();
		for(float angle=0; angle<TWO_PI; angle+=0.01) {
			float r = supershape(angle);
			float x = 100 * r * cos(angle);
			float y = 100 * r * sin(angle);
			vertex(x, y);
		}
		endShape(CLOSE);
	}
	
	public float supershape(float theta) {
		float p1 = (1 / m) * cos(theta * m / 4);
		p1 = abs(p1);
		p1 = pow(p1, n2);
		
		float p2 = (1 / b) * sin(theta * m / 4);
		p2 = abs(p2);
		p2 = pow(p2, n3);
		
		float p3 = pow(p1 + p2, (1 / n1));

		if(p3 == 0)
			return 0;
		
		return (1 / p3);
	}

}
