package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.model.abst.geo._SimMap_;
import kr.ac.kaist.se.model.sos.data.DimensionVar;
import kr.ac.kaist.se.model.sos.data.DataVar;

import java.util.ArrayList;

/**
 * A class to represent a geographical map of an SoS
 * (For current version, the Map class is not much different from _SimMap_)
 *
 * @author ymbaek
 */
public abstract class Map extends _SimMap_ {

    /* (_SimMap_)
    protected String mapId;
    protected String mapName;
     */

    protected ArrayList<DimensionVar> dimensionVarList;
    protected ArrayList<DataVar> dataVarList;
}
