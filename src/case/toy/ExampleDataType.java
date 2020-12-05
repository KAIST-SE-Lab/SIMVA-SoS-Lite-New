import kr.ac.kaist.se.model.abst.data._SimDataVariable_;
import kr.ac.kaist.se.model.abst.data._SimDomain_;

public class ExampleDataType extends _SimDataVariable_ {
    public ExampleDataType(String dataId, String dataName, String dataType) {
        super(dataId, dataName, dataType);
    }

    public ExampleDataType(String dataId, String dataName, String dataType, _SimDomain_ dataDomain) {
        super(dataId, dataName, dataType, dataDomain);
    }

    public ExampleDataType(String dataId, String dataName, String dataType, String dataCurValue, _SimDomain_ dataDomain) {
        super(dataId, dataName, dataType, dataCurValue, dataDomain);
    }

    public ExampleDataType(String dataId, String dataName, String dataType, String dataDefaultValue, String dataCurValue, _SimDomain_ dataDomain) {
        super(dataId, dataName, dataType, dataDefaultValue, dataCurValue, dataDomain);
    }
}
