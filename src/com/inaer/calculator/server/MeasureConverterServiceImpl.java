package com.inaer.calculator.server;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.inaer.calculator.client.MeasureConverterService;
import com.inaer.calculator.server.data.Conversion;

@SuppressWarnings("serial")
public class MeasureConverterServiceImpl extends RemoteServiceServlet implements MeasureConverterService {
	private static final PersistenceManagerFactory PMF = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");

	@Override
	public String convertToBinary(Float number) throws IllegalArgumentException {
		String binary = Integer.toBinaryString(Float.floatToIntBits(number));
		PersistenceManager pm = getPersistenceManager();
	    try {
	      pm.makePersistent(new Conversion(number, binary));
	    } finally {
	      pm.close();
	    }
		return binary;
	}

	private PersistenceManager getPersistenceManager() {
		return PMF.getPersistenceManager();
	}
}
