package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.model.abst.comm._SimMessage_;
import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.intf.Communicatable;
import kr.ac.kaist.se.model.intf.Movable;
import kr.ac.kaist.se.model.intf.Stateful;

import java.util.LinkedList;

/**
 * Abstract class to represent a system (or system-based entity).
 * <p>
 * According to the M2SoS, a SystemEntity is a base class to represent an infrastructure system.
 * A SystemEntity can perform its own actions (i.e., Actionable),
 * and it can do communication actions (i.e., Communicatable).
 * <p>
 * Interfaces: Simulatable, Actionable, Stateful, Movable, Communicatable
 *
 * @author ymbaek, ehcho, yjshin
 */
public abstract class SystemEntity extends _SimActionableObject_
        implements Stateful, Communicatable, Movable {

    //SoS that this object belongs to
    protected SoS mySoS;
    //Infrastructure that this object belongs to
    protected Infrastructure myInfra;

    public SystemEntity(SoS simModel, Infrastructure myInfra, String systemId, String systemName) {
        this.mySoS = simModel;
        this.myInfra = myInfra;

        this.id = systemId;
        this.name = systemName;

        //If the default state need to be different, modify codes below.
        this.isStatic = true;
        this.isActivated = true;
        this.isAvailable = true;

        msgQueue = new LinkedList<_SimMessage_>();

        printObjInfo();
    }

    public SystemEntity(SoS simModel, Infrastructure myInfra, String systemId, String systemName, boolean isStatic, boolean isActivated, boolean isAvailable) {
        this.mySoS = simModel;
        this.myInfra = myInfra;

        this.id = systemId;
        this.name = systemName;

        //If the default state need to be different, modify codes below.
        this.isStatic = isStatic;
        this.isActivated = isActivated;
        this.isAvailable = isAvailable;

        msgQueue = new LinkedList<_SimMessage_>();

        printObjInfo();
    }


    /* Getters & Setters */


    public SoS getMySoS() {
        return mySoS;
    }

    public void setMySoS(SoS mySoS) {
        this.mySoS = mySoS;
    }

    public Infrastructure getMyInfra() {
        return myInfra;
    }

    public void setMyInfra(Infrastructure myInfra) {
        this.myInfra = myInfra;
    }
}
