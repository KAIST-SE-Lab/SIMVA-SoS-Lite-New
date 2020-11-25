package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.model.abst.obj._SimContainerObject_;

/**
 * Abstract class to represent an Environment (Env)
 * (1-tier _SimContainerObject_)
 *
 * According to the Meta-Model for Systems-of-Systems (M2SoS),
 * an environment consists of multiple external EnvElements (passive or active),
 * which interact with constituent objects of an SoS.
 *
 * @author ymbaek, ehcho, yjshin
 */
public abstract class Environment extends _SimContainerObject_ {

    protected SoS mySoS;                //SoS that this environment belongs to

    public Environment(SoS simModel, String envId, String envName){
        this.mySoS = simModel;

        this.id = envId;
        this.name = envName;

        //If the default state need to be different, modify codes below.
        this.isStatic = true;
        this.isActivated = true;
        this.isAvailable = true;

        printObjInfo();
    }

    public Environment(SoS simModel, String envId, String envName, boolean isStatic, boolean isActivated, boolean isAvailable){
        this.mySoS = simModel;

        this.id = envId;
        this.name = envName;

        //If the default state need to be different, modify codes below.
        this.isStatic = isStatic;
        this.isActivated = isActivated;
        this.isAvailable = isAvailable;

        printObjInfo();
    }
}
