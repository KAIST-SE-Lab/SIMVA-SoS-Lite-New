package kr.ac.kaist.se.model.intf;

import kr.ac.kaist.se.model.sos.geo.ObjectLocation;

/**
 * Interface for objects that can geographically move.
 * Movable objects can execute move(..) by executing a MoveAction(s).
 *
 * @author ymbaek
 */
public interface Movable {

    //TODO: check return
    ObjectLocation getCurLocation();

    //TODO: check return
    void move();
}
