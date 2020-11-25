package kr.ac.kaist.se.model.intf;

import kr.ac.kaist.se.model.abst.cap._SimAction_;

/**
 * Interface for actionable objects.
 * Actionable objects can execute doActions(..) method.
 *
 * @author ymbaek
 */
public interface Actionable {

    //TODO: check return
    void doAction(_SimAction_ actionObj);
}
