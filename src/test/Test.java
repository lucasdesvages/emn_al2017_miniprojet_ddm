package test;

import visitor.AbstractVisitor;
import visitor.ConsoleVisitor;
import visitor.SVGVisitor;
import visitor.Visitor;

import diagram.Diagram;
import diagram.DiagramComposite;
import diagram.Label;
import fabriques.DiagramFactory;
import fabriques.TypeBuilder;

public class Test {

	public static void main(String[] args) {

		DiagramFactory<Diagram> fab = new DiagramComposite();

		Diagram d = fab.createDiagram(TestClassReader.class);
		Diagram dBis = fab.createDiagram(TestClassReader.class);
		dBis.add(Label.class);

		d.insert(dBis);

		d.add(TestInterface.class);
		d.add(TestInterface.class);
		d.add(SVGVisitor.class);
		d.add(ConsoleVisitor.class);
		d.add(DiagramComposite.class);
		d.add(AbstractVisitor.class);
		d.add(Visitor.class);
		d.add(TypeBuilder.class);

		ConsoleVisitor cv = new ConsoleVisitor(d);
		cv.interprete();

		SVGVisitor visitor = new SVGVisitor(d, "DiagramTest");
		visitor.interprete();

	}
}
