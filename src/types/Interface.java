package types;

import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class Interface implements TypeInterface {

	private Class<?> c;

	public Interface(Class<?> c) {
		this.c = c;
	}

	@Override
	public String getType() {
		return "<<Java Interface>>";
	}

	@Override
	public String getName() {
		return c.getSimpleName();
	}

	@Override
	public String getPackage() {
		return c.getPackage().getName();
	}

	@Override
	public String[] getHeader() {
		String[] res = new String[3];
		res[0] = getType();
		res[1] = getName();
		res[2] = getPackage();
		return res;
	}

	@Override
	public String[] getInterfaces() {
		String[] interfaces = new String[c.getInterfaces().length];
		for (int i = 0; i != interfaces.length; i++) {
			interfaces[i] = c.getInterfaces()[i].getSimpleName();
		}
		return interfaces;
	}

	@Override
	public String[] getMethods() {
		String[] methods = new String[c.getDeclaredMethods().length];

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
		return methods;
	}

	@Override
	public Class<?> getC() {
		return this.c;
	}

	@Override
	public ArrayList<String[]> getDescription() {
		ArrayList<String[]> res = new ArrayList<String[]>();
		res.add(getHeader());
		res.add(getInterfaces());
		res.add(getMethods());
		return res;
	}

	public String toString() {
		String s = "";
		ArrayList<String[]> data = getDescription();
		for (int i = 0; i != data.size(); i++) {
			for (int j = 0; j != data.get(i).length; j++) {
				s += data.get(i)[j] + "\n";
			}
			s += "\n";
		}

		return s;
	}

	@Override
	public boolean equals(Type t) {
		return getC().equals(t.getC());
	}
}
