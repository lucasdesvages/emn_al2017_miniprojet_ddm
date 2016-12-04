package test;

import visitor.ConsoleVisitor;
import visitor.SVGVisitor;
import diagram.Diagram;
import diagram.DiagramComposite;
import fabriques.DiagramFactory;

public class Test {

	public static void main(String[] args) {

		DiagramFactory<Diagram> fab = new DiagramComposite();

		Diagram vide = fab.createEmptyDiagram();
		Diagram d = fab.createDiagram(TestClassReader.class, vide, 10, 10);

		// d.add(TestInterface.class, 100, 10);
		// d.add(TestInterface.class, 200, 10);
		// d.add(SVGVisitor.class, 50, 50);

		ConsoleVisitor cv = new ConsoleVisitor(d);
		cv.draw();

		SVGVisitor visitor = new SVGVisitor(d, "DiagramTest");
		visitor.draw();
	}
}
