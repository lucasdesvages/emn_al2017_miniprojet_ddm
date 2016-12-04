package diagram;

public class EmptyDiagram extends AbstractDiagram implements Diagram {

	private static EmptyDiagram instance;

	public EmptyDiagram(DiagramState state) {
		super(state);
	}

	public EmptyDiagram() {
		super(null);
	}

	public static EmptyDiagram getInstance() {
		if (instance == null) {
			instance = new EmptyDiagram();
		}
		return instance;
	}

	@Override
	public void add(Class<?> c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void describe() {
		// TODO Auto-generated method stub

	}

	@Override
	public Diagram createDiagramWithState(DiagramState s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDiagram(Diagram d) {
		// TODO Auto-generated method stub

	}

}
