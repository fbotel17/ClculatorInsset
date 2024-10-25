package org.insset.client.exemple;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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
    public TextBox prixDepart;
    @UiField
    public TextBox pourcentage;
    @UiField
    public TextBox montantFinal;
    @UiField
    public TextBox pourcentageInverse;

    @UiField
    public Label resultatPrixPourcentage;
    @UiField
    public Label resultatMontantFinal;
    @UiField
    public Label errorLabel;

    @UiField
    public ResetButton clearPrixDepart;
    @UiField
    public ResetButton clearPourcentage;
    @UiField
    public ResetButton clearMontantFinal;
    @UiField
    public ResetButton clearPourcentageInverse;
    @UiField
    public SubmitButton boutonEnvoyerPrixPourcentage;
    @UiField
    public SubmitButton boutonEnvoyerMontantPourcentage;

    private final ExempleServiceAsync service = GWT.create(ExempleService.class);

    interface AddUiBinder extends UiBinder<HTMLPanel, ExemplePresenter> {
    }

    private static AddUiBinder ourUiBinder = GWT.create(AddUiBinder.class);

    public ExemplePresenter() {
        initWidget(ourUiBinder.createAndBindUi(this));
        initHandler();
    }

    protected void initHandler() {
        clearPrixDepart.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                prixDepart.setText("");
                resultatPrixPourcentage.setText(""); // Reset the result label
            }
        });

        clearPourcentage.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                pourcentage.setText("");
            }
        });

        clearMontantFinal.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                montantFinal.setText("");
                resultatMontantFinal.setText(""); // Reset the result label
            }
        });

        clearPourcentageInverse.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                pourcentageInverse.setText("");
            }
        });

        boutonEnvoyerPrixPourcentage.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                try {
                    int prix = Integer.parseInt(prixDepart.getText());
                    int pct = Integer.parseInt(pourcentage.getText());
                    service.calculerPrixAvecPourcentage(prix, pct, new AsyncCallback<Integer>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            resultatPrixPourcentage.setText("Erreur : " + caught.getMessage());
                        }

                        @Override
                        public void onSuccess(Integer result) {
                            resultatPrixPourcentage.setText("Résultat : " + result + " €");
                        }
                    });
                } catch (NumberFormatException e) {
                    resultatPrixPourcentage.setText("Erreur : veuillez entrer des nombres valides.");
                }
            }
        });

        boutonEnvoyerMontantPourcentage.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                try {
                    int montant = Integer.parseInt(montantFinal.getText());
                    int pct = Integer.parseInt(pourcentageInverse.getText());
                    service.calculerPrixDepartAvecPourcentage(montant, pct, new AsyncCallback<Integer>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            resultatMontantFinal.setText("Erreur : " + caught.getMessage());
                        }

                        @Override
                        public void onSuccess(Integer result) {
                            resultatMontantFinal.setText("Résultat : " + result + " €");
                        }
                    });
                } catch (NumberFormatException e) {
                    resultatMontantFinal.setText("Erreur : veuillez entrer des nombres valides.");
                }
            }
        });
    }
}
