import java.lang.reflect.Parameter;
import java.util.ArrayList;

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
		print(TestInterface.class);
		print(TestClassReader.class);
	}

}
