package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.model.abst.msg._SimMessage_;
import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.intf.Communicatable;
import kr.ac.kaist.se.model.intf.DecisionMakeable;
import kr.ac.kaist.se.model.intf.Movable;

import java.util.LinkedList;

/**
 * Abstract class to represent a constituent system, called CS.
 *
 * According to the M2SoS, a Constituent is one and only object that can belong to an organization.
 * A Constituent can communicate (i.e., Communicatable) and make its own decisions (i.e., DecisionMakeable).
 *
 * Interfaces: Simulatable, Actionable, Movable, Communicatable, DecisionMakeable
 *
 * @author ymbaek, ehcho, yjshin
 */
public abstract class Constituent extends _SimActionableObject_ implements Movable, Communicatable, DecisionMakeable {

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
}
