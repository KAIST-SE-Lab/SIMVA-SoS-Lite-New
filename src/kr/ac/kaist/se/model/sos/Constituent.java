package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.model.intf.DecisionMakeable;

/**
 * Abstract class to represent a constituent system, called CS.
 *
 * According to the M2SoS, a Constituent is one and only object that can belong to an organization.
 * Since a SystemEntity extends SystemEntity, it is Actionable, Communicatable.
 * In addition, a Constituent can make its own decisions (i.e., DecisionMakeable).
 *
 * @author ymbaek, ehcho, yjshin
 */
public abstract class Constituent extends SystemEntity implements DecisionMakeable {
}
