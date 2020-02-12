import processing.core.PApplet;

public class Metaballs extends PApplet {
	
	int n = 10;
	Blob[] blobs;

	public static void main(String[] args) {
		PApplet.main("Metaballs");
	}
	
	public void settings() {
		size(600, 300);
	}
	
	public void setup() {
		blobs = new Blob[n];
		for (int i = 0; i < n; i++) {
			blobs[i] = new Blob(random(width), random(height), this);
		}
		colorMode(HSB);
	}
	
	public void draw() {
		background(51);
		
		loadPixels();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				float sum = 0;
				for (Blob b : blobs)
					sum += 50 * b.r / dist(x, y, b.pos.x, b.pos.y);
				pixels[x + y * width] = color(sum % 255, 255, 255);
			}
		}
		updatePixels();
		
		for (Blob b : blobs)
			b.update();
	}

}
