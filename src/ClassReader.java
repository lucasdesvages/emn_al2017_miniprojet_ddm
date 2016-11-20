import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

import org.jfree.graphics2d.svg.SVGGraphics2D;

public class ClassReader {
	// -----------------------------------------
	// ---------- data initialization ----------
	// -----------------------------------------
	Class c;
	String[] header;
	String[] fields;
	String[] constructors;
	String[] methods;

	public ClassReader(Class cbis) {
		this.c = cbis;
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
	 * @param c
	 *            class
	 */
	public static void print(Class c) {
		ClassReader cr = new ClassReader(c);
		System.out.println("type : " + cr.getType());
		System.out.println("name : " + cr.getName());
		System.out.println("package : " + cr.getPackage());
		System.out.println();
		if (cr.getType().equals("Java class")) {
			System.out.println("fields : ");
			for (int i = 0; i != cr.getFields().length; i++) {
				System.out.println(cr.getFields()[i]);
			}
			System.out.println();
			System.out.println("constructors : ");
			for (int i = 0; i != cr.getConstructors().length; i++) {
				System.out.println(cr.getConstructors()[i]);
			}
			System.out.println();
		}
		System.out.println("methods : ");
		for (int i = 0; i != cr.getMethods().length; i++) {
			System.out.println(cr.getMethods()[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// print(TestInterface.class);
		// print(TestClassReader.class);
		ClassReader cr = new ClassReader(TestClassReader.class);
		cr.dessiner();
	}

	public int getTaillePlusLongueString() {
		int taille = this.getName().length();
		if (this.getType().length() > taille) {
			taille = this.getType().length();
		}
		if (this.getPackage().length() > taille) {
			taille = this.getPackage().length();
		}
		for (int i = 0; i < this.getFields().length; i++) {
			if (this.getFields()[i].length() > taille) {
				taille = this.getFields()[i].length();
			}
		}

		for (int i = 0; i < this.getConstructors().length; i++) {
			if (this.getConstructors()[i].length() > taille) {
				taille = this.getConstructors()[i].length();
			}
		}
		for (int i = 0; i < this.getMethods().length; i++) {
			if (this.getMethods()[i].length() > taille) {
				taille = this.getMethods()[i].length();
			}
		}
		return taille;
	}

	public void dessiner() {
		String fileName = "" + getName();
		int largeur = this.getTaillePlusLongueString()*7;
		int hauteur = 110 + 20 * this.getFields().length + 20 + 
				20 * this.getConstructors().length + 20 * this.getMethods().length;
		SVGGraphics2D g2 = new SVGGraphics2D(500, 500);
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

		String svgElement = g2.getSVGElement();
		System.out.println(svgElement);
		File image = new File(fileName + ".svg");

		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(image));
			writer.write(svgElement);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

}
