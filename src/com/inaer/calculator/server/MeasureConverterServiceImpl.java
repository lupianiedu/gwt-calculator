package com.inaer.calculator.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.inaer.calculator.client.MeasureConverterService;


@SuppressWarnings("serial")
public class MeasureConverterServiceImpl extends RemoteServiceServlet implements MeasureConverterService {
	@Override
	public String convertToBinary(Float number) throws IllegalArgumentException {
		return Integer.toBinaryString(Float.floatToIntBits(number));
	}

}
