package diagram;

import types.Type;

public interface DiagramAccessors<T> {

	Type getType();

	Diagram getDiagram();

	void setDiagram(Diagram d);

	Diagram getLastDiagram();

	boolean getToDescribe();

	void setToDescribe(boolean b);

	boolean isEmpty();

}
