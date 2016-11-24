package fabriques;

import java.util.ArrayList;

import diagram.Diagram;
import types.Type;

public interface DiagramFactory<T> {

	T createEmptyDiagram();

	T createDiagram(ArrayList<Type> TList, ArrayList<Diagram> DList, int x,
			int y);

}
