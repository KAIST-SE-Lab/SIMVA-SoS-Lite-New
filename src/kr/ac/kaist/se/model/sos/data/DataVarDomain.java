package kr.ac.kaist.se.model.sos.data;

import kr.ac.kaist.se.model.abst.data._SimDomain_;

public class DataVarDomain extends _SimDomain_ {

    protected boolean isLogical;
    protected boolean isDiscrete;

    protected String defaultValue;


    public boolean isLogical() {
        return isLogical;
    }

    public void setLogical(boolean logical) {
        isLogical = logical;
    }

    public boolean isDiscrete() {
        return isDiscrete;
    }

    public void setDiscrete(boolean discrete) {
        isDiscrete = discrete;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
