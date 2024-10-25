package org.insset.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("division")
public interface DivisionService extends RemoteService {
    Double diviser(double num1, double num2) throws IllegalArgumentException;
}
