package kr.ac.kaist.se.model.sos.state;

import kr.ac.kaist.se.model.abst.obj._SimObject_;
import kr.ac.kaist.se.model.abst.state._SimState_;

/**
 * A class to represent a state of an object (_SimObject_)
 * @author ymbaek
 */
public class ObjectState extends _SimState_ {

    //subjectObject should be an object who owns this ObjectState
    private _SimObject_ subjectObject;

    public ObjectState(String id, _SimObject_ subjectObject) {
        super(id);
        this.subjectObject = subjectObject;
    }

    public ObjectState(String id, String name, _SimObject_ subjectObject) {
        super(id, name);
        this.subjectObject = subjectObject;
    }

    public ObjectState(String id, String name, String tag, boolean isInitial, _SimObject_ subjectObject) {
        super(id, name, tag, isInitial);
        this.subjectObject = subjectObject;
    }

    public ObjectState(String id, String name, String tag, boolean isInitial, int timeConstraint, _SimObject_ subjectObject) {
        super(id, name, tag, isInitial, timeConstraint);
        this.subjectObject = subjectObject;
    }
}
