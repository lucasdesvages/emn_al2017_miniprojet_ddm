package test;

import types.Type;
import types.TypeClass;
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

		String s = "";
		for (int i = 0; i != d1.getClasses().size(); i++) {
			if (d1.getToDescribe().get(i)) {
				Type t = d1.getClasses().get(i);
				s += t.getType() + "\n";
				s += t.getName() + "\n";
				s += t.getPackage() + "\n";
				s += "\n";

				for (int j = 0; j != t.getInterfaces().length; j++) {
					s += t.getInterfaces()[j] + "\n";
				}
				s += "\n";

				if (t.getType().equals("<<Java Class>>")) {
					s += ((TypeClass) t).getSuperClass() + "\n";
					for (int j = 0; j != ((TypeClass) t).getFields().length; j++) {
						s += ((TypeClass) t).getFields()[j] + "\n";
					}
					s += "\n";
					for (int j = 0; j != ((TypeClass) t).getConstructors().length; j++) {
						s += ((TypeClass) t).getConstructors()[j] + "\n";
					}
					s += "\n";
				}
				for (int j = 0; j != t.getMethods().length; j++) {
					s += t.getMethods()[j] + "\n";
				}
				s += "\n";
			}
		}

		System.out.println(s);
	}
}
