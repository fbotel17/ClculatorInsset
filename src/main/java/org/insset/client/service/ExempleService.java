package org.insset.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("exemple")
public interface ExempleService extends RemoteService {
    Integer calculerPrixAvecPourcentage(int prix, int pourcentage);
    Integer calculerPrixDepartAvecPourcentage(int montant, int pourcentage);
    String inverserChaine(String name) throws IllegalArgumentException;
    Integer diviserEntiers(int a, int b) throws IllegalArgumentException;
}
