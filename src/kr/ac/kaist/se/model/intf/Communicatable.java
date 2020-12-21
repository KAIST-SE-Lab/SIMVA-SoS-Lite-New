package kr.ac.kaist.se.model.intf;

import kr.ac.kaist.se.model.abst.comm._SimMessage_;
import kr.ac.kaist.se.model.sos.cap.CommAction;
import kr.ac.kaist.se.model.sos.comm.Message;

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

    /**
     *
     * @param condition
     * @return
     */
    String findReceiverObjIdFromSoS(String condition);

    _SimMessage_ makeMsgForCommAction(CommAction aCommAction);
}
