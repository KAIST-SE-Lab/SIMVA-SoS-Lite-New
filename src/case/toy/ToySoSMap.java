import kr.ac.kaist.se.model.map.SimMap;
import kr.ac.kaist.se.model.sos.data.LocDimensionVar;

import java.util.ArrayList;

public class ToySoSMap extends SimMap {
    public ToySoSMap(String name) {
        super(name);
    }

    public ToySoSMap(String name, ArrayList<LocDimensionVar> mapDimensions) {
        super(name, mapDimensions);
    }
}
