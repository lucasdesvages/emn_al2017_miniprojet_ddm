package diagram;

import java.util.ArrayList;

public interface DiagramServices {

	void add(Class<?> c, int x, int y);

	ArrayList<String[]> getDescription();

	void insert(Diagram d, int x, int y);

}
