import processing.core.PApplet;

class Cell {	
	public float a, b;
	public Cell(float a, float b) {
		this.a = a;
		this.b = b;
	}
}

public class ReactionDiffusion extends PApplet {
	
	Cell[][] grid, next, temp;
	double dA = 1, dB = 0.5, feed = 0.055, k = 0.062;

	public static void main(String[] args) {
		PApplet.main("ReactionDiffusion");
	}
	
	public void setup() {
		grid = new Cell[width][height];
		next = new Cell[width][height];
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				grid[i][j] = new Cell(1, 0);
				next[i][j] = new Cell(0, 0);
			}
		} 
		
		for(int i=100; i<110; i++) {
			for(int j=100; j<110; j++) {
				grid[i][j].b = 1;
			}
		}
	}
	
	public  void settings() {
		size(200, 200, P2D);
		pixelDensity(1);
	}
	
	public void draw() {
		for(int i=1; i<width-1; i++) {
			for(int j=1; j<height-1; j++) {
				float a = grid[i][j].a, b = grid[i][j].b;
				next[i][j].a = (float) (a + (dA * laplaceA(i, j)) - (a * b * b) + (feed * (1 - a)));
				next[i][j].b = (float) (b + (dB * laplaceB(i, j)) + (a * b * b) - ((k + feed) * b));
			}
		}
		
		loadPixels();
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				int pix = (x + y * width);
				float a = next[x][y].a, b = next[x][y].b;
				int c = floor((a-b)*255);
				pixels[pix] = color(c, c, c, 255);
			}
		}
		updatePixels();
		
		temp = grid;
		grid = next;
		next = temp;
	}
	
	public float laplaceA(int x, int y) {
		float sum = 0;
		sum += grid[x][y].a * -1;
		sum += grid[x-1][y].a * 0.2;
		sum += grid[x+1][y].a * 0.2;
		sum += grid[x][y+1].a * 0.2;
		sum += grid[x][y-1].a * 0.2;
		sum += grid[x-1][y-1].a * 0.05;
		sum += grid[x+1][y-1].a * 0.05;
		sum += grid[x+1][y+1].a * 0.05;
		sum += grid[x-1][y+1].a * 0.05;
		return sum;
	}
	
	public float laplaceB(int x, int y) {
		float sum = 0;
		sum += grid[x][y].b * -1;
		sum += grid[x-1][y].b * 0.2;
		sum += grid[x+1][y].b * 0.2;
		sum += grid[x][y+1].b * 0.2;
		sum += grid[x][y-1].b * 0.2;
		sum += grid[x-1][y-1].b * 0.05;
		sum += grid[x+1][y-1].b * 0.05;
		sum += grid[x+1][y+1].b * 0.05;
		sum += grid[x-1][y+1].b * 0.05;
		return sum;
	}

}
