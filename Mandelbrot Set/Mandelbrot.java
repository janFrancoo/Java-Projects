import processing.core.PApplet;

public class Mandelbrot extends PApplet {
	
	int width = 720, height = 480;

	public static void main(String[] args) {
		PApplet.main("Mandelbrot");
	}
	
	public void settings() {
		size(width, height, P2D);
		pixelDensity(1);
	}
	
	public void setup() {
		
	}
	
	public void draw() {
		int maxIterations = 100;
		loadPixels();
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				float a = map(x, 0, width, -2.5f, 2.5f);
				float b = map(y, 0, height, -2.5f, 2.5f);
				
				float ca = a;
				float cb = b;
				int n = 0;
				
				while(n < maxIterations) {
					float aa = a * a - b * b;
					float bb = 2 * a * b;
					a = aa + ca;
					b = bb + cb;
					if(abs(a + b) > 16) {
						break;
					}
					n++;
				}
				
				float bright = map(n, 0, maxIterations, 0, 255);
				if(n == maxIterations)
					bright = 0;
				
				int pixel = x + y * width;
				pixels[pixel] = color(bright, bright, bright, 255);
			}
		}
		updatePixels();
	}
	
}
