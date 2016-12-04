package diagram;

import java.util.ArrayList;

import fabriques.TypeBuilder;
import types.Type;

public abstract class AbstractDiagram implements Diagram {

	private int x;
	private int y;
	private Diagram diagram;
	private Type type;
	private boolean toDescribe;

	public AbstractDiagram(Class<?> c, Diagram d, int x, int y) {
		this.type = TypeBuilder.create(c);
		this.diagram = d;
		this.x = x;
		this.y = y;
		this.toDescribe = true;
	}

	public AbstractDiagram() {

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
	public Diagram getLastDiagram() {
		if (getDiagram().isEmpty()) {
			return this;
		} else {
			return getDiagram().getLastDiagram();
		}
	}

	@Override
	public boolean getToDescribe() {
		return toDescribe;
	}

	@Override
	public void setToDescribe(boolean b) {
		toDescribe = b;
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
	abstract public boolean isEmpty();

	@Override
	abstract public Diagram createEmptyDiagram();

	@Override
	abstract public Diagram createDiagram(Class<?> c, Diagram diagram, int x,
			int y);

	@Override
	abstract public void add(Class<?> c, int x, int y);

	@Override
	abstract public void insert(Diagram d);

	@Override
	abstract public ArrayList<String[]> getDescription();

}
