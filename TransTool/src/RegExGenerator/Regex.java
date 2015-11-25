/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegExGenerator;

/**
 *
 * @author hallm8
 */
public class Regex {

    private float highBound;
    private float lowBound;
    private int hPrecision;
    private int lPrecision;
    private String regex;
    private String sHigh;
    private String sLow;

    /**
     * REGEX
     *
     * For now, I am going to use the simplest form of Regex possible. When I
     * understand Regex or perhaps find a better library for this, I will change
     * it. However ugly this solution, it IS a solution, therefore I will go
     * ahead and use it for now.
     */
    public Regex() {
        hPrecision = 0;
        lPrecision = 0;
    }

    /**
     *
     */
    public void fixExpression() {

        for (int i = 0; i < sHigh.length(); i++) {
            if (sHigh.charAt(i) == '.') {
                System.out.println("decimal found!!  Checking!");
                hPrecision = 1 + (sHigh.length() - i);
                break;
            }
        }
        for (int i = 0; i < sLow.length(); i++){
            if (sLow.charAt(i) == '.') {
                System.out.println("decimal found!!  Checking!");
                lPrecision = 1 + (sLow.length() - i);
                break;
            }
        }

        if (highBound > lowBound) {
            // It's good.  Do nothing.
        } else {
            float temp = highBound;
            highBound = lowBound;
            lowBound = temp;
        }
    }

    /**
     *
     * @return
     */
    public float getHighBound() {
        return highBound;
    }

    /**
     *
     * @param highBound
     */
    public void setHighBound(float highBound) {
        this.highBound = highBound;
    }

    /**
     *
     * @return
     */
    public float getLowBound() {
        return lowBound;
    }

    /**
     *
     * @param lowBound
     */
    public void setLowBound(float lowBound) {
        this.lowBound = lowBound;
    }

}
