import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.swing.JFrame;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

public class EmptyDiagram implements Diagram {
	
	private static EmptyDiagram instance;

	public static EmptyDiagram getInstance() {
		if (instance == null) {
			instance = new EmptyDiagram();
		}
		return instance;
	}

	private EmptyDiagram() {

	}

	@Override
	public <T> void add(Class<T> c, int x, int y) {
	}

	@Override
	public <T> void describe(Class<T> c) {
	}

	@Override
	public <T> void insert(Diagram d, int x, int y) {
	}

	@Override
	public boolean IsEmptyDiagram() {
		return true;
	}

}
