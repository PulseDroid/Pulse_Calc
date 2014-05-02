package com.pulsedroid.pulsecalc;

public class Calculator {
 
    private double mInputMath;
    private double mWaitingInputMath;
    private String mWaitingoperation;
    private double mCalculatorMemory;
 
    public static final String ADD = "+";
    public static final String SUBTRACT = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";
    public static final String CLEAR = "C" ;
    public static final String TOGGLESIGN = "+/-";

    public Calculator() {
        // Starting number
        mInputMath = 0;
        mWaitingInputMath = 0;
        mWaitingoperation = "";
        mCalculatorMemory = 0;
    }
 
    public void setInputMath(double InputMath) {
        mInputMath = InputMath;
    }
 
    public double getResult() {
        return mInputMath;
    }

    public String toString() {
        return Double.toString(mInputMath);
    }
 
    protected double performOperation(String operation) {

        if (operation.equals(CLEAR)) {
            mInputMath = 0;
            mWaitingoperation = "";
            mWaitingInputMath = 0;
   
        } else if (operation.equals(TOGGLESIGN)) {
            mInputMath = -mInputMath;
        } else {
            performWaitingOperation();
            mWaitingoperation = operation;
            mWaitingInputMath = mInputMath;
        }
 
        return mInputMath;
    }
 
    protected void performWaitingOperation() {
 
        if (mWaitingoperation.equals(ADD)) {
            mInputMath = mWaitingInputMath + mInputMath;
        } else if (mWaitingoperation.equals(SUBTRACT)) {
            mInputMath = mWaitingInputMath - mInputMath;
        } else if (mWaitingoperation.equals(MULTIPLY)) {
            mInputMath = mWaitingInputMath * mInputMath;
        } else if (mWaitingoperation.equals(DIVIDE)) {
            if (mInputMath != 0) {
                mInputMath = mWaitingInputMath / mInputMath;
            }
        }
 
    }
}