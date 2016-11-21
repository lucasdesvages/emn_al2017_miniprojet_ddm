import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Parameter;
import javax.swing.JFrame;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.swing.JSVGCanvas;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

public class ClassReader<T> {
	// -----------------------------------------
	// ---------- data initialization ----------
	// -----------------------------------------
	Class<T> c;
	String[] header;
	String[] fields;
	String[] constructors;
	String[] methods;

	public ClassReader(Class<T> c) {
		this.c = c;
		header = new String[3];
		fields = new String[c.getDeclaredFields().length];
		constructors = new String[c.getDeclaredConstructors().length];
		methods = new String[c.getDeclaredMethods().length];
		// ----------------------------------------
		// ---- header : type + name + package ----
		// ----------------------------------------

		if (c.isAnnotation()) {
			header[0] = "Java annotation";
		} else if (c.isEnum()) {
			header[0] = "Java enumeration";
		} else if (c.isInterface()) {
			header[0] = "Java interface";
		} else {
			header[0] = "Java class";
			// ----------------------------
			// --------- package ----------
			// ----------------------------

			header[1] = c.getName();
			if (c.getPackage() == null) {
				header[2] = "default";
			} else {
				header[2] = c.getPackage().getName();
			}

			// ----------------------------------
			// ------------- fields -------------
			// ----------------------------------

			for (int i = 0; i != fields.length; i++) {
				fields[i] = c.getDeclaredFields()[i].getName() + ": "
						+ c.getDeclaredFields()[i].getType().getSimpleName();
			}

			// ---------------------------------
			// --------- constructors ----------
			// ---------------------------------

			for (int i = 0; i != constructors.length; i++) {
				Parameter[] param = c.getDeclaredConstructors()[i].getParameters();
				constructors[i] = c.getDeclaredConstructors()[i].getName() + "(";
				for (int j = 0; j < param.length - 1; j++) {
					constructors[i] += param[j].getType().getSimpleName() + ", ";
				}
				if (param.length > 0) {
					constructors[i] += param[param.length - 1].getType().getSimpleName() + ")";
				} else {
					constructors[i] += ")";
				}
			}

		}

		// ----------------------------
		// --------- methods ----------
		// ----------------------------

		for (int i = 0; i != methods.length; i++) {
			Parameter[] param = c.getDeclaredMethods()[i].getParameters();
			methods[i] = c.getDeclaredMethods()[i].getName() + "(";
			for (int j = 0; j < param.length - 1; j++) {
				methods[i] += param[j].getType().getSimpleName() + ", ";
			}
			if (param.length > 0) {
				methods[i] += param[param.length - 1].getType().getSimpleName() + ")";
			} else {
				methods[i] += ")";
			}
			methods[i] += " : " + c.getDeclaredMethods()[i].getReturnType().getSimpleName();
		}
	}

	public String[] getHeader() {
		return header;
	}

	public String getType() {
		return header[0];
	}

	public String getName() {
		return header[1];
	}

	public String getPackage() {
		return header[2];
	}

	public String[] getFields() {
		return fields;
	}

	public String[] getConstructors() {
		return constructors;
	}

	public String[] getMethods() {
		return methods;
	}

	/**
	 * Static method used to print the data retrieved from the class c with the
	 * method 'getData'
	 * 
	 *            class
	 */
	public void print() {

		System.out.println("type : " + this.getType());
		System.out.println("name : " + this.getName());
		System.out.println("package : " + this.getPackage());
		System.out.println();
		if (this.getType().equals("Java class")) {
			System.out.println("fields : ");
			for (int i = 0; i != this.getFields().length; i++) {
				System.out.println(this.getFields()[i]);
			}
			System.out.println();
			System.out.println("constructors : ");
			for (int i = 0; i != this.getConstructors().length; i++) {
				System.out.println(this.getConstructors()[i]);
			}
			System.out.println();
		}
		System.out.println("methods : ");
		for (int i = 0; i != this.getMethods().length; i++) {
			System.out.println(this.getMethods()[i]);
		}
		System.out.println();
	}

	/**
	 * Method used to draw the box of a class in svg format and to display it in
	 * a window.
	 * 
	 */

	public void dessiner() {

		String fileName = "" + getName();

		// Get a DOMImplementation.
		DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
		// Create an instance of org.w3c.dom.Document.
		Document document = domImpl.createDocument(fileName, "svg", null);
		// Create an instance of SVGCanvas in order to display it in a JFrame.
		JSVGCanvas SVGCanvas = new JSVGCanvas();

		// largeur = taille de la plus longue string

		// int largeur = longest element length;
		int largeur = this.getLongestStringLength() * 7;
		// hauteur = nombre d'elements du type
		int hauteur = 110 + 20 * this.getFields().length + 20 + 20 * this.getConstructors().length
				+ 20 * this.getMethods().length;

		SVGCanvas.setSize(largeur + 10, hauteur + 10);
		SVGGraphics2D g2 = new SVGGraphics2D(document);
		g2.setPaint(Color.BLACK);

		g2.draw(new Rectangle(10, 10, largeur, hauteur));
		g2.drawString(getType(), (largeur + 10 - getType().length() * 6) / 2, 30);
		g2.setFont(new Font("default", Font.BOLD, 12));
		g2.drawString(getName(), (largeur + 10 - getName().length() * 6) / 2, 50);
		g2.setFont(new Font("default", Font.PLAIN, 12));
		g2.drawString(getPackage(), (largeur + 10 - getPackage().length() * 6) / 2, 70);
		g2.drawLine(10, 90, largeur + 10, 90);

		for (int i = 0; i < this.getFields().length; i++) {
			g2.drawString(this.getFields()[i], 20, 110 + 20 * i);
		}

		g2.drawLine(10, 110 + 20 * this.getFields().length, largeur + 10, 110 + 20 * this.getFields().length);

		for (int i = 0; i < this.getConstructors().length; i++) {
			g2.drawString(this.getConstructors()[i], 20, 110 + 20 * this.getFields().length + 20 + 20 * i);
		}
		for (int i = 0; i < this.getMethods().length; i++) {
			g2.drawString(this.getMethods()[i], 20,
					110 + 20 * this.getFields().length + 20 + 20 * this.getConstructors().length + 20 * i);
		}

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
		
		// Create a JFrame and add the SVGCanvas to display the new created svg image.
		JFrame frame = new JFrame(getName() + ".svg");
		frame.setSize(largeur + 40, hauteur + 60);
		frame.getContentPane().add(SVGCanvas);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Method used to get the longest string of the class in order to calculate
	 * the future width of the class box
	 * 
	 */
	public int getLongestStringLength() {
		int length = this.getName().length();
		if (this.getType().length() > length) {
			length = this.getType().length();
		}
		if (this.getPackage().length() > length) {
			length = this.getPackage().length();
		}
		for (int i = 0; i < this.getFields().length; i++) {
			if (this.getFields()[i].length() > length) {
				length = this.getFields()[i].length();
			}
		}

		for (int i = 0; i < this.getConstructors().length; i++) {
			if (this.getConstructors()[i].length() > length) {
				length = this.getConstructors()[i].length();
			}
		}
		for (int i = 0; i < this.getMethods().length; i++) {
			if (this.getMethods()[i].length() > length) {
				length = this.getMethods()[i].length();
			}
		}
		return length;
	}
	
	public static void main(String[] args) {
		// print(TestInterface.class);
		// print(TestClassReader.class);
		ClassReader<TestClassReader> cr = new ClassReader<TestClassReader>(TestClassReader.class);
		cr.dessiner();

	}
}
