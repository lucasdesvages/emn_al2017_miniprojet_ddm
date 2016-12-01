package visitor;

import diagram.Diagram;

public interface Visitor {

	void draw(Diagram d, boolean svg, boolean frame, boolean console);

	void label(Diagram d, String text, int x, int y);

	void setFontColor(int color);

	void setPointerShape(int shape);

	void setLineThickness(int thickness);

	void setBackgroundColor(int color);

	void setContourColor(int color);

}
