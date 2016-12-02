package visitor;

import diagram.Diagram;

public abstract class AbstractVisitor implements Visitor {

	private Diagram d;

	public AbstractVisitor(Diagram d) {
		this.d = d;
	}

	abstract public void visit();

	@Override
	public Diagram getDiagram() {
		return d;
	}

}
