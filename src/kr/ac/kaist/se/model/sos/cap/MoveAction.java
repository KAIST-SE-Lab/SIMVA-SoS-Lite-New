package kr.ac.kaist.se.model.sos.cap;

import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.model.sos.data.DimensionVar;
import kr.ac.kaist.se.model.sos.geo.ObjectLocation;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * A concrete class to represent an action for geographical movement.
 *
 * @author ymbaek
 */
public class MoveAction extends _SimAction_ {

    protected ArrayList<DimensionVar> allowedDims = new ArrayList<>();

    public MoveAction(SoS accessibleSoS, _SimActionableObject_ actionSubject, String actionId, String actionName, ArrayList<DimensionVar> allowedDims) {
        super(accessibleSoS, actionSubject, actionId, actionName);
        this.allowedDims = allowedDims;

        printMoveActionCreation();
    }

    public MoveAction(SoS accessibleSoS, _SimActionableObject_ actionSubject, String actionId, String actionName, int actionDuration, float actionCost, float actionBenefit, ArrayList<DimensionVar> allowedDims) {
        super(accessibleSoS, actionSubject, actionId, actionName, actionDuration, actionCost, actionBenefit);
        this.allowedDims = allowedDims;

        printMoveActionCreation();
    }

    @Override
    protected boolean checkPrecondition() {
        return false;
    }

    @Override
    public void executeAction() {

        //TODO: This code is a psuedo way to implement a MoveAction

        //Get current location of actionSubject
        ObjectLocation curLoc = actionSubject.getCurLocation();

        //Modify locations
        ArrayList<DimensionVar> tmpLocDims = curLoc.getObjLocDimVars();

        for (DimensionVar locDim: tmpLocDims){
            locDim.increaseValueOfDim(3);
        }


        //accessibleSoS.sosMap.mapDimensions
    }

    private void printMoveActionCreation(){
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (MoveAction) A MoveAction is initialized: " +
                accessibleSoS.getId() + " | " +
                actionSubject.getId() + " | " +
                actionId + " | " +
                actionName + " | " +
                allowedDims);
    }
}
