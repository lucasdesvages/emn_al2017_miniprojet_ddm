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
	public Diagram createEmptyDiagram() {
		return getInstance();
	}

	@Override
	public Diagram createDiagram(Class<?> c, Diagram diagram) {
		return new DiagramComposite(c, diagram);
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

}
