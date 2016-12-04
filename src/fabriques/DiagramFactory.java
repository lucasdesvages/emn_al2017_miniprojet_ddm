package fabriques;

import diagram.Diagram;

public interface DiagramFactory<T> {

	T createEmptyDiagram();

	T createDiagram(Class<?> c, Diagram diagram, int x, int y);

}
