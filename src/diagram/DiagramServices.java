package diagram;

import java.util.ArrayList;

public interface DiagramServices {

	void add(Class<?> c);

	ArrayList<String[]> getDescription();

	void insert(Diagram d);

	void label(String text, int x, int y);

	boolean contains(Diagram d);

	boolean equals(Diagram d);

}
