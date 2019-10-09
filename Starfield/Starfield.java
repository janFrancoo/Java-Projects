import processing.core.PApplet;

public class StarField extends PApplet {
	
	static float speed;
	Star[] stars = new Star[1000];

	public static void main(String[] args) {
		PApplet.main("StarField");
	}
	
	public void settings(){
		size(500, 500);
    }

    public void setup(){
    	for(int i=0; i<stars.length; i++) {
    		stars[i] = new Star(this);
    	}
    }

    public void draw(){
    	speed = map(mouseX, 0, width, 0, 30);
        background(0);
        translate(width/2, height/2);
    	for(int i=0; i<stars.length; i++) {
    		stars[i].update();
    		stars[i].show();
    	}
    }
    
}
