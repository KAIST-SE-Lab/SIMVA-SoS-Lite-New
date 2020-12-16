package kr.ac.kaist.se.model.sos.cap;

import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.intf.Movable;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.model.sos.data.DimensionVar;
import kr.ac.kaist.se.model.sos.geo.ObjectLocation;
import kr.ac.kaist.se.simdata.evnt.SimLogEvent;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A concrete class to represent an action for geographical movement.
 *
 * @author ymbaek
 */
public class MoveAction extends _SimAction_ {

    protected int numOfAllowedDims = 0;
    protected ArrayList<DimensionVar> allowedDims = new ArrayList<>();

    protected ArrayList<Integer> dimVarDiffList = new ArrayList<>();


    public MoveAction(SoS accessibleSoS, _SimActionableObject_ actionSubject, String actionId, String actionName, int numOfAllowedDims, ArrayList<DimensionVar> allowedDims, ArrayList<Integer> dimVarDiffList) {
        super(accessibleSoS, actionSubject, actionId, actionName);
        this.numOfAllowedDims = numOfAllowedDims;
        this.allowedDims = allowedDims;
        this.dimVarDiffList = dimVarDiffList;

        printMoveActionCreation();
    }

    public MoveAction(SoS accessibleSoS, _SimActionableObject_ actionSubject, String actionId, String actionName, int actionDuration, float actionCost, float actionBenefit, int numOfAllowedDims, ArrayList<DimensionVar> allowedDims, ArrayList<Integer> dimVarDiffList) {
        super(accessibleSoS, actionSubject, actionId, actionName, actionDuration, actionCost, actionBenefit);
        this.numOfAllowedDims = numOfAllowedDims;
        this.allowedDims = allowedDims;
        this.dimVarDiffList = dimVarDiffList;

        printMoveActionCreation();
    }

    @Override
    public boolean checkPrecondition() {
        //TODO: Edit checkPrecondition phrase
        return true;
    }

    @Override
    public SimLogEvent executeAction() {

//        for (DimensionVar allowedDimVar : allowedDims) {
//            System.out.print(allowedDimVar.getVarId());
//        }
//        System.out.println();

        //TODO: This code is a psuedo way to implement a MoveAction


//        printAllowedDims();

        //Get current location of actionSubject for update
        ObjectLocation updatedCurLoc = actionSubject.getCurLocation().clone();


//        printLocation(actionSubject.getCurLocation());
//        System.out.println();

//        int index = 0;
//        for (DimensionVar curLocDimVar: curLoc.getObjLocDimVars()){
//            System.out.println(curLocDimVar.getVarId());
//            System.out.println(allowedDims.get(index++).getVarId());
////            if (curLocDimVar.getVarId() )
//        }

        //+1, -1
//        while (index < allowedDims.size()){
//            DimensionVar tmpDimVar = curLoc.getObjLocDimVars().get(index);
//
//            System.out.println(tmpDimVar.getVarId() + "(" + tmpDimVar.getDataCurValue() + ") | " + allowedDims.get(index).getVarId());
//            System.out.println(tmpDimVar + " | " + allowedDims.get(index));
//            System.out.println(tmpDimVar.getVarId() == allowedDims.get(index).getVarId());
//
//            curLoc.getObjLocDimVars().get(index).increaseValueOfDim(1);
//
//            tmpDimVar = curLoc.getObjLocDimVars().get(index);
//
//            System.out.println(tmpDimVar.getVarId() + "(" + tmpDimVar.getDataCurValue() + ") | " + allowedDims.get(index).getVarId());
//
//            index++;
//        }


//        printLocation(actionSubject.getCurLocation());
//        System.out.println();
//
//        System.out.println(updatedCurLoc.getObjLocDimVars());
//        System.out.println(actionSubject.getCurLocation().getObjLocDimVars());
//
//        printLocation(actionSubject.getCurLocation());
//        System.out.println();

        //DimVar -> id same, allowed

//        Integer xDimDiff = Integer.valueOf(1);
//        Integer yDimDiff = Integer.valueOf(2);

//        System.out.print("MoveAction: ");
//        for (DimensionVar allowedDimVar : allowedDims) {
//            System.out.print(allowedDimVar.getVarId() + " ");
//        }
//        System.out.println();

        int index = 0;
        ArrayList<Integer> targetDims = new ArrayList<>();
//        System.out.print("MoveAction: ");
        for (DimensionVar objLocDimVar : actionSubject.getCurLocation().getObjLocDimVars()) {
//            System.out.print(objLocDimVar.getVarId() + " ");
            for (DimensionVar allowedDimVar : allowedDims) {
                if (objLocDimVar.getVarId().equals(allowedDimVar.getVarId())) {
//                    System.out.print(index + " ");
                    targetDims.add(Integer.valueOf(index));
                }
            }
            index++;
        }
//        System.out.println();

//        System.out.println("MoveAction: " + targetDims);

        int dimIndex = 0;
        boolean isMovable = true;

        for (Integer targetDimIndex : targetDims){
            int valueDiff = dimVarDiffList.get(dimIndex);
            if(!updatedCurLoc.getObjLocDimVars().get(targetDimIndex).checkUpdateValid(valueDiff)){
                isMovable = false;
            }
            dimIndex++;
        }

        if (isMovable){
            dimIndex = 0;

            for (Integer targetDimIndex : targetDims){
                int valueDiff = dimVarDiffList.get(dimIndex);
                updatedCurLoc.getObjLocDimVars().get(targetDimIndex).updateValueOfDim(valueDiff);

                dimIndex++;
            }

            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.print("[" + timestamp + "] (MoveAction(" + this.getActionId() + "):executeAction) A MoveAction is executed (updatedLoc:");

            printLocation(updatedCurLoc);
            System.out.println(")");

            actionSubject.setObjLocation(updatedCurLoc);

            //TODO: check return
            return null;
        }else{
            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.print("[" + timestamp + "] (MoveAction(" + this.getActionId() + "):executeAction) A MoveAction execution denied (updatedLoc:");

            printLocation(updatedCurLoc);
            System.out.println(")");

            //TODO: check return
            return null;
        }


        //TODO: recover this part

//        int dimIndex = 0;
//        boolean isMoveSuccessful = true;
//        for (DimensionVar allowedDimVar : allowedDims){
//            int valueDiff = dimVarDiffList.get(dimIndex);
//            //If any of dimension does not allow to move
//            if (!updatedCurLoc.getObjLocDimVars().get(dimIndex).checkUpdateValid(valueDiff)){
//                isMoveSuccessful = false;
//            }
//        }

//        //Only if checkUpdateValid results of all dimensions are true
//        if (isMoveSuccessful) {
//            dimIndex = 0;
//            for (DimensionVar allowedDimVar : allowedDims) {
////            System.out.println(allowedDims.size());
//
//                int valueDiff = dimVarDiffList.get(dimIndex);
//                updatedCurLoc.getObjLocDimVars().get(dimIndex).updateValueOfDim(valueDiff);
//                dimIndex++;
//            }
//
//            timestamp = new Timestamp(System.currentTimeMillis());
//            System.out.print("[" + timestamp + "] (MoveAction(" + this.getActionId() + "):executeAction) A MoveAction is executed (updatedLoc:");
//
//            printLocation(updatedCurLoc);
//            System.out.println(")");
//
//            actionSubject.setObjLocation(updatedCurLoc);
//
//            return true;
//        }else{
//            timestamp = new Timestamp(System.currentTimeMillis());
//            System.out.print("[" + timestamp + "] (MoveAction(" + this.getActionId() + "):executeAction) A MoveAction execution denied (updatedLoc:");
//
//            printLocation(updatedCurLoc);
//            System.out.println(")");
//
//            return false;
//        }


//        updatedCurLoc.getObjLocDimVars().get(0).updateValueOfDim(dimVarDiffList.get(0));
//        updatedCurLoc.getObjLocDimVars().get(1).updateValueOfDim(dimVarDiffList.get(1));







//        printLocation(actionSubject.getCurLocation());
//        System.out.print("->");





        //curLoc.updateLocation(new ArrayList<String>(Arrays.asList("DIM_X")));

//        int index = 0;
//        for (DimensionVar locDimVar : curLoc.getObjLocDimVars()){
//            System.out.print(curLoc.getObjLocDimVars().get(index++).getDataCurValue());
//            if (index < curLoc.getObjLocDimVars().size()){
//                System.out.print(",");
//            }
//        }
//        System.out.println(")");


//
//        //Modify locations
//        ArrayList<DimensionVar> tmpLocDims = curLoc.getObjLocDimVars();
//
//        for (DimensionVar locDim: tmpLocDims){
//            locDim.increaseValueOfDim(3);
//        }


        //accessibleSoS.sosMap.mapDimensions
    }

    private void printLocation(ObjectLocation objLocation){
        System.out.print("(");
        int index = 0;
        for(DimensionVar dimVar: objLocation.getObjLocDimVars()){
            System.out.print(dimVar.getDataCurValue());
            if (index++ < objLocation.getObjLocDimVars().size() - 1) {
                System.out.print(",");
            }
        }
        System.out.print(")");
    }



    private void printAllowedDims(){

        System.out.print("(allowedDims: ");
        int index = 0;
        for (DimensionVar dimVar : allowedDims){
            System.out.print(dimVar.getVarId() + "(" + ")");
            if (index++ < allowedDims.size() - 1) {
                System.out.print(",");
            }
        }
        System.out.println(")");
    }


    private void printMoveActionCreation(){
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (MoveAction) A MoveAction is initialized: (" +
                (this instanceof MoveAction) + ", " +
                (this.getClass().getSimpleName()) + ") " +
                accessibleSoS.getId() + " | " +
                actionSubject.getId() + " | " +
                actionId + " | " +
                actionName + " | " +
                allowedDims + "(" + numOfAllowedDims + ") | " +
                dimVarDiffList);
    }

    public ArrayList<DimensionVar> getAllowedDims() {
        return allowedDims;
    }

    public void setAllowedDims(ArrayList<DimensionVar> allowedDims) {
        this.allowedDims = allowedDims;
    }
}
