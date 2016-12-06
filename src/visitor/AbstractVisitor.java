package visitor;

import diagram.Diagram;

public abstract class AbstractVisitor implements Visitor {

	private Diagram diagram;

	public AbstractVisitor(Diagram d) {
		this.diagram = d;
	}

	abstract public void interprete();

	@Override
	public Diagram getDiagram() {
		return diagram;
	}

}
