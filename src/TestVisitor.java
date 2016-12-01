public class TestVisitor {

	
	public static void main(String[] args) {
		SVGConsoleFrameVisitor v = new SVGConsoleFrameVisitor();
		EmptyDiagram d = EmptyDiagram.getInstance();
		
		v.draw(d, true, true, false);
	}

}
