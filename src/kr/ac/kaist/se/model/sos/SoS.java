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
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (SoS) Lists are initialized.");

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



    /**
     * Check duplicate Ids of _SimObjects_ of an SoS
     *
     * @param objId an object's id to be checked
     * @return true if the given id is duplicate (i.e., it must be false if you want to add an object into a list)
     */
    public boolean isDuplicateId(String objId){
        if (getMemberSimObject(objId) != null){
            return true;    //There is a duplicate id
        }else{
            return false;   //There is no duplicate id
        }
    }


    /*
    Add/Remove objects into the lists of an SoS
    (Org, Infra, Env, CS, etc.)
     */

    public void addOrg(Organization aOrg){
        timestamp = new Timestamp(System.currentTimeMillis());

        if(!isDuplicateId(aOrg.getId()) && orgList.add(aOrg) == true){
            System.out.println("[" + timestamp + "] (SoS:addOrg) An Organization object is successfully added (" + aOrg.getId() + ").");
        }else{
            System.out.println("[" + timestamp + "] (SoS:addOrg) An Organization object is failed to be added.");
            System.out.println("[" + timestamp + "] (SoS:addOrg) aOrg(id): " + aOrg + "(" + aOrg.getId() +"), isDuplicateId: " + isDuplicateId(aOrg.getId()));
        }
    }

    public void removeOrg(Organization aOrg){
        if(orgList.remove(aOrg) == true){
            System.out.println("[" + timestamp + "] (SoS:removeOrg) An Organization object is successfully removed (" + aOrg.getId() + ").");
        }else{
            System.out.println("[" + timestamp + "] (SoS:removeOrg) An Organization object is failed to be removed.");

        }
    }

    public void addInfra(Infrastructure aInfra){
        if(!isDuplicateId(aInfra.getId()) && infraList.add(aInfra) == true){
            System.out.println("[" + timestamp + "] (SoS:addInfra) An Infrastructure object is successfully added (" + aInfra.getId() + ").");
        }else{
            System.out.println("[" + timestamp + "] (SoS:addInfra) An Infrastructure object is failed to be added.");
            System.out.println("[" + timestamp + "] (SoS:addInfra) aInfra(id): " + aInfra + "(" + aInfra.getId() +"), isDuplicateId: " + isDuplicateId(aInfra.getId()));
        }
    }

    public void removeInfra(Infrastructure aInfra){
        if(infraList.remove(aInfra) == true){
            System.out.println("[" + timestamp + "] (SoS:removeInfra) An Infrastructure object is successfully removed (" + aInfra.getId() + ").");
        }else{
            System.out.println("[" + timestamp + "] (SoS:removeInfra) An Infrastructure object is failed to be removed.");

        }
    }

    public void addEnv(Environment aEnv){
        if(!isDuplicateId(aEnv.getId()) && envList.add(aEnv) == true){
            System.out.println("[" + timestamp + "] (SoS:addEnv) An Environment object is successfully added (" + aEnv.getId() + ").");
        }else{
            System.out.println("[" + timestamp + "] (SoS:addEnv) An Environment object is failed to be added.");
            System.out.println("[" + timestamp + "] (SoS:addEnv) aEnv(id): " + aEnv + "(" + aEnv.getId() +"), isDuplicateId: " + isDuplicateId(aEnv.getId()));
        }
    }

    public void removeEnv(Environment aEnv){
        if(envList.remove(aEnv) == true){
            System.out.println("[" + timestamp + "] (SoS:removeEnv) An Environment object is successfully removed (" + aEnv.getId() + ").");
        }else{
            System.out.println("[" + timestamp + "] (SoS:removeEnv) An Environment object is failed to be removed.");

        }
    }

    public void addCS(Constituent aCS){
        if(!isDuplicateId(aCS.getId()) && csList.add(aCS) == true){
            System.out.println("[" + timestamp + "] (SoS:addCS) An Constituent object is successfully added (" + aCS.getId() + ").");
        }else{
            System.out.println("[" + timestamp + "] (SoS:addCS) An Constituent object is failed to be added.");
            System.out.println("[" + timestamp + "] (SoS:addCS) aCS(id): " + aCS + "(" + aCS.getId() +"), isDuplicateId: " + isDuplicateId(aCS.getId()));
        }
    }

    public void removeCS(Constituent aCS){
        if(csList.remove(aCS) == true){
            System.out.println("[" + timestamp + "] (SoS:removeCS) An Constituent object is successfully removed (" + aCS.getId() + ").");
        }else{
            System.out.println("[" + timestamp + "] (SoS:removeCS) An Constituent object is failed to be removed.");
        }
    }

    public void addSystemEntity(SystemEntity aSystemEntity){
        if(!isDuplicateId(aSystemEntity.getId()) && systemEntityList.add(aSystemEntity) == true){
            System.out.println("[" + timestamp + "] (SoS:addCS) An SystemEntity object is successfully added (" + aSystemEntity.getId() + ").");
        }else{
            System.out.println("[" + timestamp + "] (SoS:addCS) An SystemEntity object is failed to be added.");
            System.out.println("[" + timestamp + "] (SoS:addCS) aSystemEntity(id): " + aSystemEntity + "(" + aSystemEntity.getId() +"), isDuplicateId: " + isDuplicateId(aSystemEntity.getId()));
        }
    }

    public void removeSystemEntity(SystemEntity aSystemEntity){
        if(systemEntityList.remove(aSystemEntity) == true){
            System.out.println("[" + timestamp + "] (SoS:removeCS) An SystemEntity object is successfully removed (" + aSystemEntity.getId() + ").");
        }else{
            System.out.println("[" + timestamp + "] (SoS:removeCS) An SystemEntity object is failed to be removed.");
        }
    }




}
