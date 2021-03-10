package kr.ac.kaist.se.model.sos.cap;

import kr.ac.kaist.se.controller.util.MapKeyManager;
import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.abst.evnt.EnumEventType;
import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.abst.obj._SimObject_;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.model.sos.data.DimVar;
import kr.ac.kaist.se.model.sos.geo.ObjectLocation;
import kr.ac.kaist.se.simdata.evnt.SimLogEvent;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * A concrete class to represent an action for geographical movement.
 *
 * @author ymbaek
 */
public class MoveAction extends _SimAction_ {

//    private final int numOfAllowedDims;
    private ArrayList<DimVar> allowedDims;

    private ArrayList<String> allowedDimVarIds; //Ids of allowed dimensions
    private ArrayList<Integer> dimVarDiffList;


//    public MoveAction(SoS accessibleSoS, _SimActionableObject_ actionSubject, String actionId, String actionName, int numOfAllowedDims, ArrayList<DimVar> allowedDims, ArrayList<Integer> dimVarDiffList) {
//        super(accessibleSoS, actionSubject, actionId, actionName);
//        this.numOfAllowedDims = numOfAllowedDims;
//        this.allowedDims = allowedDims;
//        this.dimVarDiffList = dimVarDiffList;
//
//        printMoveActionCreation();
//    }
//
//    public MoveAction(SoS accessibleSoS, _SimActionableObject_ actionSubject, String actionId, String actionName, int actionDuration, float actionCost, float actionBenefit, int numOfAllowedDims, ArrayList<DimVar> allowedDims, ArrayList<Integer> dimVarDiffList) {
//        super(accessibleSoS, actionSubject, actionId, actionName, actionDuration, actionCost, actionBenefit);
//        this.numOfAllowedDims = numOfAllowedDims;
//        this.allowedDims = allowedDims;
//        this.dimVarDiffList = dimVarDiffList;
//
//        printMoveActionCreation();
//    }

    public MoveAction(SoS accessibleSoS,
                      _SimObject_ actionSubject,
                      String actionId,
                      String actionName,
                      ArrayList<String> allowedDimVarIds,
                      ArrayList<Integer> dimVarDiffList) {
        super(accessibleSoS, actionSubject, actionId, actionName);
        this.allowedDimVarIds = allowedDimVarIds;
        this.dimVarDiffList = dimVarDiffList;

        printMoveActionCreation();
    }

    @Override
    public boolean checkPrecondition() {
        //TODO: Edit checkPrecondition phrase
        return true;
    }

    @Override
    public ArrayList<SimLogEvent> executeAction(int tick) {

        return null;

        /** GeoMapBuilder ver */

//        //Clear of the actionLogEvents to make new logEvents
//        actionLogEvents.clear();
//
//        //First, check the precondition of this action
//        if (checkPrecondition()){
//            MapKeyManager keyManager = new MapKeyManager(accessibleSoS.getSosMap().getMapLocInfo(),
//                    accessibleSoS.getSosMap().getMapDimVars());
//
//
//            String curLoc = actionSubject.getObjLocation();
////            System.out.println(curLoc);
//
//            int index = 0;
////            ArrayList<Integer> targetDims = new ArrayList<>();
//
//            //EX. (1,0,FLOOR_2) -> [DimVar(xPosVar:1), DimVar(yPosVar:0), DimVar(floorVar:FLOOR_2)]
//
////            ArrayList<DimVar> objLocDimVars = keyManager.getDimVarsFromKey(curLoc);
////            System.out.println(objLocDimVars);
////
////
////            boolean isMovable = true;
////
////            if (isMovable){
////
////            }
//
////            for (DimVar objLocDimVar : getDimValuesFromKey(actionSubject.getObjLocation()))
//
//            return actionLogEvents;
//        }else{
//            return null;
//        }


        /** Deprecated (old ver) */

//        //Clear of the actionLogEvents to make new logEvents
//        actionLogEvents.clear();
//
//        //TODO: This code is a psuedo way to implement a MoveAction
//
////        printAllowedDims();
//
//        //Get current location of actionSubject for update
//        ObjectLocation updatedCurLoc = actionSubject.getCurLocation().clone();
//
//
//        int index = 0;
//        ArrayList<Integer> targetDims = new ArrayList<>();
//        //For all dimension variables of actionSubject's dimensions,
//        //Check if objLocDimVar.getVarId().equals(allowedDimVar.getVarId())
//        for (DimVar objLocDimVar : actionSubject.getCurLocation().getObjLocDimVars()) {
//            for (DimVar allowedDimVar : allowedDims) {
//                if (objLocDimVar.getVarId().equals(allowedDimVar.getVarId())) {
//                    //Set target dimensions
//                    targetDims.add(Integer.valueOf(index));
//                }
//            }
//            index++;
//        }
//
//
//        int dimIndex = 0;
//        boolean isMovable = true;
//
//        //For all target dimensions, check the MoveActions are valid
//        for (Integer targetDimIndex : targetDims) {
//            int valueDiff = dimVarDiffList.get(dimIndex);
//            if (!updatedCurLoc.getObjLocDimVars().get(targetDimIndex).checkUpdateValid(valueDiff)) {
//                isMovable = false;
//            }
//            dimIndex++;
//        }
//
//        //If all dimensions of the MoveAction are valid to move
//        if (isMovable) {
//            dimIndex = 0;
//
//            for (Integer targetDimIndex : targetDims) {
//                int valueDiff = dimVarDiffList.get(dimIndex);
//
//                //Update the location value
//                updatedCurLoc.getObjLocDimVars().get(targetDimIndex).updateValueOfDim(valueDiff);
//
//                dimIndex++;
//            }
//
//            timestamp = new Timestamp(System.currentTimeMillis());
//            System.out.print("[" + timestamp + "] (MoveAction(" + this.getActionId() + "):executeAction) A MoveAction is executed (updatedLoc:");
//            printLocation(updatedCurLoc);
//            System.out.println(")");
//
//            //Generate LogEvent
//            actionLogEvents.add(new SimLogEvent(actionSubject.getLogEventIdAutomatically(this),
//                    EnumEventType.LOCATION_CHANGE,
//                    new Timestamp(System.currentTimeMillis()),
//                    tick,
//                    actionSubject.getId(),
//                    actionSubject,
//                    generateLogEventSpec()));
//
//            actionSubject.setObjLocation(updatedCurLoc);
//
//            //TODO: check return
//            return actionLogEvents;
//        } else {
//            timestamp = new Timestamp(System.currentTimeMillis());
//            System.out.print("[" + timestamp + "] (MoveAction(" + this.getActionId() + "):executeAction) A MoveAction execution denied (updatedLoc:");
//
//            printLocation(updatedCurLoc);
//            System.out.println(")");
//
//            //TODO: check return
//            return null;
//        }

    }


    @Override
    public String generateLogEventSpec() {
        return null;
    }


    /**
     * Print location of an object
     *
     * @param objLocation A given ObjectLocation object
     */
    private void printLocation(ObjectLocation objLocation) {
        System.out.print("(");
        int index = 0;
        for (DimVar dimVar : objLocation.getObjLocDimVars()) {
            System.out.print(dimVar.getDataCurValue());
            if (index++ < objLocation.getObjLocDimVars().size() - 1) {
                System.out.print(",");
            }
        }
        System.out.print(")");
    }


    /**
     * Print allowed dimensions of this move action
     */
    private void printAllowedDims() {

        System.out.print("(allowedDims: ");
        int index = 0;
        for (DimVar dimVar : allowedDims) {
            System.out.print(dimVar.getVarId() + "(" + ")");
            if (index++ < allowedDims.size() - 1) {
                System.out.print(",");
            }
        }
        System.out.println(")");
    }


    /**
     * Print information about the creation of this MoveAction
     */
    private void printMoveActionCreation() {
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (MoveAction) A MoveAction is initialized: (" +
                (this instanceof MoveAction) + ", " +
                (this.getClass().getSimpleName()) + ") " +
                accessibleSoS.getId() + " | " +
                actionSubject.getId() + " | " +
                actionId + " | " +
                actionName + " | " +
                dimVarDiffList);
    }

    public void printMoveActionInfo(){
        System.out.print("[" + actionId + "] ");

        int allowedDimVarIndex = 0;
        for (String dimVarId : allowedDimVarIds){
            System.out.print(dimVarId + "(" + dimVarDiffList.get(allowedDimVarIndex) + ") ");
        }
        System.out.println();
    }

    public ArrayList<DimVar> getAllowedDims() {
        return allowedDims;
    }

    public void setAllowedDims(ArrayList<DimVar> allowedDims) {
        this.allowedDims = allowedDims;
    }
}
