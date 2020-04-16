package pepe.lmao.method;

import lombok.Getter;
import org.apache.commons.math3.util.FastMath;
import pepe.lmao.approximation.*;
import pepe.lmao.chart.XYChart;

import java.util.*;

@Getter
public class SmallSquare {
    private double[] x;
    private double[] y;
    private String key = "OriginalFunction";
    static LinearApproximation linearApproximation;
    static PolynomialApproximation polynomialApproximation;
    static ExponentialApproximation exponentialApproximation;
    static LogarithmicApproximation logarithmicApproximation;
    static PowApproximation powApproximation;

    public SmallSquare(double[] x, double[] y) {
        this.x = x;
        this.y = y;
        linearApproximation = new LinearApproximation(x, y);
        polynomialApproximation = new PolynomialApproximation(x, y);
        exponentialApproximation = new ExponentialApproximation(x, y);
        logarithmicApproximation = new LogarithmicApproximation(x, y);
        powApproximation = new PowApproximation(x, y);
    }

    public void solve() {
        linearApproximation.result();
        polynomialApproximation.result();
        exponentialApproximation.result();
        logarithmicApproximation.result();
        powApproximation.result();


    }

    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        Formatter formatter = new Formatter(stringBuilder);
        formatter.format("%25s %25s %25s %25s %25s %25s", "| FUNCTION TYPE |", "| a |", "| b |", "| c |", "| DEVIATION MEASURE (S) |", "| STANDARD DEVIATION |\n");
        formatter.format("%25s %25s %25s %25s %25s %25s", "f = ax + b", linearApproximation.getA(), linearApproximation.getB(), "-",
                linearApproximation.getDeviation_measure(), linearApproximation.getStandard_deviation() + "\n");


        formatter.format("%25s %25s %25s %25s %25s %25s", "f = ax^b", powApproximation.getA(), powApproximation.getB(), "-",
                powApproximation.getDeviation_measure(), powApproximation.getStandard_deviation() + "\n");


        formatter.format("%25s %25s %25s %25s %25s %25s", "f = ae^(bx)", exponentialApproximation.getA(), exponentialApproximation.getA(), "-",
                exponentialApproximation.getDeviation_measure(), exponentialApproximation.getStandard_deviation() + "\n");


        formatter.format("%25s %25s %25s %25s %25s %25s", "f = alnx + b", logarithmicApproximation.getA(), logarithmicApproximation.getA(), "-",
                logarithmicApproximation.getDeviation_measure(), logarithmicApproximation.getStandard_deviation() + "\n");


        formatter.format("%25s %25s %25s %25s %25s %25s", "f = ax^2bx + c", polynomialApproximation.getA(), polynomialApproximation.getB(), polynomialApproximation.getC(),
                polynomialApproximation.getDeviation_measure(), polynomialApproximation.getStandard_deviation());

        HashMap<String, Double> tmp = new HashMap<>();

        tmp.put(linearApproximation.getKey(), linearApproximation.getStandard_deviation());
        tmp.put(polynomialApproximation.getKey(), polynomialApproximation.getStandard_deviation());
        tmp.put(exponentialApproximation.getKey(), exponentialApproximation.getStandard_deviation());
        tmp.put(logarithmicApproximation.getKey(), logarithmicApproximation.getStandard_deviation());
        tmp.put(powApproximation.getKey(), powApproximation.getStandard_deviation());
        Map.Entry<String, Double> min = null;
        for (Map.Entry<String, Double> entry : tmp.entrySet()) {
            if (min == null || min.getValue() > entry.getValue()) {
                min = entry;
            }
        }
        assert min != null;
        stringBuilder.append("\nBest approximate result is shown by: ")
                .append(min.getKey())
                .append(" \nwith the least standard deviation equals to: ")
                .append(min.getValue());
        return stringBuilder.toString();
    }

    public void plot() {
        XYChart xyChart = new XYChart();
        xyChart.prepareChart(getY(), getKey());
        xyChart.prepareChart(linearApproximation.getApproximateResult(), linearApproximation.getKey());
        xyChart.prepareChart(polynomialApproximation.getApproximateResult(), polynomialApproximation.getKey());
        xyChart.prepareChart(exponentialApproximation.getApproximateResult(), exponentialApproximation.getKey());
        xyChart.prepareChart(logarithmicApproximation.getApproximateResult(), logarithmicApproximation.getKey());
        xyChart.prepareChart(powApproximation.getApproximateResult(), powApproximation.getKey());
        xyChart.pack();
        xyChart.setVisible(true);
    }


}
