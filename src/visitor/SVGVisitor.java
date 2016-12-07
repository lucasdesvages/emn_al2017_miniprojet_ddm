package visitor;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.JSVGScrollPane;
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
	public static int largeurMax = 9;

	// Nombre courant de types sur une ligne
	private static int largeurCompt = 0;

	// Hauteur max calculée pendant le dessin
	private static int hMax = 0;

	// Hauteur max courante
	private static int hMaxCourante = hMax;

	// Police par defaut
	private Font defaultFont = new Font("default", Font.PLAIN, 12);
	// Couleur de police
	private Color fontColor = Color.BLACK;
	// Metriques de police (utilisees pour obtenir la taille d'une string)
	private FontMetrics metrics;

	// Couleur de fond
	private Color backgroundColor = new Color(255, 249, 200, 255);
	// Couleur de contour
	private Color contourColor = Color.BLACK;
	// Epaisseur des contours
	private BasicStroke stroke = new BasicStroke(1.0f);
	// Taille pointe de fleche
	private int pointe = 10;

	// Objet de dessin
	private SVGGraphics2D g2;

	/**
	 * Construit un Visiteur SVG capable
	 * 
	 * @param diagram
	 *            - Diagram
	 * @param name
	 *            - Nom du diagramme et du fichier svg
	 * 
	 */

	public SVGVisitor(Diagram diagram, String name) {
		super(diagram);
		this.name = name;
	}

	/**
	 * Fonction d'interpretation du diagramme. Elle dessine le diagramme dans un
	 * fichier SVG et l'affiche dans une Frame
	 * 
	 */
	@Override
	public void interprete() {

		// Get a DOMImplementation.
		DOMImplementation domImpl = GenericDOMImplementation
				.getDOMImplementation();

		// Create an instance of org.w3c.dom.Document.
		Document document = domImpl.createDocument(name, "svg", null);

		// Create an instance of SVGCanvas in order to display it in a JFrame.
		JSVGCanvas SVGCanvas = new JSVGCanvas();
		SVGCanvas.setSize(2500, 2500);

		// Create the svg file
		File image = null;

		g2 = new SVGGraphics2D(document);
		metrics = g2.getFontMetrics();

		HashMap<Class<?>, int[]> typePos = new HashMap<Class<?>, int[]>();

		drawDiagram(this.getDiagram(), margeHor, margeVer, typePos);

		drawRelations(typePos);

		// Create the .svg file and write the xml code.
		image = new File(this.name + ".svg");

		boolean useCSS = true; // we want to use CSS style attributes
		Writer out;
		try {
			out = new OutputStreamWriter(new FileOutputStream(image), "UTF-8");
			g2.stream(out, useCSS);
			// g2.stream(new OutputStreamWriter(System.out, "UTF-8"), useCSS);
			SVGCanvas.setURI(image.toString());

		} catch (Exception e1) {
		}

		// Create a JFrame and add the SVGCanvas to display the new
		JFrame jframe = new JFrame(this.name + ".svg");
		JPanel panel = new JPanel();
		JSVGScrollPane scroll = new JSVGScrollPane(SVGCanvas);
		panel.setLayout(new BorderLayout());
		panel.add(scroll, BorderLayout.CENTER);
		panel.setSize(SVGCanvas.getSize());
		jframe.setSize(SVGCanvas.getSize());
		jframe.add(panel);
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void drawDiagram(Diagram d, int x, int y,
			HashMap<Class<?>, int[]> typePos) {
		largeurCompt++;
		if (!d.isEmpty()) {

			if (largeurCompt < largeurMax) {
				int[] lh = drawType(d, x, y);
				if (lh[1] > hMaxCourante) {
					hMaxCourante = lh[1];
				}
				typePos.put(d.getType().getC(),
						new int[] { x, y, lh[0], lh[1] });

				drawDiagram(d.getDiagram(), x + margeHor + lh[0], y, typePos);
				drawLabels(d, x, y);

			} else {
				hMax += hMaxCourante + margeVer;
				int[] lh = drawType(d, margeHor, margeVer + hMax);
				hMaxCourante = lh[1];
				largeurCompt = 1;
				typePos.put(d.getType().getC(), new int[] { x, y, lh[1] });
				drawDiagram(d.getDiagram(), 2 * margeHor + lh[0], y + hMax,
						typePos);
				drawLabels(d, x, y);
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
		/*
		 * if(!d.getType().getC().isInterface()&&!d.getType().g){ nbLig-=1; }
		 */
		return (30 + 20 * (nbLig));
	}

	/**
	 * 
	 * @param d
	 * @param x
	 * @param y
	 * @param g2
	 * @return
	 */

	private int[] drawType(Diagram d, int x, int y) {

		int largeur = this.getWidth(d.getType());
		int hauteur = this.getHeight(d);
		int[] res = { largeur, hauteur };

		g2.setPaint(backgroundColor);
		g2.fillRect(x, y, largeur, hauteur);

		g2.setStroke(stroke);
		g2.setPaint(contourColor);
		g2.draw(new Rectangle(x, y, largeur, hauteur));

		g2.setPaint(fontColor);
		// Type type
		g2.drawString(
				d.getType().getType(),
				x
						+ ((largeur - metrics
								.stringWidth(d.getType().getType())) / 2),
				y + 20);

		// Type name
		g2.setFont(new Font("default", Font.BOLD, 12));
		g2.drawString(
				d.getType().getName(),
				x
						+ ((largeur - metrics
								.stringWidth(d.getType().getName())) / 2),
				y + 40);

		// Type package
		g2.setFont(defaultFont);
		g2.drawString(
				d.getType().getPackage(),
				x
						+ ((largeur - metrics.stringWidth(d.getType()
								.getPackage())) / 2), y + 60);
		g2.setPaint(contourColor);
		g2.drawLine(x, y + 80, x + largeur, y + 80);

		if (!d.getType().getC().isInterface()) {

			// Class fields
			for (int i = 0; i < ((TypeClass) d.getType()).getFields().length; i++) {
				g2.setPaint(fontColor);
				g2.drawString(((TypeClass) d.getType()).getFields()[i], x + 20,
						y + 100 + 20 * i);
			}
			g2.setPaint(contourColor);
			g2.drawLine(x, y + 100 + 20
					* ((TypeClass) d.getType()).getFields().length,
					x + largeur,
					y + 100 + 20 * ((TypeClass) d.getType()).getFields().length);

			// Class constructors
			g2.setPaint(fontColor);
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
			// Interface methods
			for (int i = 0; i < d.getType().getMethods().length; i++) {
				g2.drawString(d.getType().getMethods()[i], x + 20, y + 100 + 20
						* i);
			}
		}
		g2.setPaint(Color.BLACK);
		return res;
	}

	private void drawRelations(HashMap<Class<?>, int[]> typePos) {

		for (Entry<Class<?>, int[]> e : typePos.entrySet()) {
			Class<?> superClasse = e.getKey().getSuperclass();
			if (superClasse != java.lang.Object.class
					&& typePos.containsKey(superClasse)) {
				if (e.getValue()[1] == typePos.get(superClasse)[1]) {
					g2.drawLine(e.getValue()[0] + e.getValue()[2],
							e.getValue()[1] + e.getValue()[3],
							typePos.get(superClasse)[0],
							typePos.get(superClasse)[1]);
					drawArrow(e.getValue()[0] + e.getValue()[2],
							e.getValue()[1] + e.getValue()[3],
							typePos.get(superClasse)[0],
							typePos.get(superClasse)[1]);
				} else {
					g2.drawLine(e.getValue()[0], e.getValue()[1],
							typePos.get(superClasse)[0],
							typePos.get(superClasse)[1]);
					drawArrow(e.getValue()[0], e.getValue()[1],
							typePos.get(superClasse)[0],
							typePos.get(superClasse)[1]);
				}
			}

			float dash1[] = { 10.0f };
			BasicStroke dashed = new BasicStroke(2.0f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
			g2.setStroke(dashed);

			for (int i = 0; i < e.getKey().getInterfaces().length; i++) {
				Class<?> interfaceBis = e.getKey().getInterfaces()[i];
				if (interfaceBis != java.lang.Object.class
						&& typePos.containsKey(interfaceBis)) {
					if (e.getValue()[1] == typePos.get(interfaceBis)[1]) {
						g2.drawLine(e.getValue()[0] + e.getValue()[2],
								e.getValue()[1] + e.getValue()[3],
								typePos.get(interfaceBis)[0],
								typePos.get(interfaceBis)[1]);
						drawArrow(e.getValue()[0] + e.getValue()[2],
								e.getValue()[1] + e.getValue()[3],
								typePos.get(interfaceBis)[0],
								typePos.get(interfaceBis)[1]);
					} else {
						g2.drawLine(e.getValue()[0], e.getValue()[1],
								typePos.get(interfaceBis)[0],
								typePos.get(interfaceBis)[1]);
						drawArrow(e.getValue()[0], e.getValue()[1],
								typePos.get(interfaceBis)[0],
								typePos.get(interfaceBis)[1]);
					}
				}
			}
			BasicStroke normal = new BasicStroke();
			g2.setStroke(normal);
		}
	}

	private void drawLabels(Diagram diagram, int x, int y) {
		if (!diagram.getLabels().isEmpty()) {
			for (int i = 0; i < diagram.getLabels().size(); i++) {
				g2.setColor(new Color(200, 30, 30, 200));
				g2.fillRoundRect(
						x + diagram.getLabels().get(i).getX(),
						y + diagram.getLabels().get(i).getY(),
						2
								* margeHor
								+ (int) (metrics.stringWidth(diagram
										.getLabels().get(i).getText())), 30,
						20, 20);
				g2.setColor(Color.WHITE);
				g2.drawString(diagram.getLabels().get(i).getText(), x
						+ margeHor + diagram.getLabels().get(i).getX(), y
						+ diagram.getLabels().get(i).getY() + 20);
				g2.setColor(Color.BLACK);
			}
		}
	}

	/**
	 * Draw the arrow's pointer that points to (x2,y2) with the direction of
	 * (x2-x1,y2-y1)
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	private void drawArrow(int x1, int y1, int x2, int y2) {

		int[] x = new int[3];
		int[] y = new int[3];
		double vx, vy;

		vx = (x2 - x1) * pointe / norme(x2 - x1, y2 - y1);
		vy = (y2 - y1) * pointe / norme(x2 - x1, y2 - y1);

		x[0] = (int) (x2 - 1.5 * vx - vy / 1.5);
		x[1] = (int) (x2 - 1.5 * vx + vy / 1.5);
		x[2] = (int) (x2);

		y[0] = (int) (y2 - 1.5 * vy + vx / 1.5);
		y[1] = (int) (y2 - 1.5 * vy - vx / 1.5);
		y[2] = (int) (y2);

		g2.setColor(contourColor);
		g2.fillPolygon(x, y, 3);
		g2.setColor(Color.BLACK);

	}

	private double norme(int x, int y) {
		return Math.sqrt(x * x + y * y);
	}

	public void setFontColor(Color color) {
		fontColor = color;

	}

	public void setPointerShape(int shape) {
		// TODO Auto-generated method stub

	}

	public void setLineThickness(float thickness) {
		stroke = new BasicStroke(thickness);

	}

	public void setBackgroundColor(Color color) {
		backgroundColor = color;
	}

	public void setContourColor(Color color) {
		contourColor = color;

	}

}
