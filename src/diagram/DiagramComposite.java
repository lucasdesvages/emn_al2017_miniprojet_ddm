package diagram;

import java.util.ArrayList;

import fabriques.TypeBuilder;
import types.Type;

public class DiagramComposite extends AbstractDiagram implements Diagram {

	public DiagramComposite(Class<?> c, Diagram d, int x, int y) {
		super(c, d, x, y);
	}

	public DiagramComposite() {
		super();
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public Diagram createEmptyDiagram() {
		return EmptyDiagram.getInstance();
	}

	@Override
	public Diagram createDiagram(Class<?> c, Diagram diagram, int x, int y) {
		return new DiagramComposite(c, diagram, x, y);
	}

	@Override
	public void add(Class<?> c, int x, int y) {
		Diagram lastDiagram = getLastDiagram();
		lastDiagram.setDiagram(new DiagramComposite(c, EmptyDiagram
				.getInstance(), x, y));
	}

	@Override
	public void insert(Diagram d, int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<String[]> getDescription() {
		return getType().getDescription();
	}

}
