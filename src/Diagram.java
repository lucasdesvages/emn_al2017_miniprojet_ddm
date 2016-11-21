
public interface Diagram {
			
	<T> void add(Class<T> c, int x, int y);
	
	<T> void describe(Class<T> c);
	
	<T> void insert(Diagram d, int x, int y);
	
	boolean IsEmptyDiagram();
}
