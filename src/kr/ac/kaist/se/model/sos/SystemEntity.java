package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.model.abst.msg._SimMessage_;
import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.intf.Actionable;
import kr.ac.kaist.se.model.intf.Communicatable;

import java.util.LinkedList;

/**
 * Abstract class to represent a system (or system-based entity).
 *
 * According to the M2SoS, a SystemEntity is a base class to represent an infrastructure system.
 * A SystemEntity can perform its own actions (i.e., Actionable),
 * and it can do communication actions (i.e., Communicatable).
 *
 * Interfaces: Simulatable, Actionable, Communicatable
 *
 * @author ymbaek, ehcho, yjshin
 */
public abstract class SystemEntity extends _SimActionableObject_ implements Communicatable {

    protected SoS mySoS;                //SoS that this object belongs to
    protected Infrastructure myInfra;   //Infrastructure that this object belongs to

    public SystemEntity(SoS simModel, Infrastructure myInfra, String systemId, String systemName){
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

    public SystemEntity(SoS simModel, Infrastructure myInfra, String systemId, String systemName, boolean isStatic, boolean isActivated, boolean isAvailable){
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


}
