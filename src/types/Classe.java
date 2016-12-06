package types;

import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class Classe extends Interface implements TypeClass {

	public Classe(Class<?> c) {
		super(c);
	}

	@Override
	public String getType() {
		return "<<Java Class>>";
	}

	@Override
	public String[] getHeader() {
		String[] res = new String[4];
		res[0] = getType();
		res[1] = getName();
		res[2] = getPackage();
		res[3] = getSuperClass();

		return res;
	}

	@Override
	public String[] getFields() {
		String[] fields = new String[getC().getDeclaredFields().length];
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
			String[] name = getC().getDeclaredConstructors()[i].getName()
					.split("\\.");
			constructors[i] = name[1] + "(";
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
		if (getC().getSuperclass().getSimpleName().equals("Object")) {
			return "";
		}
		return getC().getSuperclass().getSimpleName();
	}

	@Override
	public ArrayList<String[]> getDescription() {
		ArrayList<String[]> res = new ArrayList<String[]>();
		res.add(getHeader());
		res.add(getInterfaces());
		res.add(getFields());
		res.add(getConstructors());
		res.add(getMethods());

		return res;
	}
	
	public Class<?>[] getImplementedInterfaces(){
		return getC().getInterfaces();
	}
}
