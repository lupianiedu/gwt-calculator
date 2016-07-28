package com.inaer.calculator.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.RootPanel;
import com.inaer.calculator.shared.Operation;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class INAER_Calculator implements EntryPoint {
    private static final float DEFAULT_VALUE = 0f;
 
    // Calculator variables
    private Float result;
    private Operation pendingOp;
    final NumberField<Float> screenOutput = new NumberField<Float>(new NumberPropertyEditor.FloatPropertyEditor());
    private boolean isNewNumber;
 
    private void partialInit() {
        screenOutput.setValue(DEFAULT_VALUE);
        isNewNumber = true;
    }
 
    private void init() {
        partialInit();
        pendingOp = Operation.EQ;
        result = DEFAULT_VALUE;
    }
 
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        init();
        screenOutput.setFormat(NumberFormat.getFormat("0.#######"));
        RootPanel.get("currentNumber").add(screenOutput);
         
        addNumberButtons();
        addDeletionButtons();
        addOperatorButtons();
        addManipulationButtons();
        addBinaryConversionButton();
    }
 
    private void addBinaryConversionButton() {

    }
 
    private void addManipulationButtons() {

    }
 
    private void addOperatorButtons() {

    }
 
    private void addDeletionButtons() {

    }
 
    private void addNumberButtons() {
        for (int i = 0; i <= 9; i++) {
            final int currentValue = i;
            TextButton btnI = new TextButton("" + currentValue);
            btnI.addSelectHandler(new SelectHandler() {
                @Override
                public void onSelect(SelectEvent event) {
                    if (isNewNumber) {
                        screenOutput.setText("");
                    }
                    String output = screenOutput.getText() + currentValue;
                    screenOutput.setValue(Float.parseFloat(output));
                    screenOutput.setText(Float.toString(screenOutput.getValue()));
                    isNewNumber = false;
                }
            });
            RootPanel.get("btn" + i).add(btnI);
        }
    }
     
    class OperationHandler implements SelectHandler {
        private final Operation operation;
 
        public OperationHandler(Operation operation) {
            this.operation = operation;
        }
 
        @Override
        public void onSelect(SelectEvent event) {
            if (!isNewNumber) {
                Float operator = screenOutput.getValue();
                result = pendingOp.execute(result, operator);
                screenOutput.setValue(result);
            }
            pendingOp = operation;
            isNewNumber = true;
        }
    }
}
