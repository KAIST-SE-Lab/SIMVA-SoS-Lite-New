package kr.ac.kaist.se.model.abst.comm;

import kr.ac.kaist.se.model.intf.Transmittable;
import kr.ac.kaist.se.model.sos.data.DataVar;

import java.sql.Timestamp;
import java.util.ArrayList;

public abstract class _SimMessage_ implements Transmittable{

    protected Timestamp timestamp;    //Timestamp for stdout

    protected String msgId;
    protected String msgTag;
    protected EnumMsgType msgType;

    protected String senderId;
    protected String receiverId;

    protected ArrayList<DataVar> msgDataList = new ArrayList<>();

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgTag() {
        return msgTag;
    }

    public void setMsgTag(String msgTag) {
        this.msgTag = msgTag;
    }

    public EnumMsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(EnumMsgType msgType) {
        this.msgType = msgType;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public ArrayList<DataVar> getMsgDataList() {
        return msgDataList;
    }

    public void setMsgDataList(ArrayList<DataVar> msgDataList) {
        this.msgDataList = msgDataList;
    }
}
