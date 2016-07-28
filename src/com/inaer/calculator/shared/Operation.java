package com.inaer.calculator.shared;

public enum Operation {
    EQ, PLUS, MINUS, MULT, DIV, MOD;
 
    public Float execute(Float op1, Float op2) {
        switch (this) {
        case EQ:
            return op2;
        case PLUS:
            return op1 + op2;
        case MINUS:
            return op1 - op2;
        case MULT:
            return op1 * op2;
 
        case DIV:
            return op1 / op2;
        case MOD:
            return op1 % op2;
        default:
            return Float.NaN;
        }
    }
 
}