package pepe.lmao.approximation;

import lombok.Data;
import org.apache.commons.math3.util.FastMath;

import java.util.Arrays;
@Data
public class PowApproximation {
    private double[] x;
    private double[] y;
    private double A;
    private double B;
    private int n;
    //output variables
    private double a;
    private double b;
    private double deviation_measure = 0, standard_deviation = 0;
    private double[] approximateResult;
    private String key = "PowApproximation";
    public PowApproximation(double[] x, double[] y) {
        this.x = x;
        this.y = y;
        this.n = x.length;
        this.approximateResult = new double[n];
    }

    public void result() {
        findSolution();
    }

    private void findSolution() {
        double SX = 0, SXX = 0, SY = 0, SXY = 0;
        int n = x.length;
        double[] x_log = new double[n];
        double[] y_log = new double[n];
        double[] eps = new double[n];
        double correlation;
        for (int i = 0; i < n; i++) {
            x_log[i] = FastMath.log(x[i]);
            y_log[i] = FastMath.log(y[i]);
        }
        for (int i = 0; i < n; i++) {
            SX += x_log[i];
            SXX += FastMath.pow(x_log[i], 2);
            SY += y_log[i];
            SXY += x_log[i] * y_log[i];
        }
        correlation = CorrelationUtil.calcCorrelation(x, y);
        A = (SXY * n - SX * SY) / (SXX * n - FastMath.pow(SX, 2));
        B = (SXX * SY - SX * SXY) / (SXX * n - FastMath.pow(SX, 2));
        a = FastMath.exp(B);
        b = A;
        for (int i = 0; i < n; i++) {
            approximateResult[i] = f(x[i]);
            eps[i] = approximateResult[i] - y[i];
            deviation_measure += FastMath.pow(eps[i], 2);
        }
        standard_deviation = FastMath.sqrt(deviation_measure/n);
    }

    private double f(double x) {
        return a*FastMath.pow(x,b);
    }
}
