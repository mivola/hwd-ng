package com.voigt.hwd.client.admin.ds;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.voigt.hwd.client.admin.ds.criteria.GWTCriterion;
import com.voigt.hwd.client.admin.ds.criteria.GWTCriterionType;

/**
 * Data source with ability to communicate with server by GWT RPC.<p/>
 * SmartClient natively supports data protocol "clientCustom". This protocol
 * means that communication with server should be implemented in
 * <code>transformRequest (DSRequest request)</code> method. Here is a few
 * things to note on <code>transformRequest</code> implementation:
 * <ul>
 * <li><code>DSResponse</code> object has to be created and
 * <code>processResponse (requestId, response)</code> must be called to finish
 * data request. <code>requestId</code> should be taken from original
 * <code>DSRequest.getRequestId ()</code>.</li>
 * <li>"clientContext" attribute from <code>DSRequest</code> should be copied
 * to <code>DSResponse</code>.</li>
 * <li>In case of failure <code>DSResponse</code> should contain at least
 * "status" attribute with error code (&lt;0).</li>
 * <li>In case of success <code>DSResponse</code> should contain at least
 * "data" attribute with operation type specific data:
 * <ul>
 * <li>FETCH - <code>ListGridRecord[]</code> retrieved records.</li>
 * <li>ADD - <code>ListGridRecord[]</code> with single added record.
 * Operation is called on every newly added record.</li>
 * <li>UPDATE - <code>ListGridRecord[]</code> with single updated record.
 * Operation is called on every updated record.</li>
 * <li>REMOVE - <code>ListGridRecord[]</code> with single removed record.
 * Operation is called on every removed record.</li>
 * </ul>
 * </li>
 * </ul>
 * 
 * @author Aleksandras Novikovas
 * @author System Tier
 * @version 1.0
 */
public abstract class GwtRpcDataSource<T> extends DataSource {
    public static final String OPERATOR_AND = "and";
    public static final String OPERATOR_OR = "or";
    public static final String OPERATOR_NOT = "not";
    public static final String OPERATOR_EQ = "equals";
    public static final String OPERATOR_NE = "notEquals";
    public static final String OPERATOR_LT = "lessThan";
    public static final String OPERATOR_LE = "lessOrEqual";
    public static final String OPERATOR_GT = "greaterThan";
    public static final String OPERATOR_GE = "greaterOrEqual";
    public static final String OPERATOR_CONTAINS = "iContains";
    public static final String OPERATOR_STARTS = "iStartsWith";
    public static final String OPERATOR_ENDS = "iEndsWith";
    public static final String OPERATOR_NOT_CONTAINS = "iNotContains";
    public static final String OPERATOR_NOT_STARTS = "iNotStartsWith";
    public static final String OPERATOR_NOT_ENDS = "iNotEndsWith";
    public static final String OPERATOR_IS_NULL = "isNull";
    public static final String OPERATOR_IS_NOT_NULL = "notNull";
    public static final String OPERATOR_EQ_FIELD = "equalsField";
    public static final String OPERATOR_NE_FIELD = "notEqualField";
    public static final String OPERATOR_BETWEEN = "between";

    /**
     * Creates new data source which communicates with server by GWT RPC. It is
     * normal server side SmartClient data source with data protocol set to
     * <code>DSProtocol.CLIENTCUSTOM</code> ("clientCustom" - natively
     * supported by SmartClient but should be added to smartGWT) and with data
     * format <code>DSDataFormat.CUSTOM</code>.
     */
    public GwtRpcDataSource() {
	// setDataProtocol (DSProtocol.CLIENTCUSTOM);
	setAttribute("dataProtocol", "clientCustom", false);
	setDataFormat(DSDataFormat.CUSTOM);
	setClientOnly(false);
    }

    public DataSourceField[] getFields() {
	// We get the fields directly from the javascript object
	// because this way we should get even the "inherited" fields
	JsArray<JavaScriptObject> fieldsJs = getFieldsJS();
	if (fieldsJs != null) {
	    DataSourceField[] ret = new DataSourceField[fieldsJs.length()];
	    for (int i = 0; i < fieldsJs.length(); i++) {
		ret[i] = new DataSourceField(fieldsJs.get(i));
	    }
	    return ret;
	}

	return new DataSourceField[0];
    }

    private native JsArray<JavaScriptObject> getFieldsJS() /*-{
       	var config = this.@com.smartgwt.client.core.BaseClass::config;
       
           if(config.fields) {
               return config.fields;
           }
           return null;
       }-*/;

    /**
     * Executes request to server.
     * 
     * @param request
     *                <code>DSRequest</code> being processed.
     * @return <code>Object</code> data from original request.
     */
    @Override
    protected Object transformRequest(DSRequest request) {
	String requestId = request.getRequestId();
	DSResponse response = new DSResponse();
	response.setAttribute("clientContext", request.getAttributeAsObject("clientContext"));
	// Assume success
	response.setStatus(0);
	switch (request.getOperationType()) {
	case FETCH:
	    executeFetch(requestId, request, response);
	    break;
	case ADD:
	    executeAdd(requestId, request, response);
	    break;
	case UPDATE:
	    executeUpdate(requestId, request, response);
	    break;
	case REMOVE:
	    executeRemove(requestId, request, response);
	    break;
	default:
	    // Operation not implemented.
	    break;
	}
	return request.getData();
    }

    /**
     * Copies the field from the DTO to the <code>ListGridRecord</code>
     * 
     * @param from
     *                <code>T</code> (generic) source bean
     * @param to
     *                <code>ListGridRecord</code> destination record
     */
    protected void copyValues(T from, ListGridRecord to) {
	DataSourceField[] fields = getFields();

	for (DataSourceField field : fields) {
	    String name = field.getName();
	    to.setAttribute(name, getValueOf(from, name));
	}
    }

    /**
     * Obtains the correct java object type from the javascript object
     * <code>jsObj</code> for the attribute named <code>fieldName</code>
     * 
     * @param jsObj
     *                <code>JavaScriptObject</code> data object containing
     *                values for
     * @param fieldName
     * @return value of the attribute <code>fieldName</code> as a Java
     *         <code>Serializable</code> Object
     */
    protected Serializable getTypedValue(JavaScriptObject jsObj, String fieldName) {
	DataSourceField[] fields = getFields();

	for (DataSourceField field : fields) {
	    if (field.getName().equalsIgnoreCase(fieldName)) {
		// TODO: Add here other cases when other types are required
		switch (field.getType()) {
		case INTEGER:
		    return Integer.valueOf(JSOHelper.getAttribute(jsObj, "value"));
		case TEXT:
		    return JSOHelper.getAttribute(jsObj, "value");
		case DATE:
		    // TODO: at the moment JSOHelper.getAttributeAsDate is not
		    // always working,
		    // we try to get it as a date, if this throws an exception
		    // then
		    // we simply get it as an object and compare its runtime
		    // class against java.util.Date (thanks Alius!)
		    Object object;
		    try {
			object = JSOHelper.getAttributeAsDate(jsObj, "value");
		    } catch (Exception e) {
			object = JSOHelper.getAttributeAsObject(jsObj, "value");
		    }

		    if (object instanceof Date)
			return (Serializable) object;
		}

		return null;
	    }
	}

	return null;
    }

    /**
     * Creates a <code>GWTCriterion</code> tree from a javascript criteria
     * object
     * 
     * @param jsObj
     * @return
     * @throws Exception
     */
    protected GWTCriterion createFetchCriteria(JavaScriptObject jsObj) throws Exception {
	String[] properties = JSOHelper.getProperties(jsObj);

	if (properties != null && properties.length > 0) {
	    // If the attribute operator is present we have either an
	    // AdvancedCriteria object or a criteria item
	    String operator = JSOHelper.getAttribute(jsObj, "operator");
	    if (operator != null) {
		String fieldName;
		if ("AdvancedCriteria".equalsIgnoreCase(JSOHelper.getAttribute(jsObj, "_constructor"))) {
		    // Read the AdvancedCriteria object
		    if (properties.length < 3)
			throw new Exception("bad AdvancedCriteria format: '" + properties + "'");

		    JavaScriptObject[] criteria = JSOHelper.getAttributeAsJavaScriptObjectArray(jsObj, "criteria");

		    if (criteria == null)
			throw new Exception("bad operands for AdvancedCriteria: '" + properties + "'");

		    GWTCriterion[] criterions = new GWTCriterion[criteria.length];
		    for (int i = 0; i < criterions.length; i++)
			criterions[i] = createFetchCriteria(criteria[i]);

		    if (OPERATOR_AND.equalsIgnoreCase(operator))
			return new GWTCriterion(GWTCriterionType.and, (Serializable[]) criterions);
		    else if (OPERATOR_OR.equalsIgnoreCase(operator))
			return new GWTCriterion(GWTCriterionType.or, (Serializable[]) criterions);
		    else if (OPERATOR_NOT.equalsIgnoreCase(operator))
			return new GWTCriterion(GWTCriterionType.not, (Serializable[]) criterions);
		} else if ((fieldName = JSOHelper.getAttribute(jsObj, "fieldName")) != null) {
		    Serializable value = getTypedValue(jsObj, fieldName);

		    if (OPERATOR_EQ.equalsIgnoreCase(operator))
			return new GWTCriterion(GWTCriterionType.eq, fieldName, value);
		    else if (OPERATOR_NE.equalsIgnoreCase(operator))
			return new GWTCriterion(GWTCriterionType.ne, fieldName, value);
		    else if (OPERATOR_CONTAINS.equalsIgnoreCase(operator))
			return new GWTCriterion(GWTCriterionType.contains, fieldName, value);
		    else if (OPERATOR_NOT_CONTAINS.equalsIgnoreCase(operator))
			return new GWTCriterion(GWTCriterionType.not, new GWTCriterion(GWTCriterionType.contains,
				fieldName, value));
		    else if (OPERATOR_STARTS.equalsIgnoreCase(operator))
			return new GWTCriterion(GWTCriterionType.begins, fieldName, value);
		    else if (OPERATOR_NOT_STARTS.equalsIgnoreCase(operator))
			return new GWTCriterion(GWTCriterionType.not, new GWTCriterion(GWTCriterionType.begins,
				fieldName, value));
		    else if (OPERATOR_ENDS.equalsIgnoreCase(operator))
			return new GWTCriterion(GWTCriterionType.ends, fieldName, value);
		    else if (OPERATOR_NOT_ENDS.equalsIgnoreCase(operator))
			return new GWTCriterion(GWTCriterionType.not, new GWTCriterion(GWTCriterionType.ends,
				fieldName, value));
		    else if (OPERATOR_GE.equalsIgnoreCase(operator))
			return new GWTCriterion(GWTCriterionType.ge, fieldName, value);
		    else if (OPERATOR_LT.equalsIgnoreCase(operator))
			return new GWTCriterion(GWTCriterionType.lt, fieldName, value);
		    else if (OPERATOR_LE.equalsIgnoreCase(operator))
			return new GWTCriterion(GWTCriterionType.le, fieldName, value);
		    else if (OPERATOR_GT.equalsIgnoreCase(operator))
			return new GWTCriterion(GWTCriterionType.gt, fieldName, value);
		    else if (OPERATOR_IS_NULL.equalsIgnoreCase(operator))
			return new GWTCriterion(GWTCriterionType.isNull, fieldName);
		    else if (OPERATOR_IS_NOT_NULL.equalsIgnoreCase(operator))
			return new GWTCriterion(GWTCriterionType.isNotNull, fieldName);
		    else if (OPERATOR_EQ_FIELD.equalsIgnoreCase(operator))
			return new GWTCriterion(GWTCriterionType.eqField, fieldName, value);
		    else if (OPERATOR_NE_FIELD.equalsIgnoreCase(operator))
			return new GWTCriterion(GWTCriterionType.neField, fieldName, value);
		    /*
		     * else if (OPERATOR_BETWEEN.equalsIgnoreCase(operator))
		     * return new GWTCriterion(GWTCriterionType.between,
		     * fieldName, value, criteria[2].toString());
		     */
		    else
			throw new Exception("bad operator (" + operator + ") for criteria: '" + properties + "'");
		} else
		    return new GWTCriterion(GWTCriterionType.eq, properties[0], JSOHelper.getAttribute(jsObj,
			    properties[0]));
	    }
	}

	return null;
    }

    /**
     * Executed on <code>FETCH</code> operation.
     * <code>processResponse (requestId, response)</code> should be called
     * when operation completes (either successful or failure).
     * 
     * @param requestId
     *                <code>String</code> extracted from
     *                <code>DSRequest.getRequestId ()</code>.
     * @param request
     *                <code>DSRequest</code> being processed.
     * @param response
     *                <code>DSResponse</code>. <code>setData (list)</code>
     *                should be called on successful execution of this method.
     *                <code>setStatus (&lt;0)</code> should be called on
     *                failure.
     */
    protected abstract void executeFetch(String requestId, DSRequest request, DSResponse response);

    /**
     * Executed on <code>ADD</code> operation.
     * <code>processResponse (requestId, response)</code> should be called
     * when operation completes (either successful or failure).
     * 
     * @param requestId
     *                <code>String</code> extracted from
     *                <code>DSRequest.getRequestId ()</code>.
     * @param request
     *                <code>DSRequest</code> being processed.
     *                <code>request.getData ()</code> contains record should
     *                be added.
     * @param response
     *                <code>DSResponse</code>. <code>setData (list)</code>
     *                should be called on successful execution of this method.
     *                Array should contain single element representing added
     *                row. <code>setStatus (&lt;0)</code> should be called on
     *                failure.
     */
    protected abstract void executeAdd(String requestId, DSRequest request, DSResponse response);

    /**
     * Executed on <code>UPDATE</code> operation.
     * <code>processResponse (requestId, response)</code> should be called
     * when operation completes (either successful or failure).
     * 
     * @param requestId
     *                <code>String</code> extracted from
     *                <code>DSRequest.getRequestId ()</code>.
     * @param request
     *                <code>DSRequest</code> being processed.
     *                <code>request.getData ()</code> contains record should
     *                be updated.
     * @param response
     *                <code>DSResponse</code>. <code>setData (list)</code>
     *                should be called on successful execution of this method.
     *                Array should contain single element representing updated
     *                row. <code>setStatus (&lt;0)</code> should be called on
     *                failure.
     */
    protected abstract void executeUpdate(String requestId, DSRequest request, DSResponse response);

    /**
     * Executed on <code>REMOVE</code> operation.
     * <code>processResponse (requestId, response)</code> should be called
     * when operation completes (either successful or failure).
     * 
     * @param requestId
     *                <code>String</code> extracted from
     *                <code>DSRequest.getRequestId ()</code>.
     * @param request
     *                <code>DSRequest</code> being processed.
     *                <code>request.getData ()</code> contains record should
     *                be removed.
     * @param response
     *                <code>DSResponse</code>. <code>setData (list)</code>
     *                should be called on successful execution of this method.
     *                Array should contain single element representing removed
     *                row. <code>setStatus (&lt;0)</code> should be called on
     *                failure.
     */
    protected abstract void executeRemove(String requestId, DSRequest request, DSResponse response);

    /**
     * Returns the "field"'s value from the (generic) "from" DTO
     * 
     * @param from
     *                Bean whose field is requested
     * @param field
     *                <code>ITypedEnum</code> field requested from the DTO
     *                bean
     * @return
     */
    protected abstract Object getValueOf(T from, String fieldName);
}
