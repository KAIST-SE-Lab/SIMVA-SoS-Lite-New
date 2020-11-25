package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.intf.Actionable;
import kr.ac.kaist.se.model.intf.Communicatable;

/**
 * Abstract class to represent a system (or system-based entity).
 *
 * According to the M2SoS, a SystemEntity is a base class to represent a constituent (CS).
 * A SystemEntity can perform its own actions (i.e., Actionable),
 * and it can do communication actions (i.e., Communicatable).
 *
 * Interfaces: Simulatable, Actionable, Communicatable
 *
 * @author ymbaek, ehcho, yjshin
 */
public abstract class SystemEntity extends _SimActionableObject_ implements Communicatable {

    protected SoS mySoS;                //SoS that this organization belongs to
}
