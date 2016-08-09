package com.voigt.hwd.server.util.criteria;

/**
 * @author Roberto Mozzicato
 * @author Infracom IT s.p.a.
 */
public abstract class Criterion implements ICriterion<Object> {

	private final CriterionType type;
	private final Object[] params;

	/**
	 * Costruttore
	 * 
	 * @param propertyName
	 * @param lo
	 * @param hi
	 */
	public Criterion(CriterionType type, Object... params) {
		this.type = type;
		this.params = params;
	}

	public abstract boolean pass(Object oggetto);

	public Object[] getParams() {
		return params;
	}

	public CriterionType getType() {
		return type;
	}

}
