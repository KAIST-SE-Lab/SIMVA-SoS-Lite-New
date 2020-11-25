package kr.ac.kaist.se.model.abst.obj;

import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.simdata.output.intermediate.RunResult;
import kr.ac.kaist.se.simdata.output.intermediate.UpdateResult;

/**
 * Abstract class for an object that cannot execute its own behaviors
 *
 * @author ymbaek
 */
public abstract class _SimNonActionableObject_ extends _SimObject_{

    protected String referenceId;

    /**
     *
     * @param runResult
     * @return
     */
    public UpdateResult update(RunResult runResult){

        //TODO: Add proper information into UpdateResult
        UpdateResult updateResult = new UpdateResult();

        return updateResult;
    }
}
