package diagram;

import java.util.ArrayList;

import fabriques.TypeBuilder;
import types.Type;

public abstract class AbstractDiagram implements Diagram {

	private Diagram diagram;
	private Type type;
	private ArrayList<Label> labels;
	
	private boolean toDescribe;

	public AbstractDiagram(Class<?> c, Diagram d) {
		this.type = TypeBuilder.create(c);
		this.diagram = d;
		this.labels = new ArrayList<Label>();
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
	abstract public boolean isEmpty();

	@Override
	abstract public Diagram createEmptyDiagram();

	@Override
	abstract public Diagram createDiagram(Class<?> c, Diagram diagram);

	@Override
	abstract public void add(Class<?> c);

	@Override
	abstract public void insert(Diagram d);

	@Override
	abstract public ArrayList<String[]> getDescription();
	
	@Override
	public ArrayList<Label> getLabels() {
		return labels;
	}

}
