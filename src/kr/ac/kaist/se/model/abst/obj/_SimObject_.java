package kr.ac.kaist.se.model.abst.obj;

import kr.ac.kaist.se.model.abst.comm._SimMessage_;
import kr.ac.kaist.se.model.intf.Simulatable;

import java.sql.Timestamp;
import java.util.Queue;

/**
 * Abstract class for general simulation objects
 *
 * @author ymbaek
 */
public abstract class _SimObject_ implements Simulatable {

    protected Timestamp timestamp;    //Timestamp for stdout


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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Queue<_SimMessage_> getMsgQueue() {
        return msgQueue;
    }

    public void setMsgQueue(Queue<_SimMessage_> msgQueue) {
        this.msgQueue = msgQueue;
    }
}
