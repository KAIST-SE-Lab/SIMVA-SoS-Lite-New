package kr.ac.kaist.se.model.abst.data;

import java.util.ArrayList;

/**
 * Abstract class to represent a domain of _SimDataVariable_
 *
 * @author ymbaek
 */
public abstract class _SimDomain_ {
    protected EnumDataDomainType domainType;

    protected boolean isLogical;
    protected boolean isDiscrete;

    protected String defaultValue;

    protected double domainMinVal;
    protected double domainMaxVal;

    protected ArrayList<String> domainEnumVal;
}
