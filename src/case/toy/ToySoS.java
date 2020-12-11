import kr.ac.kaist.se.model.abst.data.EnumDomainType;
import kr.ac.kaist.se.model.abst.data._SimDomain_;
import kr.ac.kaist.se.model.sos.EnumOrgType;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.model.sos.data.DataVarDomain;
import kr.ac.kaist.se.model.sos.data.DimensionVar;
import kr.ac.kaist.se.model.sos.data.DataVar;
import kr.ac.kaist.se.simdata.output.intermediate.RunResult;
import kr.ac.kaist.se.simdata.output.intermediate.UpdateResult;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ToySoS extends SoS {

//    //TODO: check 'public static'
//    public ToySoSMap toySoSMap;

    /* Organizations */
    ExampleOrgType org1;
    ExampleOrgType org1A;
    ExampleOrgType org1B;
    ExampleOrgType org1AA;
    ExampleOrgType org2;
    ExampleOrgType org2A;

    /* Constituents */
    ExampleCSType csA;
    ExampleCSType csB;
    ExampleCSType csC;
    ExampleCSType csD;
    ExampleCSType csE;
    ExampleCSType csF;
    ExampleCSType csG;
    ExampleCSType csH;
    ExampleCSType csI; //independent CS

    /* Data */
    ExampleDataType dataA;
    ExampleDataType dataB;
    ExampleDataType dataC;
    ExampleDataType dataD;
    ExampleDataType dataE;

    public ToySoS() {
        super("TOYSOS01", "ToySoS");
    }

    public ToySoS(String sosId, String sosName) {
        super(sosId, sosName);
    }

    @Override
    protected void initSoSModel() {
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "]  ===================================================================");

        initMap();
        initOrgs();
        initCSs();
        initData();


        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "]  -------------------------------------------------------------------");
        System.out.println("[" + timestamp + "] (ToySoS:initSoSModel) An SoS model is initialized.");
        System.out.println("[" + timestamp + "]  ===================================================================");



        /* Case 1: Organizational structure -> CS added */

        org1.addSubOrg(org1A);
//        org1.addSubOrg(org1B);
//        org1A.addSubOrg(org1AA);

        org1.addCS(csA, true);
        org1.addCS(csB, true);
//        org1.addCS(csB, true); //duplicated
        org1A.addCS(csC, true);
//        org1B.addCS(csD, true);
//        org1B.addCS(csE, true);
//        org1AA.addCS(csF, true);
//        org1AA.addCS(csG, true);

//        org2.addCS(csH, true);

        this.addOrg(org1);
//        this.addOrg(org2);

//        this.addCS(csI);    //Direct addition of a CS to SoS


        /* Case 2: CS added -> Organizational structure */

//        this.addCS(csA);
//        this.addCS(csB);
//        this.addCS(csC);
//        this.addCS(csD);
//        this.addCS(csE);
//        this.addCS(csF);
//        this.addCS(csG);
//        this.addCS(csH);
//
//        org1.addSubOrg(org1A);
//        org1.addSubOrg(org1B);
//        org1A.addSubOrg(org1AA);
//
//        this.addOrg(org1);
//        this.addOrg(org2);
//
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


        printModelInfo();
    }

    protected void initObjLocation() {

    }

    /**
     * Initialization of organizations
     */
    private void initOrgs() {
        org1 = new ExampleOrgType(this, "ORG01", "Organization 01");
        org1A = new ExampleOrgType(this, "ORG01A", "Organization 01-A");
//        org1B = new ExampleOrgType(this, "ORG01B", "Organization 01-B");
//        org1AA = new ExampleOrgType(this, "ORG01AA", "Organization 01-A-A");
//        org2 = new ExampleOrgType(this, "ORG02", "Organization 02");
//        org2A = new ExampleOrgType(this, "ORG02A", "Organization 02-A");

        org1.setOrgType(EnumOrgType.COLLABORATIVE);
//        org2.setOrgType(EnumOrgType.ACKNOWLEDGED);
    }

    /**
     * Initialization of constituents
     */
    private void initCSs() {
        csA = new ExampleCSType(this, null, "CS0A", "Constituent A");
        csB = new ExampleCSType(this, null, "CS0B", "Constituent B");
        csC = new ExampleCSType(this, null, "CS0C", "Constituent C");
//        csD = new ExampleCSType(this, null, "CS0D", "Constituent D");
//        csE = new ExampleCSType(this, null, "CS0E", "Constituent E");
//        csF = new ExampleCSType(this, null, "CS0F", "Constituent F");
//        csG = new ExampleCSType(this, null, "CS0G", "Constituent G");
//        csH = new ExampleCSType(this, null, "CS0H", "Constituent H");
//
//        //independent CS
//        csI = new ExampleCSType(this, null, "CS0I", "Constituent I");
    }


    /**
     * Initialization of data
     */
    private void initData() {
//        ExampleDataDomain exampleDataDomainForDataA = new ExampleDataDomain();
//        dataA = new ExampleDataType("DATA0A", "Data A", "String", exampleDataDomainForDataA);
//
//        ExampleDataDomain exampleDataDomainForDataB = new ExampleDataDomain();
//        dataB = new ExampleDataType("DATA0B", "Data B", "String", "DEFAULT-STRING", exampleDataDomainForDataB);
//
//        ExampleDataDomain exampleDataDomainForDataC = new ExampleDataDomain();
//        dataC = new ExampleDataType("DATA0C", "Data C", "Int", "0", exampleDataDomainForDataC);
//
//        ExampleDataDomain exampleDataDomainForDataD = new ExampleDataDomain();
//        dataD = new ExampleDataType("DATA0D", "Data D", "Float", "-1", "0", exampleDataDomainForDataD);
//
//        ExampleDataDomain exampleDataDomainForDataE = new ExampleDataDomain();
//        dataE = new ExampleDataType("DATA0E", "Data E", "String", "DEFAULT-STRING", "CUR-STRING", exampleDataDomainForDataE);
    }


    /**
     * Initialization of a map
     */
    @Override
    protected void initMap() {

        //TODO: Add domains for each informationVar
//        DataVar isAccessibleInfo = new DataVar();
//        DataVar isChargingStation = new DataVar();
//
//        locInfos.add(isAccessibleInfo);
//        locInfos.add(isChargingStation);

        ToySoSMap toySoSMap = new ToySoSMap("MAP01", "Toy SoS Map 01");

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (ToySoS:initMap) An SoSMap is initialized.");

        sosMap = toySoSMap;
    }


//    @Override
//    public RunResult run() {
//        return null;
//    }

//    @Override
//    public UpdateResult update(RunResult runResult) {
//        return null;
//    }


    class ExampleDataDomain extends DataVarDomain {

        public ExampleDataDomain(EnumDomainType domainType, boolean isLogical, boolean isDiscrete, String defaultValue) {
            super(domainType, isLogical, isDiscrete, defaultValue);
        }

        public ExampleDataDomain(EnumDomainType domainType, float domainMinVal, float domainMaxVal, boolean isLogical, boolean isDiscrete, String defaultValue) {
            super(domainType, domainMinVal, domainMaxVal, isLogical, isDiscrete, defaultValue);
        }

        public ExampleDataDomain(EnumDomainType domainType, ArrayList<String> domainEnumVal, boolean isLogical, boolean isDiscrete, String defaultValue) {
            super(domainType, domainEnumVal, isLogical, isDiscrete, defaultValue);
        }
    }
}
