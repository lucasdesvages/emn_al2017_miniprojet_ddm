package visitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import types.Type;
import diagram.Diagram;

public class SVGConsoleFrameVisitor implements Visitor {

	public SVGConsoleFrameVisitor() {
		// TODO Auto-generated constructor stub
	}

	public void draw(Diagram d, boolean frame, boolean svg, boolean console) {

		if (d.isEmpty()) { // Draw the empty diagram
			drawEmptyDiagram(frame, svg, console);
		}

		else {
			drawNonEmptyDiagram(d, frame, svg, console);
		}
	}

	private void drawEmptyDiagram(boolean frame, boolean svg, boolean console) {

		String fileName = "empty_diagram";
		File image = null;

		// Get a DOMImplementation.
		DOMImplementation domImpl = GenericDOMImplementation
				.getDOMImplementation();
		// Create an instance of org.w3c.dom.Document.
		Document document = domImpl.createDocument(fileName, "svg", null);
		// Create an instance of SVGCanvas in order to display it in a JFrame.
		JSVGCanvas SVGCanvas = new JSVGCanvas();

		int largeur = 300;
		int hauteur = 300;

		SVGCanvas.setSize(largeur + 10, hauteur + 10);
		SVGGraphics2D g2 = new SVGGraphics2D(document);

		if (frame || svg) {
			// Create the .svg file and write the xml code.
			image = new File(fileName + ".svg");
		}
		boolean useCSS = true; // we want to use CSS style attributes
		Writer out;
		try {
			out = new OutputStreamWriter(new FileOutputStream(image), "UTF-8");
			g2.stream(out, useCSS);
			if (console) {
				g2.stream(new OutputStreamWriter(System.out, "UTF-8"), useCSS);
			}
			SVGCanvas.setURI(image.toString());

		} catch (Exception e1) {
		}

		if (frame) {
			// Create a JFrame and add the SVGCanvas to display the new
			JFrame jframe = new JFrame(fileName);
			jframe.setSize(largeur + 10, hauteur + 10);
			jframe.getContentPane().add(SVGCanvas);
			jframe.setLocationRelativeTo(null);
			jframe.setVisible(true);
			jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		if (!svg) { // Delete the so useless svg file
			image.delete();
		}

	}

	private void drawNonEmptyDiagram(Diagram d, boolean frame, boolean svg,
			boolean console) {
		ArrayList<Type> toDescribe = new ArrayList<Type>();
		for (int i = 0; i != d.getClasses().size(); i++) {
			if (d.getToDescribe().get(i)) {
				toDescribe.add(d.getClasses().get(i));
			}
		}
		if (console) {
			for (int i = 0; i != toDescribe.size(); i++) {
				System.out.println(toDescribe.get(i).toString());
			}
		}
		if (frame) {
			// lancer ihm
		}
		if (svg) {
			// dessiner le svg
		}
	}

	@Override
	public void label(Diagram d, String text, int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFontColor(int color) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPointerShape(int shape) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLineThickness(int thickness) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBackgroundColor(int color) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContourColor(int color) {
		// TODO Auto-generated method stub

	}

}
