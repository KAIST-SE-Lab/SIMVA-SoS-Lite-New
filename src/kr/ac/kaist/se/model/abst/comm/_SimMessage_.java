package kr.ac.kaist.se.model.abst.comm;

import java.sql.Timestamp;

public abstract class _SimMessage_ {

    protected Timestamp timestamp;    //Timestamp for stdout

    protected String msgId;
    protected String msgTag;
    protected EnumMsgType msgType;

    protected String senderId;
    protected String receiverId;
}
