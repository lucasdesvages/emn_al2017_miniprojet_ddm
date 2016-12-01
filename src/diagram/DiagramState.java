package diagram;

import fabriques.DiagramFactory;

public interface DiagramState extends DiagramAccessors<DiagramState>,
		DiagramFactory<DiagramState> {

}
