package diagram;

import types.Type;

public interface DiagramAccessors<T> {

	Type getType();

	Diagram getDiagram();

	void setDiagram(Diagram d);

	Diagram getLastDiagram();

	boolean getToDescribe();

	void setToDescribe(boolean b);

	int getX();

	void setX(int x);

	int getY();

	void setY(int y);

	boolean isEmpty();

}
