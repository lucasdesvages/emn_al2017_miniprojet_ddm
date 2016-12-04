package visitor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
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
import types.TypeClass;
import diagram.Diagram;

public class SVGVisitor extends AbstractVisitor implements Visitor {

	private String name;
	
	//Marge horizontale
	public static int margeHor = 20;
	//Marge verticale
	public static int margeVer = 20;
	
	//Nombre max de types sur une ligne
	public static int largeurMax = 6;
	//Nombre courant de types sur une ligne
	private static int largeurCompt = 0;
	//Hauteur max calculée pendant le dessin
	private static int hMax = 0;

	public SVGVisitor(Diagram d, String name) {
		super(d);
		this.name = name;
	}

	@Override
	public void draw() {

		// Get a DOMImplementation.
		DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
		// Create an instance of org.w3c.dom.Document.
		Document document = domImpl.createDocument(name, "svg", null);
		// Create an instance of SVGCanvas in order to display it in a JFrame.
		JSVGCanvas SVGCanvas = new JSVGCanvas();

		File image = null;

		// int largeur = longest element length;
		//int largeur = this.getWidth(this.getDiagram()) * 7;
		// hauteur = nombre d'elements du type
		//int hauteur = this.getHeight();		
		
		SVGGraphics2D g2 = new SVGGraphics2D(document);
	
		drawDiagram(this.getDiagram(), 0,0, g2);
		
		SVGCanvas.setSize(800, 600);		

		// Create the .svg file and write the xml code.
		image = new File(this.name + ".svg");

		boolean useCSS = true; // we want to use CSS style attributes
		Writer out;
		try {
			out = new OutputStreamWriter(new FileOutputStream(image), "UTF-8");
			g2.stream(out, useCSS);
			SVGCanvas.setURI(image.toString());

		} catch (Exception e1) {
		}

		// Create a JFrame and add the SVGCanvas to display the new
		JFrame jframe = new JFrame(this.name + ".svg");
		jframe.setSize(800, 600);
		jframe.getContentPane().add(SVGCanvas);
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	

	private void drawDiagram(Diagram d, int x, int y, SVGGraphics2D g) {
		if(!d.isEmpty()){
			if(largeurCompt<largeurMax){
				int[] lh = drawType(d,x,y, g);
				if(lh[1]>hMax){
					hMax=lh[1];
				}
				drawDiagram(d.getDiagram(),x+margeHor+ lh[0] ,y+margeVer ,g);
			}
			else{
				largeurCompt=0;
				int[] lh = drawType(d, d.getX(), d.getY(), g);
				drawDiagram(d.getDiagram(),x+margeHor+ lh[0] ,y+lh[1]+margeVer, g);
				hMax=0;
			}
		}			
	}

	/**
	 * 
	 * @param t
	 * @return
	 */
	public int getWidth(Type t) {
		int width = 0;
		
		ArrayList<String[]> description = t.getDescription();
		for (int i = 0; i != description.size(); i++) {
			for (int j = 0; j != description.get(i).length; j++) {
				if (description.get(i)[j].length() > width) {
					width = description.get(i)[j].length();
				}
			}
		}
		return width*7;
	}
	
	/**
	 * Calculate the height of the type from the total number of elements in its description.
	 * @return int height
	 */
	public int getHeight(Type t) {
		return  (110 + 20 * this.getDiagram().getDescription().size()+ 20);
	}
	
	public int[] drawType(Diagram d, int x, int y, SVGGraphics2D g2){
				
		g2.setPaint(Color.BLACK);
		int largeur = this.getWidth(d.getType());
		int hauteur = this.getHeight(d.getType());
		int[] res = {largeur, hauteur};
		
		g2.draw(new Rectangle(x, y, largeur, hauteur));
		//Type type
		g2.drawString(d.getType().getType(), x+((largeur + 10 - largeur * 6) / 2),
				y + 20);
		//Type name
		g2.setFont(new Font("default", Font.BOLD, 12));
		g2.drawString(d.getType().getName(), x+ (largeur + 10 - d.getType().getName().length() * 6) / 2,
				y + 40);
		//Type package
		g2.setFont(new Font("default", Font.PLAIN, 12));
		g2.drawString(d.getType().getPackage(),
				x+ ((largeur + 10 - d.getType().getPackage().length() * 6) / 2), y +60);
		g2.drawLine(x , y +80, x + largeur,y + 80);
		
		if(!d.getType().getC().isInterface()){
			//Class fields
			for (int i = 0; i < ((TypeClass) d).getFields().length; i++) {
				g2.drawString(((TypeClass) d).getFields()[i], x + 20, y + 100 + 20 * i);
			}
			g2.drawLine(x, y + 100 + 20 * ((TypeClass) d).getFields().length, largeur + 10,
					100 + 20 * ((TypeClass) d).getFields().length);
			//Class constructors
			for (int i = 0; i < ((TypeClass) d).getConstructors().length; i++) {
				g2.drawString(((TypeClass) d).getConstructors()[i], x + 20,
						y + 100 + 20 * ((TypeClass) d).getFields().length + 20 + 20 * i);
			}
			for (int i = 0; i < d.getType().getMethods().length; i++) {
				g2.drawString(
						d.getType().getMethods()[i],
						x + 20,
						y + 100 + 20 * ((TypeClass) d).getFields().length + 20 + 20
								* ((TypeClass) d).getConstructors().length + 20 * i);
			}
		}
		for (int i = 0; i < d.getType().getMethods().length; i++) {
			g2.drawString(
					d.getType().getMethods()[i],
					x + 20,
					y + 100 );
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
