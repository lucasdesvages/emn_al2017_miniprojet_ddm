package diagram;

import java.util.ArrayList;

import types.Type;

public interface DiagramServices {

	void add(Class<?> c);

	void describe(Type t);

	void describe();

	void insert(ArrayList<Diagram> list);

}
