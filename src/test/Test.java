package test;

import java.util.ArrayList;

import diagram.Diagram;
import diagram.DiagramWithList;
import diagram.TypeList;
import fabriques.DiagramFactory;

public class Test {

	public static void main(String[] args) {

		DiagramFactory<Diagram> fab = new DiagramWithList(new TypeList());

		Diagram d1 = fab.createEmptyDiagram();

		d1.add(TestClassReader.class);
		d1.add(TestInterface.class);

		d1.setToDescribe(0, true);
		d1.setToDescribe(1, true);

		for (int i = 0; i != d1.getClasses().size(); i++) {
			if (d1.getToDescribe().get(i)) {
				// draw i
				ArrayList<String[]> description = d1.getClasses().get(i)
						.getDescription();
				System.out.println(d1.getClasses().get(i).toString());
			}
		}
	}
}
