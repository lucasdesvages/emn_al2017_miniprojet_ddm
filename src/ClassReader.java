import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class ClassReader {

	/**
	 * Static method used to retrieve data from a class
	 * 
	 * @param c
	 *            the specified class
	 * @return A list of 4 arrays with the data in this format :<br/>
	 *         <ol>
	 *         <li>the header which includes the type of the java object, the
	 *         name and the package
	 *         <li/>
	 *         the fields of the class in parameter
	 *         <li/>
	 *         the constructors
	 *         <li/>
	 *         the methods
	 *         <ol/>
	 */
	public static ArrayList<String[]> getData(Class c) {

		// -----------------------------------------
		// ---------- data initialization ----------
		// -----------------------------------------
		String[] header = new String[3];
		String[] fields = new String[c.getDeclaredFields().length];
		String[] constructors = new String[c.getDeclaredConstructors().length];
		String[] methods = new String[c.getDeclaredMethods().length];

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
				Parameter[] param = c.getDeclaredConstructors()[i]
						.getParameters();
				constructors[i] = c.getDeclaredConstructors()[i].getName()
						+ "(";
				for (int j = 0; j < param.length - 1; j++) {
					constructors[i] += param[j].getType().getSimpleName()
							+ ", ";
				}
				if (param.length > 0) {
					constructors[i] += param[param.length - 1].getType()
							.getSimpleName() + ")";
				} else {
					constructors[i] += ")";
				}
			}

		}

		// ----------------------------
		// --------- package ----------
		// ----------------------------

		header[1] = c.getName();
		if (c.getPackage() == null) {
			header[2] = "default";
		} else {
			header[2] = c.getPackage().getName();
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
				methods[i] += param[param.length - 1].getType().getSimpleName()
						+ ")";
			} else {
				methods[i] += ")";
			}
			methods[i] += " : "
					+ c.getDeclaredMethods()[i].getReturnType().getSimpleName();
		}

		ArrayList<String[]> res = new ArrayList<String[]>();
		res.add(header);
		res.add(fields);
		res.add(constructors);
		res.add(methods);

		return res;
	}

	/**
	 * Static method used to print the data retrieved from the class c with the
	 * method 'getData'
	 * 
	 * @param c
	 *            class
	 */
	public static void print(Class c) {
		ArrayList<String[]> s = getData(c);

		System.out.println("type : " + s.get(0)[0]);
		System.out.println("name : " + s.get(0)[1]);
		System.out.println("package : " + s.get(0)[2]);
		System.out.println();
		if (s.get(0)[0].equals("Java class")) {
			System.out.println("fields : ");
			for (int i = 0; i != s.get(1).length; i++) {
				System.out.println(s.get(1)[i]);
			}
			System.out.println();
			System.out.println("constructors : ");
			for (int i = 0; i != s.get(2).length; i++) {
				System.out.println(s.get(2)[i]);
			}
			System.out.println();
		}
		System.out.println("methods : ");
		for (int i = 0; i != s.get(3).length; i++) {
			System.out.println(s.get(3)[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		print(TestInterface.class);
		print(TestClassReader.class);
	}

}
