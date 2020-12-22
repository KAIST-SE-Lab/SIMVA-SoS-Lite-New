import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.sos.Organization;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.model.sos.geo.ObjectLocation;
import kr.ac.kaist.se.simdata.output.intermediate.RunResult;
import kr.ac.kaist.se.simdata.output.intermediate.UpdateResult;

import java.util.ArrayList;

public class ExampleOrgType extends Organization {
    public ExampleOrgType(SoS simModel, String orgId, String orgName) {
        super(simModel, orgId, orgName);
    }

    public ExampleOrgType(SoS simModel, String orgId, String orgName, boolean isStatic, boolean isActivated, boolean isAvailable) {
        super(simModel, orgId, orgName, isStatic, isActivated, isAvailable);
    }


    protected void initObjLocation() {

    }

    @Override
    public ArrayList<_SimAction_> selectMoveActions(ArrayList<_SimAction_> possibleMoveActions) {
        return null;
    }


//    @Override
//    public UpdateResult update(RunResult runResult) {
//        return null;
//    }
}
