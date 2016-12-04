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
		Diagram d = fab.createDiagram(TestClassReader.class, vide);

		d.add(TestInterface.class);
		d.add(TestInterface.class);
		d.add(SVGVisitor.class);

		ConsoleVisitor cv = new ConsoleVisitor(d);
		cv.draw();

		SVGVisitor visitor = new SVGVisitor(d, "DiagramTest");
		visitor.draw();
	}
}
