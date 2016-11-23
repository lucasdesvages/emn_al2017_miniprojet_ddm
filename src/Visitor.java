
public interface Visitor {
	
	void draw(IDiagram d, boolean svg, boolean frame, boolean console);
	
	void label(IDiagram d, String text, int x, int y);
	
	void setFontColor(int color);
	void setPointerShape(int shape);
	void setLineThickness(int thickness);
	void setBackgroundColor(int color);
	void setContourColor(int color);

}
