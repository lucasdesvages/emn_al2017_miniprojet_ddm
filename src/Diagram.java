import java.util.ArrayList;

public class Diagram implements IDiagram {
	
	int x; //Relative x
	int y; //Relative y
	ArrayList<Class<?>> classes;
	
	//Composite
	ArrayList<Diagram> diagrams;

	public Diagram() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Class<?>> getClasses() {
		return classes;
	}

	public ArrayList<Diagram> getDiagrams() {
		return diagrams;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public <T> void add(Class<T> c, int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void describe(Class<T> c) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void insert(IDiagram d, int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean IsEmptyDiagram() {
		// TODO Auto-generated method stub
		return false;
	}

}
