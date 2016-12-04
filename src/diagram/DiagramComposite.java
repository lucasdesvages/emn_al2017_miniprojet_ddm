package diagram;

import java.util.ArrayList;

import types.Type;

public class DiagramComposite extends AbstractDiagram implements Diagram {

	public DiagramComposite(Type t, Diagram d, int x, int y) {
		super(t, d, x, y);
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public Diagram createEmptyDiagram() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Diagram createDiagram(Type type, Diagram diagram, int x, int y) {
		return new DiagramComposite(type, diagram, x, y);
	}

	@Override
	public void add(Class<?> c, int x, int y) {
		
	}

	@Override
	public ArrayList<String[]> getDescription() {
		return getType().getDescription();
	}

}
