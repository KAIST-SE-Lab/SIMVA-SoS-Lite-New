package kr.ac.kaist.se.model.intf;

import kr.ac.kaist.se.model.sos.geo.ObjectLocation;

import java.util.ArrayList;

/**
 * Interface for objects that can geographically move.
 * Movable objects can execute move(..) by executing a MoveAction(s).
 *
 * @author ymbaek
 */
public interface Movable {


    //TODO: check return
    void move();
}
