package org.insset.client.exemple;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResetButton;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import org.insset.client.service.ExempleService;
import org.insset.client.service.ExempleServiceAsync;

public class ExemplePresenter extends Composite {

    @UiField
    public ResetButton boutonClear;
    @UiField
    public SubmitButton boutonEnvoyer;
    @UiField
    public TextBox nombre1;
    @UiField
    public TextBox nombre2;
    @UiField
    public Label errorLabel;

    private final ExempleServiceAsync service = GWT.create(ExempleService.class);

    interface AddUiBinder extends UiBinder<HTMLPanel, ExemplePresenter> {
    }

    private static AddUiBinder ourUiBinder = GWT.create(AddUiBinder.class);

    public ExemplePresenter() {
        initWidget(ourUiBinder.createAndBindUi(this));
        initHandler();
    }

    protected void initHandler() {
        boutonClear.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                nombre1.setText("");
                nombre2.setText("");
                errorLabel.setText("");
            }
        });

        boutonEnvoyer.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                envoyerDivision();
            }
        });
    }

    /**
     * Envoie les valeurs pour la division au service
     */
    private void envoyerDivision() {
        errorLabel.setText(""); // Réinitialiser le message d'erreur

        try {
            int valeur1 = Integer.parseInt(nombre1.getText());
            int valeur2 = Integer.parseInt(nombre2.getText());

            service.diviserEntiers(valeur1, valeur2, new AsyncCallback<Integer>() {
                @Override
                public void onFailure(Throwable caught) {
                    errorLabel.setText("Erreur : " + caught.getMessage());
                }

                @Override
                public void onSuccess(Integer result) {
                    errorLabel.setText("Résultat : " + result);
                }
            });
        } catch (NumberFormatException e) {
            errorLabel.setText("Erreur : Veuillez entrer des nombres valides.");
        }
    }
}
