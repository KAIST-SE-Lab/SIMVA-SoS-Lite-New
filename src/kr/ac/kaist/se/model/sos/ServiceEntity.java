package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.intf.Communicatable;

public abstract class ServiceEntity extends _SimActionableObject_
        implements Communicatable {

    //SoS that this object belongs to
    protected SoS mySoS;
    //Infrastructure that this object belongs to
    protected Infrastructure myInfra;
}
