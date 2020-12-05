import kr.ac.kaist.se.model.abst.data._SimDataVariable_;
import kr.ac.kaist.se.model.abst.data._SimDomain_;
import kr.ac.kaist.se.model.sos.data.DataVar;
import kr.ac.kaist.se.model.sos.data.DataVarDomain;

public class ExampleDataType extends DataVar {


    public ExampleDataType(String dataId, String dataName, String dataType) {
        super(dataId, dataName, dataType);
    }

    public ExampleDataType(String dataId, String dataName, String dataType, DataVarDomain dataDomain) {
        super(dataId, dataName, dataType, dataDomain);
    }

    public ExampleDataType(String dataId, String dataName, String dataType, String dataCurValue, DataVarDomain dataDomain) {
        super(dataId, dataName, dataType, dataCurValue, dataDomain);
    }

    public ExampleDataType(String dataId, String dataName, String dataType, String dataDefaultValue, String dataCurValue, DataVarDomain dataDomain) {
        super(dataId, dataName, dataType, dataDefaultValue, dataCurValue, dataDomain);
    }
}
