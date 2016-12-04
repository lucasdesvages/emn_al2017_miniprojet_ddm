package diagram;

import fabriques.DiagramFactory;

public interface Diagram extends DiagramAccessors<Diagram>,
		DiagramFactory<Diagram>, DiagramServices {

}
