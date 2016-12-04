package test;

import visitor.ConsoleVisitor;
import visitor.Visitor;
import diagram.Diagram;
import diagram.DiagramWithList;
import diagram.TypeComposite;
import fabriques.DiagramFactory;

public class Test {

	public static void main(String[] args) {

		DiagramFactory<Diagram> fab = new DiagramWithList(new TypeComposite());

		Diagram d1 = fab.createEmptyDiagram();

		d1.add(TestClassReader.class);
		d1.add(TestInterface.class);

		d1.setToDescribe(0, true);
		d1.setToDescribe(1, true);

		Visitor v = new ConsoleVisitor(d1);
		v.visit();
	}
}
