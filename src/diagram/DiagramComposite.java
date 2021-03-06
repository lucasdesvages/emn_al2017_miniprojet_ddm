package diagram;

import java.util.ArrayList;

public class DiagramComposite extends AbstractDiagram implements Diagram {

	public DiagramComposite(Class<?> c, Diagram d) {
		super(c, d);
	}

	public DiagramComposite() {
		super();
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Diagram createDiagram(Class<?> c) {
		return new DiagramComposite(c, EmptyDiagram.getInstance());
	}

	@Override
	public void add(Class<?> c) {
		Diagram d = createDiagram(c);
		if (!this.contains(d)) {
			Diagram lastDiagram = getLastDiagram();
			lastDiagram.setDiagram(d);
		}
	}

	@Override
	public void insert(Diagram d) {
		if (!d.isEmpty()) {
			this.add(d.getType().getC());
			insert(d.getDiagram());
		}
	}

	@Override
	public ArrayList<String[]> getDescription() {
		return getType().getDescription();
	}

	@Override
	public void label(String text, int x, int y) {
		getLabels().add(new Label(text, x, y));

	}

}
