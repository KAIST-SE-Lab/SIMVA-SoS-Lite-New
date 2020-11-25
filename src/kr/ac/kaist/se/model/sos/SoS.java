package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.model.abst.obj._SimContainerObject_;
import kr.ac.kaist.se.model.abst.obj._SimObject_;
import kr.ac.kaist.se.simdata.output.intermediate.RunResult;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Abstract class to represent a System-of-Systems (SoS)
 * (0-tier _SimContainerObject_,
 * i.e., an SoS object represents a whole simulation model)
 *
 * According to the Meta-Model for Systems-of-Systems (M2SoS),
 * an SoS is constructed by developing _SimContainerObjects_,
 * which consists of organization(s), infrastructure(s), environment(s),
 * and each _SimContainerObject_ consists of member objects.
 *
 * Interfaces: Simulatable
 *
 * @author ymbaek, ehcho, yjshin
 */
public abstract class SoS extends _SimContainerObject_ {

    /* Member containers */
    protected ArrayList<Organization> orgList;      //List of organizations
    protected ArrayList<Infrastructure> infraList;  //List of infrastructures
    protected ArrayList<Environment> envList;       //List of environments

    /* Member constituents */
    protected ArrayList<Constituent> csList;       //List of all constituent systems

    /* Infrastructure entities */
    protected ArrayList<SystemEntity> systemEntityList;     //List of infra system entities
    //Service
    //Resource

    /* Environment entities */


    public SoS(String sosId, String sosName){
        this.id = sosId;
        this.name = sosName;

        //If the default state need to be different, modify codes below.
        this.isStatic = true;
        this.isActivated = true;
        this.isAvailable = true;

        printObjInfo();
        initLists();
    }


    /**
     * Initialization of member lists
     */
    private void initLists() {
        orgList = new ArrayList<>();
        infraList = new ArrayList<>();
        envList = new ArrayList<>();

        csList = new ArrayList<>();

        systemEntityList = new ArrayList<>();
    }


    /**
     * Method to obtain an object of a member of this SimModel (SoS)
     *
     * @param objId     unique id of object
     * @return          object that has the given id
     */
    public _SimObject_ getMemberSimObject(String objId){
        for (Organization org: orgList){
            if (org.getId().equals(objId)){
                return org;
            }
        }
        for (Infrastructure infra: infraList){
            if (infra.getId().equals(objId)){
                return infra;
            }
        }
        for (Environment env: envList){
            if (env.getId().equals(objId)){
                return env;
            }
        }
        for (Constituent cs: csList){
            if (cs.getId().equals(objId)){
                return cs;
            }
        }
        for (SystemEntity systemEntity: systemEntityList){
            if (systemEntity.getId().equals(objId)){
                return systemEntity;
            }
        }

        //If any object is not found, null is returned.
        return null;
    }


    public RunResult run(){
        //RunResult runResult = new RunResult();

        return null;
    }


}
