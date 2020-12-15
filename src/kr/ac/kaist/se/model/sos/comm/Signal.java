package kr.ac.kaist.se.model.sos.comm;

import kr.ac.kaist.se.model.abst.comm.EnumMsgType;
import kr.ac.kaist.se.model.abst.comm._SimMessage_;
import kr.ac.kaist.se.model.sos.data.DataVar;

import java.util.ArrayList;

public class Signal extends _SimMessage_ {
    public Signal(String msgId, String msgTag, EnumMsgType msgType) {
        super(msgId, msgTag, msgType);
    }

    public Signal(String msgId, String msgTag, EnumMsgType msgType, ArrayList<DataVar> msgDataList) {
        super(msgId, msgTag, msgType, msgDataList);
    }

    public Signal(String msgId, String msgTag, EnumMsgType msgType, String senderId, String receiverId, ArrayList<DataVar> msgDataList) {
        super(msgId, msgTag, msgType, senderId, receiverId, msgDataList);
    }
}
