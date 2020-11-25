package kr.ac.kaist.se.model.abst.obj;

import kr.ac.kaist.se.model.abst.msg._SimMessage_;
import kr.ac.kaist.se.model.intf.Simulatable;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

/**
 * Abstract class for general simulation objects
 *
 * @author ymbaek
 */
public abstract class _SimObject_ implements Simulatable {

    Timestamp timestamp;    //Timestamp for stdout


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
    protected Queue<_SimMessage_> msgQueue;


    /**
     * Print object information of a _SimObject_
     */
    protected void printObjInfo(){
        String thisClassName = this.getClass().getSimpleName();

        if (this.getClass() != null){
            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] (" + thisClassName + ") An object is created.");
            System.out.println("[" + timestamp + "] (" + thisClassName + ") id: " + this.id +
                    ", name: " + this.name +
                    ", isStatic: " + this.isStatic +
                    ", isActivated: " + this.isActivated +
                    ", isAvailable: " + this.isAvailable
            );
        }

    }
}
