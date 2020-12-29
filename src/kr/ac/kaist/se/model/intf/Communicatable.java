package kr.ac.kaist.se.model.intf;

import kr.ac.kaist.se.model.abst.comm._SimMessage_;
import kr.ac.kaist.se.model.sos.cap.CommAction;

/**
 * Interface for objects that can communicate with other objects.
 * Communicatable objects can execute sendMsg(..) and readIncomingMsgs(..).
 *
 * @author ymbaek
 */
public interface Communicatable {

    /**
     * Initialization of communication actions
     */
    void initCommActions();

//    //TODO: check return
//    void sendMsg(_SimMessage_ msgObj);

    //TODO: check return

    /**
     * Read incoming messages from its own msgQueue.
     * According to its implementation, messages are differently handled.
     */
    void readIncomingMsgs();

    /**
     * A method to find an id of a receiver object from SoS with a search condition
     * @param condition Search condition
     * @return An id of a receiver object found
     */
    String findReceiverObjIdFromSoS(String condition);

    /**
     * Make a message for a communication action
     * @param aCommAction
     * @return
     */
    _SimMessage_ makeMsgForCommAction(CommAction aCommAction);
}
