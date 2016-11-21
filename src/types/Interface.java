package types;

import java.lang.reflect.Parameter;

public class Interface<T> implements TypeInterface {

	private Class<T> c;

	public Interface(Class<T> c) {
		this.c = c;
	}

	public Class<T> getC() {
		return c;
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
	public String[] getInterfaces() {
		String[] interfaces = new String[c.getInterfaces().length];
		for (int i = 0; i != interfaces.length; i++) {
			interfaces[i] = c.getInterfaces()[i].getSimpleName();
		}
		return interfaces;
	}

}
