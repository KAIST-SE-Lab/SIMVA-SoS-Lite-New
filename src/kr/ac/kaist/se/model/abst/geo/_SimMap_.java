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
}
