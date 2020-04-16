package pepe.lmao.approximation;

import lombok.Data;
import org.apache.commons.math3.util.FastMath;
import pepe.lmao.cramer.CramersRuleImplementation;

@Data
public class PolynomialApproximation {
    private double[] x;
    private double[] y;
    private int n;
    //output variables
    private double a, b, c;
    double deviation_measure = 0, standard_deviation = 0;
    private double[] approximateResult;
    private String key = "PolynomialApproximation";
    public PolynomialApproximation(double[] x, double[] y) {
        this.x = x;
        this.y = y;
        this.n = x.length;
        this.approximateResult = new double[n];
    }
    public void result() {
        findSolution();
    }
    private void findSolution() {
        double SX = 0, SXX = 0, SXXX = 0, SXXXX = 0, SY = 0, SXY = 0, SXXY = 0;
        int n = x.length;
        double[] eps = new double[n];
        double correlation;
        for (int i = 0; i < n; i++) {
            SX += x[i];
            SXX += FastMath.pow(x[i], 2);
            SXXX += FastMath.pow(x[i], 3);
            SXXXX += FastMath.pow(x[i], 4);
            SY += y[i];
            SXY += x[i] * y[i];
            SXXY += FastMath.pow(x[i], 2) * y[i];
        }
        double[][] coefficients = {
                {n, SX, SXX, SY},
                {SX, SXX, SXXX, SXY},
                {SXX, SXXX, SXXXX, SXXY},
        };
        CramersRuleImplementation cramersRuleImplementation = new CramersRuleImplementation();
        cramersRuleImplementation.findSolution(coefficients);
        a = CramersRuleImplementation.a0;
        b = CramersRuleImplementation.a1;
        c = CramersRuleImplementation.a2;
        for (int i = 0; i < n; i++) {
            approximateResult[i] = f(x[i]);
            eps[i] = approximateResult[i] - y[i];
            deviation_measure += FastMath.pow(eps[i], 2);
        }
        standard_deviation = FastMath.sqrt(deviation_measure/n);
        correlation = CorrelationUtil.calcCorrelation(x,y);
    }

    private double f(double x) {
        return a + b*x + c * FastMath.pow(x,2);
    }
}
