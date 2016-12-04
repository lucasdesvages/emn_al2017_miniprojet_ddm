package diagram;

import java.util.ArrayList;

public interface DiagramServices {

	void add(Class<?> c);

	ArrayList<String[]> getDescription();

	void insert(Diagram d);

}
