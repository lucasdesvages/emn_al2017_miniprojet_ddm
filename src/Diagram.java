
public interface Diagram {
			
	<T> void add(Class<T> c, int x, int y);
	
	<T> void describe(Class<T> c);
	
	<T> void insert(Diagram d, int x, int y);
	
	void draw(boolean svg, boolean frame, boolean console);
	
	void label(String text, int x, int y);	

}
