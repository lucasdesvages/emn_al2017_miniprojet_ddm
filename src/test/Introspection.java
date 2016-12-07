package test;

import visitor.SVGVisitor;
import visitor.Visitor;
import diagram.AbstractDiagram;
import diagram.Diagram;
import diagram.DiagramAccessors;
import diagram.DiagramComposite;
import diagram.DiagramServices;
import diagram.EmptyDiagram;
import fabriques.DiagramFactory;

public class Introspection {

	public static void main(String[] args) {
		DiagramFactory<Diagram> fab = new DiagramComposite();

		Diagram diagram = fab.createDiagram(DiagramFactory.class);

		diagram.add(DiagramAccessors.class);
		diagram.add(DiagramServices.class);
		diagram.add(Diagram.class);
		diagram.add(AbstractDiagram.class);
		diagram.add(DiagramComposite.class);
		diagram.add(EmptyDiagram.class);

		Visitor svgVisitor = new SVGVisitor(diagram,
				"Diagramme du package diagram");
		SVGVisitor.largeurMax = 4;
		svgVisitor.interprete();
	}

}
