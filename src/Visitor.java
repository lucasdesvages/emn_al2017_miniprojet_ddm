
public interface Visitor {
	
	void draw(Diagram d, boolean svg, boolean frame, boolean console);
	
	void Label(String text, int x, int y);
	
	void setFontColor(int color);
	void setPointerShape(int shape);
	void setLineThickness(int thickness);

}
