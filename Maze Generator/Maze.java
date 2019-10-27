import java.util.Stack;

import processing.core.PApplet;

public class Maze extends PApplet{
	
	int cols, rows, w = 40;
	Cell current, next;
	Cell[] cells;
	Stack<Cell> stack = new Stack<>();

	public static void main(String[] args) {
		PApplet.main("Maze");
	}
	
	public void setup() {
		int n = 0;
		cols = width / 40;
		rows = height / 40;
		cells = new Cell[cols * rows];
		for(int i=0; i<cols; i++) {
			for(int j=0; j<rows; j++) {
				cells[n] = new Cell(this, i, j);
				n++;
			}
		}
		current = cells[0];
	}
	
	public void settings() {
		size(600, 600);
	}
	
	public void draw() {
		background(51);
		for(int i=0; i<rows*cols; i++) {
			cells[i].show();
		}
		
		current.visited = true;
		Cell next = next(current.i, current.j);
		if(next != null) {
			next.visited = true;
			stack.push(current);
			removeWalls(current, next);
			current = next;
		}
		else if(!stack.isEmpty()) {
			current = stack.pop();
		}	
	}
	
	public int index(int i, int j) {
		if(i < 0 || j < 0 || i > cols - 1 || j > rows - 1) {
			return -1;
		}
		return j + (i * cols);
	}
	
	public Cell next(int i, int j) {
		int n = 0;
		Cell[] neighbours  = new Cell[4];
		Cell top = null, right = null, bottom = null, left = null;
		
		if(index(i, j-1) != -1) {
			top = cells[index(i, j-1)];
		}
		if(index(i+1, j) != -1) {
			right = cells[index(i+1, j)];
		}
		if(index(i, j+1) != -1) {
			bottom = cells[index(i, j+1)];
		}
		if(index(i-1, j) != -1) {
			left = cells[index(i-1, j)];
		}
		
		if(top != null && !top.visited) {
			neighbours[n] = top;
			n++;
		}
		if(right != null && !right.visited) {
			neighbours[n] = right;
			n++;
		}
		if(bottom != null && !bottom.visited) {
			neighbours[n] = bottom;
			n++;
		}
		if(left != null && !left.visited) {
			neighbours[n] = left;
			n++;
		}
		
		if(n > 0) {
			int r = floor(random(0, n));
			return neighbours[r];
		}
		else {
			return null;
		}
	}
	
	public void removeWalls(Cell current, Cell next) {
		int dX = current.i - next.i, dY = current.j - next.j;
		if(dX == -1) {
			current.walls[1] = false;
			next.walls[3] = false;
		}
		if(dX == 1) {
			current.walls[3] = false;
			next.walls[1] = false;
		}
		if(dY == -1) {
			current.walls[2] = false;
			next.walls[0] = false;
		}
		if(dY == 1) {
			current.walls[0] = false;
			next.walls[2] = false;
		}
	}
	
}
