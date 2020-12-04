package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.model.abst.comm._SimMessage_;
import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.intf.Communicatable;
import kr.ac.kaist.se.model.intf.DecisionMakeable;
import kr.ac.kaist.se.model.intf.Movable;
import kr.ac.kaist.se.model.intf.Stateful;
import kr.ac.kaist.se.model.sos.geo.ObjectLocation;

import java.util.LinkedList;

/**
 * Abstract class to represent a constituent system, called CS.
 *
 * According to the M2SoS, a Constituent is one and only object that can belong to an organization.
 * A Constituent can communicate (i.e., Communicatable) and make its own decisions (i.e., DecisionMakeable).
 *
 * Interfaces: Simulatable, Actionable, Stateful, Movable, Communicatable, DecisionMakeable
 *
 * @author ymbaek, ehcho, yjshin
 */
public abstract class Constituent extends _SimActionableObject_
        implements Stateful, Movable, Communicatable, DecisionMakeable {

    protected ObjectLocation csObjectLocation;

    protected SoS mySoS;                //SoS that this object belongs to
    protected Organization myOrg;       //Organization that this object belongs to

    //TODO: knowledge base
    //TODO: decision making

    public Constituent(SoS simModel, Organization myOrg, String csId, String csName){
        this.mySoS = simModel;
        this.myOrg = myOrg;

        this.id = csId;
        this.name = csName;

        //If the default state need to be different, modify codes below.
        this.isStatic = true;
        this.isActivated = true;
        this.isAvailable = true;

        msgQueue = new LinkedList<_SimMessage_>();

        printObjInfo();
    }

    public Constituent(SoS simModel, Organization myOrg, String csId, String csName, boolean isStatic, boolean isActivated, boolean isAvailable){
        this.mySoS = simModel;
        this.myOrg = myOrg;

        this.id = csId;
        this.name = csName;

        //If the default state need to be different, modify codes below.
        this.isStatic = isStatic;
        this.isActivated = isActivated;
        this.isAvailable = isAvailable;

        msgQueue = new LinkedList<_SimMessage_>();

        printObjInfo();
    }


    /* Getters & Setters */

    /**
     * A method that returns current location of this object
     * @return ObjectLocation of this organization
     */
    public ObjectLocation getCurLocation() {
        return csObjectLocation;
    }

    public void setCsObjectLocation(ObjectLocation csObjectLocation) {
        this.csObjectLocation = csObjectLocation;
    }

    public SoS getMySoS() {
        return mySoS;
    }

    public void setMySoS(SoS mySoS) {
        this.mySoS = mySoS;
    }

    public Organization getMyOrg() {
        return myOrg;
    }

    public void setMyOrg(Organization myOrg) {
        this.myOrg = myOrg;
    }
}
