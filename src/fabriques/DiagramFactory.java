package fabriques;

public interface DiagramFactory<T> {

	T createDiagram(Class<?> c);

}
