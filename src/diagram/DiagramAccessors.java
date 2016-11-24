package diagram;

import java.util.ArrayList;

import types.Type;

public interface DiagramAccessors<T> {

	ArrayList<Type> getClasses();

	ArrayList<Diagram> getDiagrams();

	int getX();

	void setX(int x);

	int getY();

	void setY(int y);

	boolean getToDescribe();

	void setToDescribe(boolean b);

	boolean isEmpty();

}
