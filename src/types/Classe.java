package types;

import java.lang.reflect.Parameter;

public class Classe<T> extends Interface<T> implements TypeClass {

	public Classe(Class<T> c) {
		super(c);
	}

	@Override
	public String getType() {
		return "<<Java Class>>";
	}

	@Override
	public String[] getFields() {
		String[] fields = new String[getC().getFields().length];
		for (int i = 0; i != fields.length; i++) {
			fields[i] = getC().getDeclaredFields()[i].getName() + ": "
					+ getC().getDeclaredFields()[i].getType().getSimpleName();
		}
		return fields;
	}

	@Override
	public String[] getConstructors() {
		String[] constructors = new String[getC().getDeclaredConstructors().length];
		for (int i = 0; i != constructors.length; i++) {
			Parameter[] param = getC().getDeclaredConstructors()[i]
					.getParameters();
			constructors[i] = getC().getDeclaredConstructors()[i].getName()
					+ "(";
			for (int j = 0; j < param.length - 1; j++) {
				constructors[i] += param[j].getType().getSimpleName() + ", ";
			}
			if (param.length > 0) {
				constructors[i] += param[param.length - 1].getType()
						.getSimpleName() + ")";
			} else {
				constructors[i] += ")";
			}
		}
		return constructors;
	}

	@Override
	public String getSuperClass() {
		return getC().getSuperclass().getSimpleName();
	}

}
