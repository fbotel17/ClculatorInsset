package org.insset.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.insset.client.service.ExempleService;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ExempleServiceImpl extends RemoteServiceServlet implements
        ExempleService {

    public String inverserChaine(String input) throws IllegalArgumentException {
        int longueur = input.length();
        StringBuffer envers = new StringBuffer();
        int i;

        for (i = 0; i < longueur; i++) {
            envers.append(input.charAt(longueur - i - 1));
        }
        return new String(envers);
    }
    
    
    @Override
    public Integer calculerPrixAvecPourcentage(int prix, int pourcentage) {
        double result = prix - (prix * pourcentage / 100.0);
        return (int) result; // Cast to int
    }

    @Override
    public Integer calculerPrixDepartAvecPourcentage(int montant, int pourcentage) {
        double prixInitial = montant / (1 - (pourcentage / 100.0)); // Convertir le pourcentage en fraction
        return (int) Math.round(prixInitial);
    }

    @Override
    public Integer diviserEntiers(int valeur1, int valeur2) {
        if (valeur2 == 0) {
            throw new IllegalArgumentException("Division par zéro");
        }
        return valeur1 / valeur2;
    }


}
