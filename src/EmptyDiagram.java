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

	public static EmptyDiagram getInstance(){
		if(instance== null){
			instance = new EmptyDiagram();
		} 
		return instance;
	}
	
	private EmptyDiagram(){
		
	}

	@Override
	public <T> void add(Class<T> c, int x, int y){
	}

	@Override
	public <T> void describe(Class<T> c) {
	}

	@Override
	public <T> void insert(Diagram d, int x, int y) {
	}

	@Override
	public void draw(boolean svg, boolean frame, boolean console) {
		
		
			String fileName = "empty_diagram.svg" ;
	
			// Get a DOMImplementation.
			DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
			// Create an instance of org.w3c.dom.Document.
			Document document = domImpl.createDocument(fileName, "svg", null);
			// Create an instance of SVGCanvas in order to display it in a JFrame.
			JSVGCanvas SVGCanvas = new JSVGCanvas();	
		
			int largeur = 200;
			int hauteur = 500;

			SVGCanvas.setSize(largeur + 10, hauteur + 10);
			SVGGraphics2D g2 = new SVGGraphics2D(document);
		
			if(frame||svg){
				// Create the .svg file and write the xml code.
				File image = new File(fileName + ".svg");
				boolean useCSS = true; // we want to use CSS style attributes
				Writer out;
				try {
		
					out = new OutputStreamWriter(new FileOutputStream(image), "UTF-8");
					g2.stream(out, useCSS);
					g2.stream(new OutputStreamWriter(System.out,"UTF-8"), useCSS);
					SVGCanvas.setURI(image.toString());
		
				} catch (Exception e1) {
		
				}
				
				if(frame){
					// Create a JFrame and add the SVGCanvas to display the new created svg image.
					JFrame jframe = new JFrame(fileName);
					jframe.setSize(largeur + 40, hauteur + 60);
					jframe.getContentPane().add(SVGCanvas);
					jframe.setLocationRelativeTo(null);
					jframe.setVisible(true);
					jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			}
		
	}

	@Override
	public void label(String text, int x, int y) {
		// TODO Auto-generated method stub

	}

}
