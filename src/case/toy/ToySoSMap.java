import kr.ac.kaist.se.model.map.SimMap;
import kr.ac.kaist.se.model.sos.data.DataVar;
import kr.ac.kaist.se.model.sos.data.DimensionVar;
import kr.ac.kaist.se.model.sos.data.DimensionVarDomain;

import java.util.ArrayList;

public class ToySoSMap extends SimMap {

//    ArrayList<DimensionVar> mapDimensions = new ArrayList<>();
    ArrayList<DataVar> dataVarList = new ArrayList<>();

    MapCoordinateDimensionDomain xDimDomain = new MapCoordinateDimensionDomain();

    //TODO: Add domains for each dimensionVar
    MapCoordinateDimensionType xDim = new MapCoordinateDimensionType("DIM_X", "xDimension", "Int");
    MapCoordinateDimensionType yDim = new MapCoordinateDimensionType("DIM_Y", "yDimension", "Int");
    MapFloorDimensionType floorDim = new MapFloorDimensionType("DIM_FLOOR", "floorNumDimension", "Enum");

    public ToySoSMap(String mapId, String mapName) {
        super(mapId, mapName);
        initDimensions();
    }

    public ToySoSMap(String mapId, String mapName, ArrayList<DimensionVar> mapDimensions) {
        super(mapId, mapName, mapDimensions);
        initDimensions();
    }


    private void initDimensions(){
        mapDimensions.add(xDim);
        mapDimensions.add(yDim);
        mapDimensions.add(floorDim);
    }

    @Override
    protected void initializeMap() {

    }
}
