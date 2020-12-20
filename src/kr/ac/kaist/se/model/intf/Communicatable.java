package kr.ac.kaist.se.model.intf;

import kr.ac.kaist.se.model.abst.comm._SimMessage_;

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
    void readIncomingMsgs();
}
