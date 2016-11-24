package diagram;

import java.util.ArrayList;

import types.Type;

public abstract class AbstractDiagram implements Diagram {

	private DiagramState state;

	public AbstractDiagram(DiagramState state) {
		this.state = state;
	}

	abstract public Diagram createDiagramWithState(DiagramState s);

	@Override
	public ArrayList<Diagram> getDiagrams() {
		return state.getDiagrams();
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
	public ArrayList<Type> getClasses() {
		return getState().getClasses();
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
	public Diagram createDiagram(ArrayList<Type> TList,
			ArrayList<Diagram> DList, int x, int y) {
		return this.createDiagramWithState(state.createDiagram(TList, DList, x,
				y));
	}

}
