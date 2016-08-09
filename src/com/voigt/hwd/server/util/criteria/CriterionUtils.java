package com.voigt.hwd.server.util.criteria;

import java.util.Collection;

import com.voigt.hwd.client.admin.ds.criteria.GWTCriterion;

/**
 * @author Roberto Mozzicato
 * @author Infracom IT s.p.a.
 */
public class CriterionUtils {
    @SuppressWarnings("unchecked")
    public static Criterion transformCriterion(GWTCriterion criterion) {
	if (criterion == null)
	    return null;

	Object first = null, second = null;
	if (criterion.getParams().length > 0) {
	    first = criterion.getParams()[0];
	    if (criterion.getParams().length > 1) {
		second = criterion.getParams()[1];
	    }
	}

	if (first == null)
	    return null;

	switch (criterion.getType()) {
	/*
	 * case allEq : return Restrictions.allEq((Map)
	 * criterion.getParams()[0]);
	 */
	case not:
	    return Restrictions.not(transformCriterion((GWTCriterion) first));

	case or:
	    if (second != null)
		return Restrictions.or(transformCriterion((GWTCriterion) first),
			transformCriterion((GWTCriterion) second));
	    else
		return transformCriterion((GWTCriterion) first);

	case and:
	    if (second != null)
		return Restrictions.and(transformCriterion((GWTCriterion) first),
			transformCriterion((GWTCriterion) second));
	    else
		return transformCriterion((GWTCriterion) first);

	case between:
	    return Restrictions.between((String) first, second, criterion.getParams()[2]);
	case eq:
	    return Restrictions.eq((String) first, second);
	case ge:
	    return Restrictions.ge((String) first, second);
	case gt:
	    return Restrictions.gt((String) first, second);
	case le:
	    return Restrictions.le((String) first, second);
	case lt:
	    return Restrictions.lt((String) first, second);
	case ne:
	    return Restrictions.ne((String) first, second);
	case isEmpty:
	    return Restrictions.isEmpty((String) first);
	case isNotEmpty:
	    return Restrictions.isNotEmpty((String) first);
	case isNull:
	    return Restrictions.isNull((String) first);
	case isNotNull:
	    return Restrictions.isNotNull((String) first);
	case in:
	    if (second instanceof Collection)
		return Restrictions.in((String) first, (Collection) second);
	    else
		return Restrictions.in((String) first, (Object[]) second);

	    // case aggiunti da A.R. in data 26/02/2009
	case begins:
	    return Restrictions.begins((String) first, second);
	case ends:
	    return Restrictions.ends((String) first, second);
	case contains:
	    return Restrictions.contains((String) first, second);
	case eqField:
	    return Restrictions.eqField((String) first, (String) second);
	case neField:
	    return Restrictions.neField((String) first, (String) second);

	}

	return null;
    }
}
