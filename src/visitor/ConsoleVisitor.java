package visitor;


import java.util.ArrayList;

import diagram.Diagram;

public class ConsoleVisitor extends AbstractVisitor implements Visitor {

	public ConsoleVisitor(Diagram d) {
		super(d);
	}

	@Override

	public void interprete() {
		/*TODO*/

		drawDiagram(getDiagram());
	}

	private void drawDiagram(Diagram d) {
		if (!d.isEmpty()) {
			ArrayList<String[]> toDescribe = d.getDescription();
			for (int i = 0; i != toDescribe.size(); i++) {
				for (int j = 0; j != toDescribe.get(i).length; j++) {
					System.out.println(toDescribe.get(i)[j]);
				}
				System.out.println();
			}
			drawDiagram(d.getDiagram());
		}

	}

}
