package org.insset.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class DivisionApp implements EntryPoint {

    interface DivisionUiBinder extends UiBinder<Widget, DivisionApp> {}
    private static DivisionUiBinder uiBinder = GWT.create(DivisionUiBinder.class);

    @UiField
    TextBox txtNumber1;

    @UiField
    TextBox txtNumber2;

    @UiField
    Button divideButton;

    @UiField
    Label resultLabel;

    @Override
    public void onModuleLoad() {
        // Charge l'interface utilisateur à partir du fichier UiBinder .ui.xml
        Widget mainWidget = uiBinder.createAndBindUi(this);
        RootPanel.get("divisionapp").add(mainWidget);

        // Gestionnaire d'événement pour le bouton "Diviser"
        divideButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                try {
                    double number1 = Double.parseDouble(txtNumber1.getText());
                    double number2 = Double.parseDouble(txtNumber2.getText());
                    if (number2 != 0) {
                        double result = number1 / number2;
                        resultLabel.setText("Résultat : " + result);
                    } else {
                        resultLabel.setText("Erreur : Division par zéro !");
                    }
                } catch (NumberFormatException e) {
                    resultLabel.setText("Erreur : Veuillez entrer des nombres valides.");
                }
            }
        });
    }
}
