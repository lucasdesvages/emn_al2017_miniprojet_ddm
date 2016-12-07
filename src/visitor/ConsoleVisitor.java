package visitor;


import java.util.ArrayList;

import diagram.Diagram;

public class ConsoleVisitor extends AbstractVisitor implements Visitor {

	public ConsoleVisitor(Diagram d) {
		super(d);
	}

	@Override

	public void interprete() {
		drawDiagram(getDiagram());
		drawLabels(getDiagram());
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
	
	private void drawLabels(Diagram diagram) {
		if(!diagram.getLabels().isEmpty()){
			for(int i=0; i<diagram.getLabels().size(); i++){
				System.out.println("Label : "+diagram.getLabels().get(i).getText());
			}
		}
	}

}
