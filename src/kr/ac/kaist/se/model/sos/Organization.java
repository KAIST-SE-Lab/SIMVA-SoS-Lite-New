package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.model.abst.obj._SimContainerObject_;

import java.util.ArrayList;

/**
 * Abstract class to represent an Orgniazation (Org)
 * (1-tier _SimContainerObject_)
 *
 * According to the Meta-Model for Systems-of-Systems (M2SoS),
 * an organization consists of multiple SystemEntities,
 * and driven by a task(s) to be accomplished by collective capabilities of the SystemEntities.
 *
 * Interfaces: Simulatable, Actionable, Communicatable
 *
 * @author ymbaek, ehcho, yjshin
 */
public abstract class Organization extends _SimContainerObject_ {

    protected SoS mySoS;                //SoS that this organization belongs to
    protected Organization parentOrg;   //Organization that this organization belongs to

    protected EnumOrgType orgType;      //Type of an organization: DIR,ACK,COL,VIR

    protected ArrayList<Organization> subOrgList;       //Sub organizations

    protected ArrayList<Constituent> allMemberCSList;   //All CSs that belong to this org and suborg of this org
    protected ArrayList<Constituent> directCSList;      //CSs that only (directly) belong to this org


}
