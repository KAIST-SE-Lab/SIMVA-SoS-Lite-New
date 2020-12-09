package kr.ac.kaist.se.model.sos.cap;

import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.intf.Movable;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.model.sos.data.DimensionVar;
import kr.ac.kaist.se.model.sos.geo.ObjectLocation;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A concrete class to represent an action for geographical movement.
 *
 * @author ymbaek
 */
public class MoveAction extends _SimAction_ {

    protected ArrayList<DimensionVar> allowedDims = new ArrayList<>();

    protected ArrayList<Integer> dimVarDiffList = new ArrayList<>();


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

    public MoveAction(SoS accessibleSoS, _SimActionableObject_ actionSubject, String actionId, String actionName, ArrayList<DimensionVar> allowedDims, ArrayList<Integer> dimVarDiffList) {
        super(accessibleSoS, actionSubject, actionId, actionName);
        this.allowedDims = allowedDims;
        this.dimVarDiffList = dimVarDiffList;
    }

    public MoveAction(SoS accessibleSoS, _SimActionableObject_ actionSubject, String actionId, String actionName, int actionDuration, float actionCost, float actionBenefit, ArrayList<DimensionVar> allowedDims, ArrayList<Integer> dimVarDiffList) {
        super(accessibleSoS, actionSubject, actionId, actionName, actionDuration, actionCost, actionBenefit);
        this.allowedDims = allowedDims;
        this.dimVarDiffList = dimVarDiffList;
    }

    @Override
    public boolean checkPrecondition() {
        //TODO: Edit checkPrecondition phrase
        return true;
    }

    @Override
    public void executeAction() {

        //TODO: This code is a psuedo way to implement a MoveAction

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.print("[" + timestamp + "] (MoveAction(" + this + "):executeAction) A MoveAction is executed ");

        printAllowedDims();

        //Get current location of actionSubject
        ObjectLocation updatedCurLoc = actionSubject.getCurLocation();

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


        //DimVar -> id same, allowed

//        Integer xDimDiff = Integer.valueOf(1);
//        Integer yDimDiff = Integer.valueOf(2);

        updatedCurLoc.getObjLocDimVars().get(0).updateValueOfDim(1);
        updatedCurLoc.getObjLocDimVars().get(1).updateValueOfDim(+2);

        System.out.print("(updated curLoc: ");
        int index = 0;
        for(DimensionVar dimVar: updatedCurLoc.getObjLocDimVars()){
                System.out.print(dimVar.getDataCurValue());
                if (index++ < updatedCurLoc.getObjLocDimVars().size() - 1) {
                    System.out.print(",");
                }
        }
        System.out.println(")");

        actionSubject.setObjLocation(updatedCurLoc);


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

    public void executeAction(ArrayList<Integer> dimVarDiffList){

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
                allowedDims);
    }

    public ArrayList<DimensionVar> getAllowedDims() {
        return allowedDims;
    }

    public void setAllowedDims(ArrayList<DimensionVar> allowedDims) {
        this.allowedDims = allowedDims;
    }
}
