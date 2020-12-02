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

        ExampleOrgType org1 = new ExampleOrgType(this, "ORG01", "Organization 01");
        ExampleOrgType org1A = new ExampleOrgType(this, "ORG01A", "Organization 01-A");
        ExampleOrgType org1B = new ExampleOrgType(this, "ORG01B", "Organization 01-B");
        ExampleOrgType org1AA = new ExampleOrgType(this, "ORG01AA", "Organization 01-A-A");

        ExampleCSType cs01a = new ExampleCSType(this, null, "CS01A-001", "Constituent 01-A 001");
        ExampleCSType cs01b = new ExampleCSType(this, null, "CS01B-001", "Constituent 01-B 001");
        ExampleCSType cs02a = new ExampleCSType(this, null, "CS02A-001", "Constituent 02-A 001");


        /* Case 1: Organizational structure -> CS added */

        org1.addSubOrg(org1A);
        org1.addSubOrg(org1B);
        org1A.addSubOrg(org1AA);

        this.addOrg(org1);

        /* Case 2: CS added -> Organizational structure */

        // org
        // cs
        // org

//        org1.addSubOrg(org1A);
//        org1.addSubOrg(org1B);
//
//        org1.addCS(cs01a);
//        org1.addCS(cs01b);
//        org1A.addCS(cs02a);
//
//        this.addOrg(org1);
//        //this.addOrg(org2);
//
//        this.addCS(cs01a);
//        this.addCS(cs01b);
//        this.addCS(cs02a);

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
