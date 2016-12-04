package visitor;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
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

	// Marge horizontale
	public static int margeHor = 20;
	// Marge verticale
	public static int margeVer = 20;

	// Nombre max de types sur une ligne
	public static int largeurMax = 6;
	// Nombre courant de types sur une ligne
	private static int largeurCompt = 0;
	// Hauteur max calculée pendant le dessin
	private static int hMax = 0;

	// Default Font
	Font defaultFont = new Font("default", Font.PLAIN, 12);
	// Font metrics to get a text width
	FontMetrics metrics;

	public SVGVisitor(Diagram diagram, String name) {
		super(diagram);
		this.name = name;
	}

	@Override
	public void draw() {

		// Get a DOMImplementation.
		DOMImplementation domImpl = GenericDOMImplementation
				.getDOMImplementation();
		// Create an instance of org.w3c.dom.Document.
		Document document = domImpl.createDocument(name, "svg", null);
		// Create an instance of SVGCanvas in order to display it in a JFrame.
		JSVGCanvas SVGCanvas = new JSVGCanvas();
		SVGCanvas.setSize(800, 600);

		File image = null;

		// int largeur = longest element length;
		// int largeur = this.getWidth(this.getDiagram()) * 7;
		// hauteur = nombre d'elements du type
		// int hauteur = this.getHeight();

		SVGGraphics2D g2 = new SVGGraphics2D(document);
		metrics = g2.getFontMetrics();

		drawDiagram(this.getDiagram(), 10, 10, g2);

		// Create the .svg file and write the xml code.
		image = new File(this.name + ".svg");

		boolean useCSS = true; // we want to use CSS style attributes
		Writer out;
		try {
			out = new OutputStreamWriter(new FileOutputStream(image), "UTF-8");
			g2.stream(out, useCSS);
			g2.stream(new OutputStreamWriter(System.out, "UTF-8"), useCSS);
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
		if (!d.isEmpty()) {
			if (largeurCompt < largeurMax) {
				int[] lh = drawType(d, x, y, g);
				if (lh[1] > hMax) {
					hMax = lh[1];

				}

				drawDiagram(d.getDiagram(), x + margeHor + lh[0], y, g);
			} else {
				largeurCompt = 0;
				int[] lh = drawType(d, x, y, g);
				drawDiagram(d.getDiagram(), x + margeHor + lh[0], y + lh[1]
						+ margeVer, g);
				hMax = 0;
			}
		}
	}

	/**
	 * Calculate the width of the type t from its longest string in its
	 * description
	 * 
	 * @return int width
	 */
	public int getWidth(Type t) {
		String longest = "";

		ArrayList<String[]> description = t.getDescription();
		for (int i = 0; i != description.size(); i++) {
			for (int j = 0; j != description.get(i).length; j++) {
				if (description.get(i)[j].length() > longest.length()) {
					longest = description.get(i)[j];
				}
			}
		}

		return (int) (metrics.stringWidth(longest) + margeHor);
	}

	/**
	 * 
	 * Calculate the height of the type from the total number of elements in its
	 * description.
	 * 
	 * @param
	 * @return int height
	 */
	public int getHeight(Diagram d) {
		int nbLig = 0;
		for (int i = 0; i < d.getDescription().size(); i++) {
			nbLig += d.getDescription().get(i).length;
		}
		return (30 + 20 * (nbLig));
	}

	public int[] drawType(Diagram d, int x, int y, SVGGraphics2D g2) {

		g2.setPaint(Color.BLACK);
		int largeur = this.getWidth(d.getType());

		int hauteur = this.getHeight(d);
		int[] res = { largeur, hauteur };

		g2.draw(new Rectangle(x, y, largeur, hauteur));

		// Type type
		g2.drawString(d.getType().getType(), x
				+ ((largeur + 10 - d.getType().getType().length() * 6) / 2),
				y + 20);

		// Type name
		g2.setFont(new Font("default", Font.BOLD, 12));
		g2.drawString(d.getType().getName(), x
				+ ((largeur + 10 - d.getType().getName().length() * 6) / 2),
				y + 40);

		// Type package
		// g2.setFont(new Font("default", Font.PLAIN, 12));
		g2.setFont(defaultFont);
		g2.drawString(d.getType().getPackage(), x
				+ ((largeur + 10 - d.getType().getPackage().length() * 6) / 2),
				y + 60);
		g2.drawLine(x, y + 80, x + largeur, y + 80);

		if (!d.getType().getC().isInterface()) {

			// Class fields
			for (int i = 0; i < ((TypeClass) d.getType()).getFields().length; i++) {
				g2.drawString(((TypeClass) d.getType()).getFields()[i], x + 20,
						y + 100 + 20 * i);
			}
			g2.drawLine(x, y + 100 + 20
					* ((TypeClass) d.getType()).getFields().length,
					x + largeur,
					y + 100 + 20 * ((TypeClass) d.getType()).getFields().length);
			// Class constructors
			g2.setFont(new Font("default", Font.ITALIC, 12));
			for (int i = 0; i < ((TypeClass) d.getType()).getConstructors().length; i++) {
				g2.drawString(((TypeClass) d.getType()).getConstructors()[i],
						x + 20,
						y + 100 + 20
								* ((TypeClass) d.getType()).getFields().length
								+ 20 + 20 * i);
			}
			g2.setFont(defaultFont);
			// Class methods
			for (int i = 0; i < d.getType().getMethods().length; i++) {
				g2.drawString(d.getType().getMethods()[i], x + 20, y + 100 + 20
						* ((TypeClass) d.getType()).getFields().length + 20
						+ 20
						* ((TypeClass) d.getType()).getConstructors().length
						+ 20 * i);
			}
		} else {
			for (int i = 0; i < d.getType().getMethods().length; i++) {
				g2.drawString(d.getType().getMethods()[i], x + 20, y + 100 + 20
						* i);
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
