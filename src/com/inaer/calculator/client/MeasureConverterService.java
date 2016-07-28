package com.inaer.calculator.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("measureConverter")
public interface MeasureConverterService extends RemoteService{
	String convertToBinary(Float number) throws IllegalArgumentException;
}
