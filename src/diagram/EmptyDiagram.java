package diagram;
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

public class EmptyDiagram extends AbstractDiagram implements Diagram {
	
	private static EmptyDiagram instance;

	public EmptyDiagram(DiagramState state) {
		super(state);
		// TODO Auto-generated constructor stub
	}



	public static EmptyDiagram getInstance() {
		if (instance == null) {
			instance = new EmptyDiagram();
		}
		return instance;
	}



	@Override
	public void add(Class<?> c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void describe(Type t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void describe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(ArrayList<Diagram> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Diagram createDiagramWithState(DiagramState s) {
		// TODO Auto-generated method stub
		return null;
	}



}
