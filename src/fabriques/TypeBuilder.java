package fabriques;

import types.Classe;
import types.Interface;
import types.Type;

public class TypeBuilder {

	public static Type create(Class<?> c) {
		if (c.isInterface()) {
			return new Interface(c);
		} else {
			return new Classe(c);
		}
	}

}
