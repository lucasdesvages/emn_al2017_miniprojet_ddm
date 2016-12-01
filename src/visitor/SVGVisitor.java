package visitor;

import java.util.ArrayList;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import types.Type;
import diagram.Diagram;

public class SVGVisitor extends AbstractVisitor implements Visitor {

	private String name;

	public SVGVisitor(Diagram d, String name) {
		super(d);
		this.name = name;
	}

	@Override
	public void visit() {

		// Get a DOMImplementation.
		DOMImplementation domImpl = GenericDOMImplementation
				.getDOMImplementation();
		// Create an instance of org.w3c.dom.Document.
		Document document = domImpl.createDocument(name, "svg", null);
		// Create an instance of SVGCanvas in order to display it in a JFrame.
		JSVGCanvas SVGCanvas = new JSVGCanvas();

	}

	public int getLongestStringLength(Type t) {
		int res = 0;
		ArrayList<String[]> description = t.getDescription();
		for (int i = 0; i != description.size(); i++) {
			for (int j = 0; j != description.get(i).length; j++) {
				if (description.get(i)[j].length() > res) {
					res = description.get(i)[j].length();
				}
			}
		}

		return res;
	}

	public void label(String text, int x, int y) {
		// TODO Auto-generated method stub

	}

	public void setFontColor(int color) {
		// TODO Auto-generated method stub

	}

	public void setPointerShape(int shape) {
		// TODO Auto-generated method stub

	}

	public void setLineThickness(int thickness) {
		// TODO Auto-generated method stub

	}

	public void setBackgroundColor(int color) {
		// TODO Auto-generated method stub

	}

	public void setContourColor(int color) {
		// TODO Auto-generated method stub

	}

}
