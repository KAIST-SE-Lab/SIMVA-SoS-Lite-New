import kr.ac.kaist.se.model.sos.Organization;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.simdata.output.intermediate.RunResult;
import kr.ac.kaist.se.simdata.output.intermediate.UpdateResult;

public class Org01 extends Organization {
    public Org01(SoS simModel, String orgId, String orgName) {
        super(simModel, orgId, orgName);
    }

    public Org01(SoS simModel, String orgId, String orgName, boolean isStatic, boolean isActivated, boolean isAvailable) {
        super(simModel, orgId, orgName, isStatic, isActivated, isAvailable);
    }

    @Override
    public void move() {

    }

    @Override
    public RunResult run() {
        return null;
    }

    @Override
    public UpdateResult update(RunResult runResult) {
        return null;
    }
}
