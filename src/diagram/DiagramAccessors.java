package diagram;

import java.util.ArrayList;

import types.Type;

public interface DiagramAccessors<T> {

	ArrayList<Type> getClasses();

	ArrayList<Diagram> getDiagrams();

	ArrayList<Boolean> getToDescribe();

	void setToDescribe(int index, boolean b);

	int getX();

	void setX(int x);

	int getY();

	void setY(int y);

	boolean isEmpty();

}
