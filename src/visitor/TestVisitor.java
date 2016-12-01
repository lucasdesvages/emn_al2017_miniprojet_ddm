package visitor;
import diagram.EmptyDiagram;

public class TestVisitor {

	public TestVisitor() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		SVGConsoleFrameVisitor v = new SVGConsoleFrameVisitor();
		EmptyDiagram d = EmptyDiagram.getInstance();
		
		v.draw(d, true, true, false);
	}

}
