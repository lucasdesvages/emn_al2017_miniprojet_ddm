package diagram;

import java.util.ArrayList;

import types.Type;

public class TypeList implements DiagramState {

	private int x;
	private int y;
	private ArrayList<Diagram> DList;
	private ArrayList<Type> TList;
	private boolean toDescribe;

	public TypeList(ArrayList<Type> TList, ArrayList<Diagram> DList, int x,
			int y) {
		this.TList = TList;
		this.DList = DList;
		this.x = x;
		this.y = y;
		this.toDescribe = false;
	}

	public TypeList() {
		this.TList = new ArrayList<Type>();
		this.DList = new ArrayList<Diagram>();
		this.x = 0;
		this.y = 0;
		this.toDescribe = false;
	}

	@Override
	public ArrayList<Diagram> getDiagrams() {
		return DList;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public ArrayList<Type> getClasses() {
		return TList;
	}

	@Override
	public boolean getToDescribe() {
		return toDescribe;
	}

	@Override
	public void setToDescribe(boolean b) {
		toDescribe = b;

	}

	@Override
	public boolean isEmpty() {
		return TList.isEmpty();
	}

	@Override
	public DiagramState createEmptyDiagram() {
		return createDiagram(new ArrayList<Type>(), new ArrayList<Diagram>(),
				0, 0);
	}

	@Override
	public DiagramState createDiagram(ArrayList<Type> TList,
			ArrayList<Diagram> DList, int x, int y) {
		return new TypeList(TList, DList, x, y);
	}

}
