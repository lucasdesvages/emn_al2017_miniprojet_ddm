
public interface IDiagram {
			
	<T> void add(Class<T> c, int x, int y);
	
	<T> void describe(Class<T> c);
	
	<T> void insert(IDiagram d, int x, int y);
	
	boolean IsEmptyDiagram();
}
