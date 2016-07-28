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
    
    private static final String DEC_STR = ".";
 
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
		TextButton btnDec = new TextButton(DEC_STR);
		btnDec.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				if (isNewNumber) {
					screenOutput.setValue(DEFAULT_VALUE);
				}
				if (!screenOutput.getText().contains(".")) {
					screenOutput.setText(screenOutput.getText() + ".");
					isNewNumber = false;
				}
			}
		});
		RootPanel.get("btnDec").add(btnDec);

		TextButton btnNeg = new TextButton("+/-");
		btnNeg.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				isNewNumber = false;
				screenOutput.setValue(screenOutput.getValue() * -1);
			}
		});
		RootPanel.get("btnNeg").add(btnNeg);
    }
 
    private void addOperatorButtons() {
        TextButton btnPls = new TextButton("+");
        btnPls.addSelectHandler(new OperationHandler(Operation.PLUS));
        RootPanel.get("btnPls").add(btnPls);
 
        TextButton btnMns = new TextButton("-");
        btnMns.addSelectHandler(new OperationHandler(Operation.MINUS));
        RootPanel.get("btnMns").add(btnMns);
 
        TextButton btnMul = new TextButton("*");
        btnMul.addSelectHandler(new OperationHandler(Operation.MULT));
        RootPanel.get("btnMul").add(btnMul);
 
        TextButton btnDiv = new TextButton("/");
        btnDiv.addSelectHandler(new OperationHandler(Operation.DIV));
        RootPanel.get("btnDiv").add(btnDiv);
 
        TextButton btnPct = new TextButton("%");
        btnPct.addSelectHandler(new OperationHandler(Operation.MOD));
        RootPanel.get("btnPct").add(btnPct);
 
        TextButton btnEql = new TextButton("=");
        btnEql.addSelectHandler(new OperationHandler(Operation.EQ));
        RootPanel.get("btnEql").add(btnEql);
    }
 
    private void addDeletionButtons() {
    	TextButton ceButton = new TextButton("CE");
		ceButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				partialInit();
			}
		});
		RootPanel.get("btnCE").add(ceButton);

		// ADD C BUTTON
		TextButton cButton = new TextButton("C");
		cButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				init();
			}
		});
		RootPanel.get("btnC").add(cButton);
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
