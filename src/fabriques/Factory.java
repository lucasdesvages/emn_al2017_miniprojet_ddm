package fabriques;

import types.Classe;
import types.Interface;
import types.Type;

public class Factory {

	public Factory() {
	}

	public <T> Type create(Class<T> c) {
		if (c.isInterface()) {
			return new Interface<T>(c);
		} else {
			return new Classe<T>(c);
		}
	}

}
