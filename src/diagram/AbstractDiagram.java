package diagram;

import types.Type;

public abstract class AbstractDiagram implements Diagram {

	private DiagramState state;

	public AbstractDiagram(DiagramState state) {
		this.state = state;
	}

	abstract public Diagram createDiagramWithState(DiagramState s);

	@Override
	public Diagram getDiagram() {
		return state.getDiagram();
	}

	@Override
	public void setDiagram(Diagram d) {
		state.setDiagram(d);
	}

	@Override
	public int getX() {
		return state.getX();
	}

	@Override
	public void setX(int x) {
		state.setX(x);
	}

	@Override
	public int getY() {
		return state.getY();
	}

	@Override
	public void setY(int y) {
		state.setY(y);
	}

	@Override
	public DiagramState getState() {
		return state;
	}

	@Override
	public Type getType() {
		return getState().getType();
	}

	@Override
	public boolean getToDescribe() {
		return getState().getToDescribe();
	}

	@Override
	public void setToDescribe(boolean b) {
		getState().setToDescribe(b);
	}

	@Override
	public boolean isEmpty() {
		return state.isEmpty();
	}

	@Override
	public Diagram createEmptyDiagram() {
		return this.createDiagramWithState(state.createEmptyDiagram());
	}

	@Override
	public Diagram createDiagram(Type t, Diagram d, int x, int y) {
		return this.createDiagramWithState(state.createDiagram(t, d, x, y));
	}

}
