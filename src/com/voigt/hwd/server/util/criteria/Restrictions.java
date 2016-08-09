package com.voigt.hwd.server.util.criteria;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.voigt.hwd.server.util.ReflUtils;

/**
 * @author Roberto Mozzicato
 * @author Infracom IT s.p.a.
 * 
 */
public class Restrictions {

    @SuppressWarnings("unchecked")
    public static Criterion allEq(final Map propertyNameValues) {
	return new Criterion(CriterionType.allEq, propertyNameValues) {
	    @Override
	    public boolean pass(Object oggetto) {
		if (propertyNameValues == null)
		    return false;

		Set<String> keySet = propertyNameValues.keySet();

		for (String key : keySet) {
		    if (!ReflUtils.getValue(oggetto, key).equals(propertyNameValues.get(key)))
			return false;
		}
		return true;
	    }
	};
    }

    public static Criterion and(final Criterion lhs, final Criterion rhs) {
	return new Criterion(CriterionType.and, lhs, rhs) {
	    @Override
	    public boolean pass(Object oggetto) {
		return lhs.pass(oggetto) && rhs.pass(oggetto);
	    }
	};
    }

    public static Criterion or(final Criterion lhs, final Criterion rhs) {
	return new Criterion(CriterionType.or, lhs, rhs) {
	    @Override
	    public boolean pass(Object oggetto) {
		return lhs.pass(oggetto) || rhs.pass(oggetto);
	    }
	};
    }

    public static Criterion not(final Criterion expression) {
	return new Criterion(CriterionType.not, expression) {
	    @Override
	    public boolean pass(Object oggetto) {
		return !expression.pass(oggetto);
	    }
	};
    }

    public static Criterion between(final String propertyName, final Object lo, final Object hi) {
	return new Criterion(CriterionType.between, propertyName, lo, hi) {
	    @Override
	    @SuppressWarnings("unchecked")
	    public boolean pass(Object oggetto) {
		if (lo != null && hi != null) {
		    Object objVal = ReflUtils.getValue(oggetto, propertyName);
		    if (objVal instanceof Comparable)
			return ((Comparable) objVal).compareTo(lo) >= 0 && ((Comparable) objVal).compareTo(hi) < 0;
		}

		return false;
	    }
	};
    }

    public static Criterion eq(final String propertyName, final Object value) {
	return new Criterion(CriterionType.eq, propertyName, value) {
	    @Override
	    public boolean pass(Object oggetto) {
		return ReflUtils.getValue(oggetto, propertyName).equals(value);
	    }
	};
    }

    public static Criterion ge(final String propertyName, final Object value) {
	return new Criterion(CriterionType.ge, propertyName, value) {
	    @Override
	    @SuppressWarnings("unchecked")
	    public boolean pass(Object oggetto) {
		if (value != null) {
		    Object objVal = ReflUtils.getValue(oggetto, propertyName);
		    if (objVal instanceof Comparable)
			return ((Comparable) objVal).compareTo(value) >= 0;
		}

		return false;
	    }
	};
    }

    public static Criterion gt(final String propertyName, final Object value) {
	return new Criterion(CriterionType.gt, propertyName, value) {
	    @Override
	    @SuppressWarnings("unchecked")
	    public boolean pass(Object oggetto) {
		if (value != null) {
		    Object objVal = ReflUtils.getValue(oggetto, propertyName);
		    if (objVal instanceof Comparable)
			return ((Comparable) objVal).compareTo(value) > 0;
		}

		return false;
	    }
	};
    }

    @SuppressWarnings("unchecked")
    public static Criterion in(final String propertyName, final Collection values) {
	return new Criterion(CriterionType.in, propertyName, values) {
	    @Override
	    public boolean pass(final Object oggetto) {
		if (values != null) {
		    Object val = ReflUtils.getValue(oggetto, propertyName);
		    for (Object tmp : values) {
			if (tmp.equals(val))
			    return true;
		    }
		}

		return false;
	    }
	};
    }

    public static Criterion in(final String propertyName, final Object[] values) {
	return new Criterion(CriterionType.in, propertyName, values) {
	    @Override
	    public boolean pass(final Object oggetto) {
		if (values != null) {
		    Object val = ReflUtils.getValue(oggetto, propertyName);
		    for (Object tmp : values) {
			if (tmp.equals(val))
			    return true;
		    }
		}

		return false;
	    }
	};
    }

    public static Criterion isEmpty(final String propertyName) {
	return new Criterion(CriterionType.isEmpty, propertyName) {
	    @Override
	    public boolean pass(final Object oggetto) {
		Object val = ReflUtils.getValue(oggetto, propertyName);
		if (val != null && val.toString().length() == 0)
		    return true;

		return false;
	    }
	};
    }

    public static Criterion isNotEmpty(final String propertyName) {
	return new Criterion(CriterionType.isNotEmpty, propertyName) {
	    @Override
	    public boolean pass(final Object oggetto) {
		Object val = ReflUtils.getValue(oggetto, propertyName);
		if (val != null && val.toString().length() > 0)
		    return true;

		return false;
	    }
	};
    }

    public static Criterion isNotNull(final String propertyName) {
	return new Criterion(CriterionType.isNotNull, propertyName) {
	    @Override
	    public boolean pass(final Object oggetto) {
		Object val = ReflUtils.getValue(oggetto, propertyName);
		if (val != null)
		    return true;

		return false;
	    }
	};
    }

    public static Criterion isNull(final String propertyName) {
	return new Criterion(CriterionType.isNull, propertyName) {
	    @Override
	    public boolean pass(final Object oggetto) {
		Object val = ReflUtils.getValue(oggetto, propertyName);
		if (val == null)
		    return true;

		return false;
	    }
	};
    }

    public static Criterion le(final String propertyName, final Object value) {
	return new Criterion(CriterionType.le, propertyName) {
	    @Override
	    @SuppressWarnings("unchecked")
	    public boolean pass(Object oggetto) {
		if (value != null) {
		    Object objVal = ReflUtils.getValue(oggetto, propertyName);
		    if (objVal instanceof Comparable)
			return ((Comparable) objVal).compareTo(value) <= 0;
		}

		return false;
	    }
	};
    }

    public static Criterion lt(final String propertyName, final Object value) {
	return new Criterion(CriterionType.lt, propertyName, value) {
	    @Override
	    @SuppressWarnings("unchecked")
	    public boolean pass(Object oggetto) {
		if (value != null) {
		    Object objVal = ReflUtils.getValue(oggetto, propertyName);
		    if (objVal instanceof Comparable)
			return ((Comparable) objVal).compareTo(value) < 0;
		}

		return false;
	    }
	};
    }

    public static Criterion ne(final String propertyName, final Object value) {
	return new Criterion(CriterionType.ne, propertyName, value) {
	    @Override
	    public boolean pass(Object oggetto) {
		return !ReflUtils.getValue(oggetto, propertyName).equals(value);
	    }
	};
    }

    // Aggiunto da Angelo Reina in data 26/02/2009
    public static Criterion begins(final String propertyName, final Object value) {
	return new Criterion(CriterionType.begins, propertyName, value) {
	    @Override
	    public boolean pass(Object oggetto) {
		Object val = ReflUtils.getValue(oggetto, propertyName);

		if (val != null && val instanceof String) {
		    return val.toString().startsWith((String) value);
		} else
		    return false;
	    }
	};
    }

    // Aggiunto da Angelo Reina in data 26/02/2009
    public static Criterion ends(final String propertyName, final Object value) {
	return new Criterion(CriterionType.ends, propertyName, value) {
	    @Override
	    public boolean pass(Object oggetto) {
		Object val = ReflUtils.getValue(oggetto, propertyName);

		if (val != null && val instanceof String) {
		    return val.toString().endsWith((String) value);
		} else
		    return false;
	    }
	};
    }

    // Aggiunto da Angelo Reina in data 26/02/2009
    public static Criterion contains(final String propertyName, final Object value) {
	return new Criterion(CriterionType.contains, propertyName, value) {
	    @Override
	    public boolean pass(Object oggetto) {
		Object val = ReflUtils.getValue(oggetto, propertyName);

		if (val != null && val instanceof String) {
		    return val.toString().indexOf((String) value) >= 0;
		} else
		    return false;
	    }
	};
    }

    // Aggiunto da Angelo Reina in data 26/02/2009
    public static Criterion eqField(final String propertyName, final Object otherPropertyName) {
	return new Criterion(CriterionType.eqField, propertyName, otherPropertyName) {
	    @Override
	    public boolean pass(Object oggetto) {
		Object val1 = ReflUtils.getValue(oggetto, propertyName);
		Object val2 = ReflUtils.getValue(oggetto, (String) otherPropertyName);

		if (val1 != null) {
		    return val1.equals(val2);
		} else if (val2 != null) {
		    return val2.equals(val1);
		} else
		    return true;
	    }
	};
    }

    // Aggiunto da Angelo Reina in data 26/02/2009
    public static Criterion neField(final String propertyName, final Object otherPropertyName) {
	return new Criterion(CriterionType.neField, propertyName, otherPropertyName) {
	    @Override
	    public boolean pass(Object oggetto) {
		Object val1 = ReflUtils.getValue(oggetto, propertyName);
		Object val2 = ReflUtils.getValue(oggetto, (String) otherPropertyName);

		if (val1 != null) {
		    return !val1.equals(val2);
		} else if (val2 != null) {
		    return !val2.equals(val1);
		} else
		    return false;
	    }
	};
    }

}
