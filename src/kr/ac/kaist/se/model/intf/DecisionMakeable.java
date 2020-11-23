package kr.ac.kaist.se.model.intf;

/**
 * Interface for SimObjects that can make own decisions.
 * DecisionMAkeable objects can execute doDecisionMaking(..).
 *
 * @author ymbaek
 */
public interface DecisionMakeable {

    //TODO: check return
    void doDecisionMaking();
}
