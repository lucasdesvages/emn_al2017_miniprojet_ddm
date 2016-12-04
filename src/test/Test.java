package test;

import visitor.ConsoleVisitor;
import visitor.Visitor;
import diagram.Diagram;
import diagram.DiagramComposite;
import diagram.DiagramWithList;
import diagram.TypeComposite;
import fabriques.DiagramFactory;

public class Test {

	public static void main(String[] args) {

		DiagramFactory<Diagram> fab = new DiagramComposite();

		Diagram d1 = fab.createEmptyDiagram();

		d1.add(TestClassReader.class,10,10);
		d1.add(TestInterface.class,100,100);



		Visitor v = new ConsoleVisitor(d1);
		v.draw();
	}
}
