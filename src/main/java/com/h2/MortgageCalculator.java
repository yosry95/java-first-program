package com.h2;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MortgageCalculator {
    private long loanAmount;
    private int termInYears;
    private float annualRate;
    private double monthlyPayment;

    public MortgageCalculator(long loanAmount, int termInYears, float annualRate) {
        this.loanAmount = loanAmount;
        this.termInYears = termInYears;
        this.annualRate = annualRate;
    }

    private int getNumberOfPayments() {
        return termInYears * 12;
    }

    private float getMonthlyInterestRate() {
        return (annualRate / 100 / 12);
    }

    public void calculateMonthlyPayment() {
        double powerValue = Math.pow(1 + getMonthlyInterestRate(), getNumberOfPayments());
        double M = loanAmount * ((getMonthlyInterestRate() * powerValue) / (powerValue - 1));
        this.monthlyPayment = M;
    }

    public String toString() {
        Locale currentLocale = Locale.getDefault();
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator(',');
        DecimalFormat df = new DecimalFormat("####0.00", otherSymbols);
        return "monthlyPayment: " + df.format(monthlyPayment);
    }

    public static void main(String[] args) {
        long loanAmount = Long.parseLong(args[0]);
        int termsInYear = Integer.parseInt(args[1]);
        float annualRate = Float.parseFloat(args[2]);
        MortgageCalculator calculator = new MortgageCalculator(loanAmount, termsInYear, annualRate);
        calculator.calculateMonthlyPayment();
        System.out.println(calculator.toString());
    }
}
