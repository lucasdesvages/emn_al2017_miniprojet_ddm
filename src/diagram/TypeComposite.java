package diagram;

import types.Type;

public class TypeComposite implements DiagramState {

	private int x;
	private int y;
	private Diagram diagram;
	private Type type;
	private boolean toDescribe;

	public TypeComposite(Type t, Diagram d, int x, int y) {
		this.diagram = d;
		this.type = t;
		this.x = x;
		this.y = y;
		this.toDescribe = false;
	}

	public TypeComposite() {
		this.diagram = null;
		this.type = null;
		this.x = 0;
		this.y = 0;
		this.toDescribe = false;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public Diagram getDiagram() {
		return diagram;
	}

	@Override
	public void setDiagram(Diagram d) {
		this.diagram = d;

	}

	@Override
	public boolean getToDescribe() {
		return toDescribe;
	}

	@Override
	public void setToDescribe(boolean b) {
		this.toDescribe = b;

	}

	@Override
	public boolean isEmpty() {
		return this.diagram == null;
	}

	@Override
	public DiagramState createEmptyDiagram() {
		return new TypeComposite();
	}

	@Override
	public DiagramState createDiagram(Type type, Diagram diagram, int x, int y) {
		return new TypeComposite(type, diagram, x, y);
	}

}
