package diagram;

import java.util.ArrayList;

import types.Type;

public interface DiagramAccessors<T> {

	/**
	 * 
	 * @return
	 */
	Type getType();

	Diagram getDiagram();

	void setDiagram(Diagram d);

	Diagram getLastDiagram();

	boolean getToDescribe();

	void setToDescribe(boolean b);

	boolean isEmpty();
	
	ArrayList<Label> getLabels();

}
