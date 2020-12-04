package kr.ac.kaist.se.model.sos.cap;

import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.model.sos.data.LocDimensionVar;
import kr.ac.kaist.se.model.sos.geo.ObjectLocation;

/**
 * A concrete class to represent an action for geographical movement.
 *
 * @author ymbaek
 */
public class MoveAction extends _SimAction_ {

    public MoveAction(SoS accessibleSoS,
                         _SimActionableObject_ actionSubject,
                         String actionId,
                         String actionName) {
        super(accessibleSoS, actionSubject, actionId, actionName);
    }

    public MoveAction(SoS accessibleSoS,
                         _SimActionableObject_ actionSubject,
                         String actionId,
                         String actionName,
                         int actionDuration,
                         float actionCost,
                         float actionBenefit) {
        super(accessibleSoS, actionSubject, actionId, actionName, actionDuration, actionCost, actionBenefit);
    }

    @Override
    protected boolean checkPrecondition() {
        return false;
    }

    @Override
    public void executeAction() {

        //TODO: This code is a psuedo way to implement a MoveAction

        ObjectLocation curLoc = new ObjectLocation();

        for (LocDimensionVar dim: curLoc.getObjLocDims()){
            dim.increaseValueOfDim(3);
        }

        //accessibleSoS.sosMap.mapDimensions
    }
}
