package kr.ac.kaist.se.model.abst.obj;

import kr.ac.kaist.se.model.abst.msg._SimMessage_;
import kr.ac.kaist.se.model.intf.Simulatable;

import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

/**
 * Abstract class for general simulation objects
 *
 * @author ymbaek
 */
public abstract class _SimObject_ implements Simulatable {

    /** Universally unique object id */
    protected String id;        //Every SimObject should have its own unique id
    /** Name of an object */
    protected String name;

    /** Static object / Dynamic object */
    protected boolean isStatic;
    /** Activated object / Deactivated object */
    protected boolean isActivated;
    /** Available object / Unavailable object */
    protected boolean isAvailable;

    /** Queue for storing incoming messages from other objects */
    protected Queue<_SimMessage_> msgQueue = new LinkedList<_SimMessage_>();
}
