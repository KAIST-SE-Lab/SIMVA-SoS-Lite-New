package kr.ac.kaist.se.model.abst.state;

import java.util.ArrayList;

/**
 * Abstract class to represent a state of a SimObject
 *
 * @author ymbaek
 */
public abstract class _SimState_ {
    protected String id;
    protected String name;
    protected String tag;

    protected boolean isInitial;

    protected int timeConstraint;

    protected ArrayList<_SimState_> nextStates = new ArrayList<>();

}
