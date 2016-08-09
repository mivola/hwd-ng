// Creato il 17/lug/08
package com.voigt.hwd.server.util.criteria;

/**
 * @author Roberto Mozzicato
 * @author Infracom IT s.p.a.
 */
public enum CriterionType {
    allEq, and, between, eq, ge, gt, in, isEmpty, isNotEmpty, isNotNull, isNull, le, lt, ne, not, or, begins, // aggiunto da A.R.
    ends, // aggiunto da A.R.
    contains, // aggiunto da A.R.
    eqField, // aggiunto da A.R. Uguaglianza tra field
    neField
    // aggiunto da A.R. Disuguaglianza tra field

}
