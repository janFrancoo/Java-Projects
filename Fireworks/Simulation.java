import java.util.ArrayList;
import processing.core.PApplet;

public class Simulation extends PApplet {
	
	ArrayList<Firework> fireworks;
	
	public static void main(String[] args) {
		PApplet.main("Simulation");
	}
	
	public void settings() {
		size(600, 600);
	}
	
	public void setup() {
		fireworks = new ArrayList<>();
		fireworks.add(new Firework(this));
	}
	
	public void draw() {
		background(21);
		if (random(1) < 0.05)
			fireworks.add(new Firework(this));
		for(Firework f : fireworks) {
			f.show();
		}
		for(int i=0; i<fireworks.size(); i++) {
			if(fireworks.get(i).dead)
				fireworks.remove(i);
		}
	}

}
