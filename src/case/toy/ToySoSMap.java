import data.MapCoordinateDimensionDomain;
import data.MapCoordinateDimensionType;
import data.MapFloorDimensionDomain;
import data.MapFloorDimensionType;
import kr.ac.kaist.se.model.abst.data.EnumDomainType;
import kr.ac.kaist.se.model.map.SimMap;
import kr.ac.kaist.se.model.sos.data.DataVar;
import kr.ac.kaist.se.model.sos.data.DimensionVar;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

public class ToySoSMap extends SimMap {

    public static MapCoordinateDimensionType xDim;
    public static MapCoordinateDimensionType yDim;
    public static MapFloorDimensionType floorDim;

    public ToySoSMap(String mapId, String mapName) {
        super(mapId, mapName);
        initDimensions();
    }

    public ToySoSMap(String mapId, String mapName, ArrayList<DimensionVar> mapDimensions) {
        super(mapId, mapName, mapDimensions);
        initDimensions();
    }


    private void initDimensions(){

        /* Definition of domains for each dimension variable */
        MapCoordinateDimensionDomain xDimDomain = new MapCoordinateDimensionDomain(EnumDomainType.VALUE_RANGE, 0, 10);
        MapCoordinateDimensionDomain yDimDomain = new MapCoordinateDimensionDomain(EnumDomainType.VALUE_RANGE, 0, 10);
        MapFloorDimensionDomain floorDimDomain = new MapFloorDimensionDomain(EnumDomainType.ENUMERATION, new ArrayList<String>(Arrays.asList("FLOOR_B1", "FLOOR_1", "FLOOR_2", "FLOOR_3")));

        /* Initialization of every dimension with the domains defined */
        xDim = new MapCoordinateDimensionType("DIM_X", "xDimension", "Int", xDimDomain);
        yDim = new MapCoordinateDimensionType("DIM_Y", "yDimension", "Int", yDimDomain);
        floorDim = new MapFloorDimensionType("DIM_FLOOR", "floorNumDimension", "Enum", floorDimDomain);

        /* Add dimensions to this map */
        mapDimensions.add(xDim);
        mapDimensions.add(yDim);
        mapDimensions.add(floorDim);

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (ToySoSMap: initDimensions) Dimensions are initialized (size:" + mapDimensions.size() + ")");
    }

    @Override
    protected void initializeMap() {

    }
}
