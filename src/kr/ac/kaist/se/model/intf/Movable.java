package kr.ac.kaist.se.model.intf;

import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.sos.geo.ObjectLocation;

import java.util.ArrayList;

/**
 * Interface for objects that can geographically move.
 * Movable objects can execute move(..) by executing a MoveAction(s).
 *
 * @author ymbaek
 */
public interface Movable {

    /**
     * Select a move action(s) among possible move actions
     * @param possibleMoveActions MoveActions that can be executed
     * @return List of selected move actions
     */
    ArrayList<_SimAction_> selectMoveActions(ArrayList<_SimAction_> possibleMoveActions);
}
