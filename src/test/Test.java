package test;

import visitor.ConsoleVisitor;
import visitor.Visitor;
import diagram.Diagram;
import diagram.DiagramComposite;
import fabriques.DiagramFactory;

public class Test {

	public static void main(String[] args) {

		DiagramFactory<Diagram> fab = new DiagramComposite();

		Diagram vide = fab.createEmptyDiagram();
		Diagram d = fab.createDiagram(TestClassReader.class, vide, 0, 0);

	}
}
