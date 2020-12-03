import kr.ac.kaist.se.model.sos.EnumOrgType;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.simdata.output.intermediate.RunResult;
import kr.ac.kaist.se.simdata.output.intermediate.UpdateResult;

import java.sql.Timestamp;

public class ToySoS extends SoS {

    public ToySoS() {
        super("TOYSOS01", "ToySoS");
    }

    @Override
    protected void initSoSModel() {
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "]  ===================================================================");
        System.out.println("[" + timestamp + "] (ToySoS:initSoSModel) An SoS model is initialized.");

        ExampleOrgType org1 = new ExampleOrgType(this, "ORG01", "Organization 01");
        org1.setOrgType(EnumOrgType.COLLABORATIVE);
        ExampleOrgType org1A = new ExampleOrgType(this, "ORG01A", "Organization 01-A");
        ExampleOrgType org1B = new ExampleOrgType(this, "ORG01B", "Organization 01-B");
        ExampleOrgType org1AA = new ExampleOrgType(this, "ORG01AA", "Organization 01-A-A");

        ExampleOrgType org2 = new ExampleOrgType(this, "ORG02", "Organization 02");
        org1.setOrgType(EnumOrgType.ACKNOWLEDGED);
        ExampleOrgType org2A = new ExampleOrgType(this, "ORG02A", "Organization 02-A");


        ExampleCSType csA = new ExampleCSType(this, null, "CS0A", "Constituent A");
        ExampleCSType csB = new ExampleCSType(this, null, "CS0B", "Constituent B");
        ExampleCSType csC = new ExampleCSType(this, null, "CS0C", "Constituent C");
        ExampleCSType csD = new ExampleCSType(this, null, "CS0D", "Constituent D");
        ExampleCSType csE = new ExampleCSType(this, null, "CS0E", "Constituent E");
        ExampleCSType csF = new ExampleCSType(this, null, "CS0F", "Constituent F");
        ExampleCSType csG = new ExampleCSType(this, null, "CS0G", "Constituent G");
        ExampleCSType csH = new ExampleCSType(this, null, "CS0H", "Constituent H");

        //independent CS
        ExampleCSType csI = new ExampleCSType(this, null, "CS0I", "Constituent I");



        /* Case 1: Organizational structure -> CS added */

//        org1.addSubOrg(org1A);
//        org1.addSubOrg(org1B);
//        org1A.addSubOrg(org1AA);
//
//        org1.addCS(csA, true);
//        org1.addCS(csB, true);
//        org1.addCS(csB, true); //duplicated
//        org1A.addCS(csC, true);
//        org1B.addCS(csD, true);
//        org1B.addCS(csE, true);
//        org1AA.addCS(csF, true);
//        org1AA.addCS(csG, true);
//
//        org2.addCS(csH, true);
//
//        this.addOrg(org1);
//        this.addOrg(org2);
//
//        this.addCS(csI);    //Direct addition of a CS to SoS


        /* Case 2: CS added -> Organizational structure */

        this.addCS(csA);
        this.addCS(csB);
        this.addCS(csC);
        this.addCS(csD);
        this.addCS(csE);
        this.addCS(csF);
        this.addCS(csG);
        this.addCS(csH);

        org1.addSubOrg(org1A);
        org1.addSubOrg(org1B);
        org1A.addSubOrg(org1AA);

        this.addOrg(org1);
        this.addOrg(org2);

        org1.addCS(csA, true);
        org1.addCS(csB, true);
        org1.addCS(csB, true); //duplicated
        org1A.addCS(csC, true);
        org1B.addCS(csD, true);
        org1B.addCS(csE, true);
        org1AA.addCS(csF, true);
        org1AA.addCS(csG, true);

        org2.addCS(csH, true);

        
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
