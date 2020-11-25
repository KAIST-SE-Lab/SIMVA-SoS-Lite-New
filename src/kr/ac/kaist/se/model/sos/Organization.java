package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.model.abst.msg._SimMessage_;
import kr.ac.kaist.se.model.abst.obj._SimContainerObject_;
import kr.ac.kaist.se.model.intf.Movable;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Abstract class to represent an Orgniazation (Org)
 * (1-tier _SimContainerObject_)
 *
 * According to the Meta-Model for Systems-of-Systems (M2SoS),
 * an organization consists of multiple SystemEntities,
 * and driven by a task(s) to be accomplished by collective capabilities of the SystemEntities.
 *
 * Interfaces: Simulatable, Movable
 *
 * @author ymbaek, ehcho, yjshin
 */
public abstract class Organization extends _SimContainerObject_ implements Movable {

    protected SoS mySoS;                //SoS that this organization belongs to
    protected Organization parentOrg;   //Organization that this organization belongs to

    protected EnumOrgType orgType;      //Type of an organization: DIR,ACK,COL,VIR

    protected ArrayList<Organization> subOrgList;       //Sub organizations

    protected ArrayList<Constituent> allMemberCSList;   //All CSs that belong to this org and suborg of this org
    protected ArrayList<Constituent> directCSList;      //CSs that only (directly) belong to this org

    public Organization(SoS simModel, String orgId, String orgName){
        this.mySoS = simModel;

        this.id = orgId;
        this.name = orgName;

        //If the default state need to be different, modify codes below.
        this.isStatic = true;
        this.isActivated = true;
        this.isAvailable = true;

        msgQueue = new LinkedList<_SimMessage_>();

        printObjInfo();
    }


    public Organization(SoS simModel, String orgId, String orgName, boolean isStatic, boolean isActivated, boolean isAvailable){
        this.mySoS = simModel;

        this.id = orgId;
        this.name = orgName;

        //If the default state need to be different, modify codes below.
        this.isStatic = isStatic;
        this.isActivated = isActivated;
        this.isAvailable = isAvailable;

        msgQueue = new LinkedList<_SimMessage_>();

        printObjInfo();
    }


}
