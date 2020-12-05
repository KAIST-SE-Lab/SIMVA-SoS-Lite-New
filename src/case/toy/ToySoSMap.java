import kr.ac.kaist.se.model.map.SimMap;
import kr.ac.kaist.se.model.sos.data.DimensionVar;

import java.util.ArrayList;

public class ToySoSMap extends SimMap {


    public ToySoSMap(String mapId, String mapName) {
        super(mapId, mapName);
    }

    public ToySoSMap(String mapId, String mapName, ArrayList<DimensionVar> mapDimensions) {
        super(mapId, mapName, mapDimensions);
    }

    @Override
    protected void initializeMap() {

    }
}
