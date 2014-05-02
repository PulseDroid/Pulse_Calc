package com.pulsedroid.pulsecalc;
 
import android.os.Bundle;

import java.text.DecimalFormat;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.pulsedroid.pulsecalc.R;

public class MainActivity extends SlidingActivity implements OnClickListener {
 
	private TextView mOutput;
    private Calculator mCalculator;
    
    private Boolean userIsInTheMiddleOfTypingANumber = false;
    private static final String DIGITS = "0123456789.";
    DecimalFormat df = new DecimalFormat("@###########");
    
    @SuppressLint("NewApi")
    
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //About sliding menu
        
        setBehindContentView(R.layout.activity_menu);
       
        SlidingMenu sm = getSlidingMenu();
        sm.setMode(SlidingMenu.LEFT);
        sm.setBehindOffset(150);
        sm.setShadowWidthRes(R.dimen.shadow_width);
        sm.setShadowDrawable(R.drawable.shadow);

        mCalculator = new Calculator();
        mOutput = (TextView) findViewById(R.id.textView1);
 
        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(8);
 
        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
 
        findViewById(R.id.buttonAdd).setOnClickListener(this);
        findViewById(R.id.buttonSubtract).setOnClickListener(this);
        findViewById(R.id.buttonMultiply).setOnClickListener(this);
        findViewById(R.id.buttonDivide).setOnClickListener(this);
        findViewById(R.id.buttonToggleSign).setOnClickListener(this);
        findViewById(R.id.buttonDecimalPoint).setOnClickListener(this);
        findViewById(R.id.buttonEquals).setOnClickListener(this);
        findViewById(R.id.buttonClear).setOnClickListener(this); 
    }
       
 
    @Override
    public void onClick(View v) {
    	
    	
 
        String buttonPressed = ((Button) v).getText().toString();
 
        if (DIGITS.contains(buttonPressed)) {
 
            // Input number
            if (userIsInTheMiddleOfTypingANumber) {
 
                if (buttonPressed.equals(".") && mOutput.getText().toString().contains(".")) {
                	
                	
                		
                    // Avoid more than one .
                } else {
                    mOutput.append(buttonPressed);
                }
 
            } else {
 
                if (buttonPressed.equals(".")) {
                    // Avoid . before starting
                    mOutput.setText(0 + buttonPressed);
                } else {
                    mOutput.setText(buttonPressed);
                }
 
                userIsInTheMiddleOfTypingANumber = true;
            }
 
        } else {
            // Input Operation
            if (userIsInTheMiddleOfTypingANumber) {
 
                mCalculator.setInputMath(Double.parseDouble(mOutput.getText().toString()));
                userIsInTheMiddleOfTypingANumber = false;
            }
 
            mCalculator.performOperation(buttonPressed);
            if (new Double(mCalculator.getResult()).equals(0.0)){
                mOutput.setText("" + 0);
            } else {
            	mOutput.setText(df.format(mCalculator.getResult()));
            }
 
        }
 
    }
    
}
  
