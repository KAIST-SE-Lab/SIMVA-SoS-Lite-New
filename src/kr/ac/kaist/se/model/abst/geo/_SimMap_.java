package kr.ac.kaist.se.model.abst.geo;

import java.sql.Timestamp;

/**
 * Abstract class to represent a geographical map of an SoS
 *
 * @author ymbaek, sjlee
 */
public abstract class _SimMap_ {

    protected Timestamp timestamp;    //Timestamp for stdout

    protected String mapId;
    protected String mapName;

    public _SimMap_(String mapId, String mapName) {
        this.mapId = mapId;
        this.mapName = mapName;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }
}
