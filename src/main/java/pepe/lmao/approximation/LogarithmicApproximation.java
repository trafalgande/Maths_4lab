package pepe.lmao.approximation;

import lombok.Data;
import org.apache.commons.math3.util.FastMath;

import java.util.Arrays;
@Data
public class LogarithmicApproximation {
    private double[] x;
    private double[] y;

    //output variables
    private double a;
    private double b;
    double deviation_measure = 0, standard_deviation = 0;
    private double[] approximateResult;
    private int n;
    private String key = "LogarithmicApproximation";

    public LogarithmicApproximation(double[] x, double[] y) {
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
        double[] eps = new double[n];

        double correlation;
        for (int i = 0; i < n; i++) {
            x_log[i] = FastMath.log(x[i]);
        }
        for (int i = 0; i < n; i++) {
            SX += x_log[i];
            SXX += FastMath.pow(x_log[i], 2);
            SY += y[i];
            SXY += x_log[i] * y[i];
        }

        correlation = CorrelationUtil.calcCorrelation(x, y);

        a = (SXY * n - SX * SY) / (SXX * n - FastMath.pow(SX, 2));
        b = (SXX * SY - SX * SXY) / (SXX * n - FastMath.pow(SX, 2));
        for (int i = 0; i < n; i++) {
            approximateResult[i] = f(x[i]);
            eps[i] = approximateResult[i] - y[i];
            deviation_measure += FastMath.pow(eps[i], 2);
        }

        standard_deviation = FastMath.sqrt(deviation_measure/n);

       /* System.out.println("f(aln(x)+b) = " + Arrays.toString(approximateResult) + "\n" +
                "eps[i] = " + Arrays.toString(eps) + "\ndeviation = " + deviation_measure + "\n" +
                "a = " + a + "\nb = " + b + "\ncorrelation = " + correlation + "\nstandard deviation = " + standard_deviation);*/

    }

    private double f(double x) {
        return a*FastMath.log(x) + b;
    }
}
