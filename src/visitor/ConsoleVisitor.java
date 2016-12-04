package visitor;

import java.util.ArrayList;

import types.Type;
import diagram.Diagram;

public class ConsoleVisitor extends AbstractVisitor implements Visitor {

	public ConsoleVisitor(Diagram d) {
		super(d);
	}

	@Override
	public void draw() {
		ArrayList<Type> toDescribe = new ArrayList<Type>();
		for (int i = 0; i != getDiagram().getClasses().size(); i++) {
			if (getDiagram().getToDescribe().get(i)) {
				toDescribe.add(getDiagram().getClasses().get(i));
			}
		}
		for (int i = 0; i != toDescribe.size(); i++) {
			System.out.println(toDescribe.get(i).toString());
		}

	}

}
