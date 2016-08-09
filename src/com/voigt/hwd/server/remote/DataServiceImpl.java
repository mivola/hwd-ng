package com.voigt.hwd.server.remote;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.voigt.hwd.client.service.DataService;

public class DataServiceImpl extends RemoteServiceServlet implements DataService {
    public String[][] getData() {
	return new String[][] {
		new String[] { "1", "3m Co", "71.72", "0.02", "0.03", "9/1 12:00am", "MMM", "Manufacturing" },
		new String[] { "2", "Alcoa Inc", "29.01", "0.42", "1.47", "9/1 12:00am", "AA", "Manufacturing" },
		new String[] { "3", "Altria Group Inc", "83.81", "0.28", "0.34", "9/1 12:00am", "MO", "Manufacturing" },
		new String[] { "4", "American Express Company", "52.55", "0.01", "0.02", "9/1 12:00am", "AXP",
			"Finance" },
		new String[] { "5", "American International Group, Inc.", "64.13", "0.31", "0.49", "9/1 12:00am",
			"AIG", "Services" },
		new String[] { "6", "AT&T Inc.", "31.61", "-0.48", "-1.54", "9/1 12:00am", "T", "Services" },
		new String[] { "7", "Boeing Co.", "75.43", "0.53", "0.71", "9/1 12:00am", "BA", "Manufacturing" },
		new String[] { "8", "Caterpillar Inc.", "67.27", "0.92", "1.39", "9/1 12:00am", "CAT", "Services" },
		new String[] { "9", "Citigroup, Inc.", "49.37", "0.02", "0.04", "9/1 12:00am", "C", "Finance" },
		new String[] { "10", "E.I. du Pont de Nemours and Company", "40.48", "0.51", "1.28", "9/1 12:00am",
			"DD", "Manufacturing" } };
    }
}
