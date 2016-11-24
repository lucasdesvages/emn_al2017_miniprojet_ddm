package diagram;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import fabriques.TypeBuilder;
import types.Type;

public class DiagramWithList extends AbstractDiagram implements Diagram {

	public DiagramWithList(DiagramState state) {
		super(state);
	}

	@Override
	public void add(Class<?> c) {
		this.getClasses().add(TypeBuilder.create(c));

	}

	@Override
	public void describe(Type t) {
		if (getClasses().contains(t)) {
			this.setToDescribe(true);
		} else {
			this.setToDescribe(false);
			throw new InvalidParameterException(
					"It is not in the list of classes contained in the diagram");
		}

	}

	@Override
	public void describe() {
		for (Type t : getClasses()) {
			describe(t);
		}
	}

	@Override
	public void insert(ArrayList<Diagram> list) {
		for (Diagram d : list) {
			for (int i = 0; i != d.getClasses().size(); i++) {
				add(d.getClasses().get(i).getC());
			}
		}
		this.getDiagrams().addAll(list);

	}

	@Override
	public boolean isEmpty() {
		return getClasses().isEmpty();
	}

	@Override
	public Diagram createDiagramWithState(DiagramState s) {
		return new DiagramWithList(s);
	}

}
