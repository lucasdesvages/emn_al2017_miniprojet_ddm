package fabriques;

import diagram.Diagram;
import types.Type;

public interface DiagramFactory<T> {

	T createEmptyDiagram();

	T createDiagram(Type type, Diagram diagram, int x, int y);

}
