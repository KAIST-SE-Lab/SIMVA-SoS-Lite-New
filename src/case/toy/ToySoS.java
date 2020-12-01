import kr.ac.kaist.se.model.sos.Organization;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.simdata.output.intermediate.RunResult;
import kr.ac.kaist.se.simdata.output.intermediate.UpdateResult;

import java.sql.Timestamp;

public class ToySoS extends SoS {

    public ToySoS(){
        super("TOYSOS01", "ToySoS");
    }

    @Override
    protected void initSoSModel() {
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (ToySoS:initSoSModel) An SoS model is initialized.");

        Org01 org1 = new Org01(this, "ORG01", "Organization 01");
        Org02 org2 = new Org02(this, "ORG02", "Organization 02");

        CS01A cs01a = new CS01A(this, null, "CS01A-001", "Constituent 01-A 001");
        CS01B cs01b = new CS01B(this, null, "CS01B-001", "Constituent 01-B 001");
        CS02A cs02a = new CS02A(this, null, "CS02A-001", "Constituent 02-A 001");

        org1.addSubOrg(org2);

        org1.addCS(cs01a);
        org1.addCS(cs01b);
        org2.addCS(cs02a);

        this.addOrg(org1);
        //this.addOrg(org2);

        this.addCS(cs01a);
        this.addCS(cs01b);
        this.addCS(cs02a);

        printModelInfo();
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
