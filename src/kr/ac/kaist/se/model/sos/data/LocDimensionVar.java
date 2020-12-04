package kr.ac.kaist.se.model.sos.data;

import kr.ac.kaist.se.model.abst.data._SimDataVariable_;

/**
 * Concrete class to define a dimension of a geolocation
 *
 * To enable a MoveAction,
 * increaseValueOfDim(..) and decreaseValueOfDim(..) should be implemented
 * to specify how an object can move according to the definition
 *
 * @author ymbaek
 */
public abstract class LocDimensionVar extends _SimDataVariable_ {

    protected abstract void increaseValueOfDim(int diff);
    protected abstract void decreaseValueOfDim(int diff);

}
