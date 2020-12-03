package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.model.abst.comm._SimMessage_;
import kr.ac.kaist.se.model.abst.obj._SimContainerObject_;
import kr.ac.kaist.se.model.intf.Movable;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Abstract class to represent an Orgniazation (Org)
 * (1-tier _SimContainerObject_)
 * <p>
 * According to the Meta-Model for Systems-of-Systems (M2SoS),
 * an organization consists of multiple SystemEntities,
 * and driven by a task(s) to be accomplished by collective capabilities of the SystemEntities.
 * <p>
 * Interfaces: Simulatable, Movable
 *
 * @author ymbaek, ehcho, yjshin
 */
public abstract class Organization extends _SimContainerObject_ implements Movable {

    protected SoS mySoS;                //SoS that this organization belongs to
    protected Organization parentOrg;   //Organization that this organization belongs to

    protected int orgDepth;

    protected EnumOrgType orgType;      //Type of an Organization(" + this.id + "): DIR,ACK,COL,VIR

    protected ArrayList<Organization> subOrgList;       //Sub organizations

    protected ArrayList<Constituent> allMemberCSList;   //All CSs that belong to this org and suborg of this org
    protected ArrayList<Constituent> directCSList;      //CSs that only (directly) belong to this org


    public Organization(SoS simModel, String orgId, String orgName) {
        this.mySoS = simModel;

        this.id = orgId;
        this.name = orgName;

        //If the default state need to be different, modify codes below.
        this.isStatic = true;
        this.isActivated = true;
        this.isAvailable = true;

        msgQueue = new LinkedList<_SimMessage_>();

        printObjInfo();

        initLists();
    }


    public Organization(SoS simModel, String orgId, String orgName, boolean isStatic, boolean isActivated, boolean isAvailable) {
        this.mySoS = simModel;

        this.id = orgId;
        this.name = orgName;

        //If the default state need to be different, modify codes below.
        this.isStatic = isStatic;
        this.isActivated = isActivated;
        this.isAvailable = isAvailable;

        msgQueue = new LinkedList<_SimMessage_>();

        printObjInfo();

        initLists();
    }


    /**
     * Initialization of member lists
     */
    public void initLists() {
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (Organization(" + this.id + "):initLists) Lists are initialized.");

        subOrgList = new ArrayList<>();
        allMemberCSList = new ArrayList<>();
        directCSList = new ArrayList<>();
    }


    /**
     * A method to check if a CS is contained or not
     *
     * @param aCS a CS object to be checked
     * @return true if the CS is contained in this organization
     */
    public boolean isContainedInAllMemberCSList(Constituent aCS) {
        return this.allMemberCSList.contains(aCS);
    }

    /**
     * @param aCS
     * @return
     */
    public boolean isContainedInDirectCSList(Constituent aCS) {
        return this.directCSList.contains(aCS);
    }

    /**
     * @param aCS
     * @return
     */
    public boolean isCSAlreadyContainedInOrg(Constituent aCS) {
        boolean isContained = false;

        if (allMemberCSList.contains(aCS) || directCSList.contains(aCS)) {
            isContained = true;
        }

        return isContained;
    }

    /**
     * @param aCS
     * @return
     */
    public boolean isCSAlreadyContainedInSoS(Constituent aCS) {
        boolean isContained = false;

        for (Constituent cs : mySoS.getCsList()) {
            if (aCS.getId().equals(cs.getId())) {
                isContained = true;
            }
        }

        return isContained;
    }

    /**
     * NOTE: this method inspects multiple sub-organizations
     *
     * @param aCS
     * @return
     */
    public boolean isSubOrgsContainCS(Constituent aCS) {
        for (Organization suborg : this.subOrgList) {
            //If a sub-organization contains this CS as a member
            if (suborg.isContainedInAllMemberCSList(aCS)) {
                return true;
            }
        }
        //TODO: need to check
        return true;
    }

    /*
    Add/Remove objects into the lists of an Org
    (Sub-Org, CS (member, direct))
     */

    public void addSubOrg(Organization subOrg) {
        this.subOrgList.add(subOrg);
        subOrg.setParentOrg(this);

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (Organization(" + this.id + "):addSubOrg) A sub-organization is added (id: " + subOrg.getId() + ").");
    }

    /**
     * Add a suborganization after this organization had been added into SoS
     * (for dynamic reconfiguration of an organization)
     *
     * @param subOrg
     */
    public void addSubOrgDynamically(Organization subOrg) {
        this.subOrgList.add(subOrg);
        subOrg.setParentOrg(this);
        this.getMySoS().addOrg(subOrg);

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (Organization(" + this.id + "):addSubOrg) A sub-organization is added (id: " + subOrg.getId() + ").");
    }

    public void removeSubOrg(Organization subOrg) {
        this.subOrgList.remove(subOrg);

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (Organization(" + this.id + "):removeSubOrg) A sub-organization is removed (id: " + subOrg.getId() + ").");
    }

    /**
     * @param aCS
     * @param isDirect
     */
    public void addCS(Constituent aCS, boolean isDirect) {

        //If aCS isn't contained in this organization
        if (!isCSAlreadyContainedInOrg(aCS)) {

            //Set affiliated SoS (org's SoS = CS's SoS)
            aCS.setMySoS(this.getMySoS());

            this.allMemberCSList.add(aCS);

            //If this CS directly belongs to this
            if (isDirect == true) {
                this.directCSList.add(aCS);

                //Set affiliated organization of CS (this)
                aCS.setMyOrg(this);
            }

            //If this organization has its parent organization
            if (this.getParentOrg() != null) {
                //For parents, aCS is not a direct CS.
                //addCS() is called until top-level organization is reached.
                this.getParentOrg().addCS(aCS, false);
            }
        }

        //If aCS is already contained in this organization
        else {
            timestamp = new Timestamp(System.currentTimeMillis());
            System.err.println("[" + timestamp + "] ADD_CS_TO_ORG_FAILED: A CS to be added already exists in this Org (" + aCS.getId() + " in " + this.id + ").");
        }

    }


//    /**
//     *
//     * @param aCS
//     * @param isDirect
//     */
//    public void addCSDynamically(Constituent aCS, boolean isDirect){
//
//    }
//    public void addCS(Constituent aCS){
//
//        //If a CS is not included in an SoS before adding it into an org.
//        if (!this.getMySoS().csList.contains(aCS)){
//            System.out.println("Not included in the SoS");
//        }
//        //Only if a CS is already registered in the csList of an SoS,
//        //it can be added into the organization
//        else{
//
//        }
//
//        timestamp = new Timestamp(System.currentTimeMillis());
//        System.out.println("[" + timestamp + "] (Organization(" + this.id + "):addCS) addCS is called with a CS (id: " + aCS.getId() + ").");
//
//        //if there is a parent organization
//        if (parentOrg != null){
//            //if parent organization already contains the CS as a member,
//            //a CS is not added.
//            if (!parentOrg.isContainedInAllMemberCSList(aCS)){
//                System.out.println("[" + timestamp + "] (Organization(" + this.id + "):addCS) parentOrg != null && !parentOrg.isContainedInAllMemberCSList(aCS)");
//                return;
//            }
//            //if parent organization contains the CS as a direct member,
//            //the CS is removed from its parent organization
//            if (!parentOrg.isContainedInDirectCSList(aCS)){
//                System.out.println("[" + timestamp + "] (Organization(" + this.id + "):addCS) parentOrg != null && !parentOrg.isContainedInDirectCSList(aCS)");
//                parentOrg.removeCSFromDirectCSList(aCS);
//            }
//        }
//
//        this.allMemberCSList.add(aCS);
//        this.directCSList.add(aCS);
//
//        timestamp = new Timestamp(System.currentTimeMillis());
//        //System.out.println("[" + timestamp + "] (Organization(" + this.id + "):addCS) addCS is called with a CS (id: " + aCS.getId() + ").");
//    }

    /**
     * @param aCS
     */
    public void removeCS(Constituent aCS) {
        timestamp = new Timestamp(System.currentTimeMillis());

        this.allMemberCSList.remove(aCS);

        //If this CS is also contained in the directed CS list,
        //the CS is removed from the DirectCSList
        if (isContainedInDirectCSList(aCS)) {
            this.directCSList.remove(aCS);
        }
        //If this CS is not contained in the directed CS list,
        else {
            //For every sub-organizations,
            for (Organization suborg : this.subOrgList) {
                //If a sub organization contains this CS as a member CS,
                //the CS is also removed from the sub-organization.
                if (suborg.isContainedInAllMemberCSList(aCS)) {
                    suborg.removeCS(aCS);
                }
            }
        }

        //If this organization is not a top-level organization,
        //the parent organization tries to add this CS as a direct CS.
        if (parentOrg != null) {
            parentOrg.tryToAddDirectCS(aCS);
        }
    }

    /**
     * @param aCS
     */
    public void tryToAddDirectCS(Constituent aCS) {
        timestamp = new Timestamp(System.currentTimeMillis());

        if (!this.isSubOrgsContainCS(aCS)) {
            this.addCSToDirectCSList(aCS);
        }
    }


    public void addCSToDirectCSList(Constituent aCS) {
        timestamp = new Timestamp(System.currentTimeMillis());

        this.directCSList.add(aCS);
    }

    public void removeCSFromDirectCSList(Constituent aCS) {
        timestamp = new Timestamp(System.currentTimeMillis());

        this.directCSList.remove(aCS);
    }



    /* Getters & Setters */

    public SoS getMySoS() {
        return mySoS;
    }

    public void setMySoS(SoS mySoS) {
        this.mySoS = mySoS;
    }

    public Organization getParentOrg() {
        return parentOrg;
    }

    public void setParentOrg(Organization parentOrg) {
        this.parentOrg = parentOrg;
    }

    public EnumOrgType getOrgType() {
        return orgType;
    }

    public void setOrgType(EnumOrgType orgType) {
        this.orgType = orgType;
    }

    public ArrayList<Organization> getSubOrgList() {
        return subOrgList;
    }

    public void setSubOrgList(ArrayList<Organization> subOrgList) {
        this.subOrgList = subOrgList;
    }

    public ArrayList<Constituent> getAllMemberCSList() {
        return allMemberCSList;
    }

    public void setAllMemberCSList(ArrayList<Constituent> allMemberCSList) {
        this.allMemberCSList = allMemberCSList;
    }

    public ArrayList<Constituent> getDirectCSList() {
        return directCSList;
    }

    public void setDirectCSList(ArrayList<Constituent> directCSList) {
        this.directCSList = directCSList;
    }

    public int getOrgDepth() {
        return orgDepth;
    }

    public void setOrgDepth(int orgDepth) {
        this.orgDepth = orgDepth;
    }
}
