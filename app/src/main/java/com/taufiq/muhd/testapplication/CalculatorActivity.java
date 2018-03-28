package com.taufiq.muhd.testapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CalculatorActivity extends AppCompatActivity {

    private EditText etLoanAmount, etDownPayment, etTerm, etAnnualInterestRate;
    private TextView tvMonthlyPayment, tvTotalRepayment, tvTotalInterest, tvAverageMonthlyInterest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        etLoanAmount = (EditText) findViewById(R.id.loan_amount);
        etDownPayment = (EditText) findViewById(R.id.down_payment);
        etTerm = (EditText) findViewById(R.id.term);
        etAnnualInterestRate = (EditText) findViewById(R.id.annual_interest_rate);

        tvMonthlyPayment = (TextView) findViewById(R.id.monthly_repayment);
        tvTotalRepayment = (TextView) findViewById(R.id.total_repayment);
        tvTotalInterest = (TextView) findViewById(R.id.total_interest);
        tvAverageMonthlyInterest = (TextView) findViewById(R.id.average_montlhy_interest);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.buttonCalculate:
                calculate();
                break;
            case R.id.buttonReset:
                reset();
                break;
        }
        Log.d("Check","Button clicked!");
    }
    private void calculate(){
        String amount = etLoanAmount.getText().toString();
        String downPayment = etDownPayment.getText().toString();
        String interestRate = etAnnualInterestRate.getText().toString();
        String term = etTerm.getText().toString();

        double loanAmount = Double.parseDouble(amount) - Double.parseDouble(downPayment);
        double interest = Double.parseDouble(interestRate) / 12 / 100;
        double noOfmonth = (Integer.parseInt(term) * 12);

        /*------put 2 decimal-----*/
        DecimalFormat formatD = new DecimalFormat("#.00");
        /*-----put 2 decimal-----*/

        if(noOfmonth > 0){
            double monthlyRepayment = loanAmount *
                    (interest+ (interest/(java.lang.Math.pow((1+interest),
                            noOfmonth)-1)));
            double totalRepayment = monthlyRepayment * noOfmonth;
            double totalInterest = totalRepayment - loanAmount;
            double monthlyInterest = totalInterest / noOfmonth;

            /*original without 2 decimal above*/
            /*tvMonthlyPayment.setText(String.valueOf(monthlyRepayment));
            tvTotalRepayment.setText(String.valueOf(totalRepayment));
            tvTotalInterest.setText(String.valueOf(totalInterest));
            tvAverageMonthlyInterest.setText(String.valueOf(monthlyInterest));*/

            tvMonthlyPayment.setText(formatD.format(monthlyRepayment));
            tvTotalRepayment.setText(formatD.format(totalRepayment));
            tvTotalInterest.setText(formatD.format(totalInterest));
            tvAverageMonthlyInterest.setText(formatD.format(monthlyInterest));
        }
    }
    private void reset(){
        etLoanAmount.setText("");
        etDownPayment.setText("");
        etTerm.setText("");
        etAnnualInterestRate.setText("");

        tvMonthlyPayment.setText(R.string.default_result);
        tvTotalRepayment.setText(R.string.default_result);
        tvTotalInterest.setText(R.string.default_result);
        tvAverageMonthlyInterest .setText(R.string.default_result);
    }
}
