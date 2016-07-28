package com.inaer.calculator.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MeasureConverterServiceAsync {
	void convertToBinary(Float number, AsyncCallback<String> callback) throws IllegalArgumentException;
}
