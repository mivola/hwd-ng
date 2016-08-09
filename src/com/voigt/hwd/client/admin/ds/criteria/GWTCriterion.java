package com.voigt.hwd.client.admin.ds.criteria;

import java.io.Serializable;

/**
 * Client-side criterion
 * 
 * @author Roberto Mozzicato
 * @author Infracom IT s.p.a.
 * 
 */
public class GWTCriterion implements Serializable {
    private static final long serialVersionUID = -7177622812093114889L;

    private GWTCriterionType type;
    private Serializable[] params;

    public GWTCriterion() {
    }

    /**
     * Costruttore
     * 
     * @param propertyName
     * @param lo
     * @param hi
     */
    public GWTCriterion(GWTCriterionType type, Serializable... params) {
	this.type = type;
	this.params = params;
    }

    public Serializable[] getParams() {
	return params;
    }

    public GWTCriterionType getType() {
	return type;
    }

}
