package kr.ac.kaist.se.model.intf;

/**
 * Interface for objects that can communicate with other objects.
 * Communicatable objects can execute sendMsg(..) and readIncomingMsgs(..).
 *
 * @author ymbaek
 */
public interface Communicatable {

    //TODO: check return
    void sendMsg();

    //TODO: check return
    void readIncomingMsgs();
}
