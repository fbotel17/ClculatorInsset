package org.insset.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DivisionServiceAsync {
    void diviser(double num1, double num2, AsyncCallback<Double> callback);
}
