import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.sos.Constituent;
import kr.ac.kaist.se.model.sos.Organization;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.simdata.output.intermediate.RunResult;

public class CS01A extends Constituent {


    public CS01A(SoS simModel, Organization myOrg, String csId, String csName) {
        super(simModel, myOrg, csId, csName);
    }

    public CS01A(SoS simModel, Organization myOrg, String csId, String csName, boolean isStatic, boolean isActivated, boolean isAvailable) {
        super(simModel, myOrg, csId, csName, isStatic, isActivated, isAvailable);
    }

    @Override
    protected void selectActions() {

    }

    @Override
    public void doAction(_SimAction_ actionObj) {

    }

    @Override
    public void sendMsg() {

    }

    @Override
    public void readIncomingMsgs() {

    }

    @Override
    public void doDecisionMaking() {

    }

    @Override
    public void move() {

    }

    @Override
    public RunResult run() {
        return null;
    }
}
