package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.model.abst.obj._SimNonActionableObject_;
import kr.ac.kaist.se.model.intf.Movable;
import kr.ac.kaist.se.model.intf.Stateful;

public abstract class ResourceEntity extends _SimNonActionableObject_
        implements Stateful, Movable {

    //SoS that this object belongs to
    protected SoS mySoS;
    //Infrastructure that this object belongs to
    protected Infrastructure myInfra;

    public ResourceEntity(SoS mySoS, Infrastructure myInfra, String resId, String resName) {
        this.mySoS = mySoS;
        this.myInfra = myInfra;

        this.id = resId;
        this.name = resName;

        //If the default state need to be different, modify codes below.
        this.isStatic = true;
        this.isActivated = true;
        this.isAvailable = true;

        //A resource is a stateful object
        this.isStateful = true;
    }

    public ResourceEntity(SoS mySoS, Infrastructure myInfra, String resId, String resName, boolean isStatic, boolean isActivated, boolean isAvailable) {
        this.mySoS = mySoS;
        this.myInfra = myInfra;

        this.id = resId;
        this.name = resName;

        this.isStatic = isStatic;
        this.isActivated = isActivated;
        this.isAvailable = isAvailable;

        //A resource is a stateful object
        this.isStateful = true;
    }
}
