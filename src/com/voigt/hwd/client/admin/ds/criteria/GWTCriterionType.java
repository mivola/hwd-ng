// Modificato da Angelo Reina in data 26/02/2009 - aggiunti due operatori sui fields

// Creato il 17/lug/08
package com.voigt.hwd.client.admin.ds.criteria;

import java.io.Serializable;

/**
 * Enum of values for the type of a {@link GWTCriterion}
 * 
 * @author Roberto Mozzicato
 * @author Infracom IT s.p.a.
 * 
 */
public enum GWTCriterionType implements Serializable {
	allEq, //
	and, //
	between, //
	eq, //
	ge, //
	gt, //
	in, //
	isEmpty, //
	isNotEmpty, //
	isNotNull, //
	isNull, //
	le, //
	lt, //
	ne, //
	not, //
	or, //
	begins, //
	ends, //
	contains, //
	eqField, // aggiunto da A.R. Uguaglianza tra field
	neField
	// aggiunto da A.R. Disuguaglianza tra field
}

/*
 * public class CriterionType implements Serializable { private static final
 * long serialVersionUID = 2563643937148135672L;
 * 
 * public static final String allEq="allEq", // and="and", // between="between",
 * // eq="eq", // ge="ge", // gt="gt", // in="in", // isEmpty="isEmpty", //
 * isNotEmpty="isNotEmpty", // isNotNull="isNotNull", // isNull="isNull", //
 * le="le", // lt="lt", // ne="ne", // not="not", // or="or", //
 * begins="begins", // ends="ends", // contains="contains", //
 * eqField="eqField", // aggiunto da A.R. Uguaglianza tra field
 * neField="neField"; // aggiunto da A.R. Disuguaglianza tra field }
 */