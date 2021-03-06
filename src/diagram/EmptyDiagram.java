package diagram;

import java.util.ArrayList;

public class EmptyDiagram extends AbstractDiagram implements Diagram {

	private static EmptyDiagram instance;

	public static EmptyDiagram getInstance() {
		if (instance == null) {
			instance = new EmptyDiagram();
		}
		return instance;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public Diagram createDiagram(Class<?> c) {
		return new DiagramComposite(c, getInstance());
	}

	@Override
	public void add(Class<?> c) {
		insert(new DiagramComposite(c, this));
	}

	@Override
	public void insert(Diagram d) {
		d.setDiagram(this);
	}

	@Override
	public ArrayList<String[]> getDescription() {
		return null;
	}

	@Override
	public void label(String text, int x, int y) {
	}

}
