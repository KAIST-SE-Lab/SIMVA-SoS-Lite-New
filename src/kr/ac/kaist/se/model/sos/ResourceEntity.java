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
}
