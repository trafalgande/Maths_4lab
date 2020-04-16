package pepe.lmao.approximation;

import org.apache.commons.math3.util.FastMath;

public class CorrelationUtil {
    public static double calcCorrelation(double[] x, double[] y) {
    double SX = 0, SY = 0, avg_x = 0, avg_y = 0;
    double cor_sum = 0, cor_x = 0, cor_y = 0;
    int n = x.length;
        for (int i = 0; i < n; i++) {
            SX += x[i];
            SY += y[i];

        }
        avg_x = SX / n;
        avg_y = SY / n;
        for (int i = 0; i < n; i++) {
            cor_sum += (x[i] - avg_x) * (y[i] - avg_y);
            cor_x += FastMath.pow((x[i] - avg_x), 2);
            cor_y += FastMath.pow((y[i] - avg_y), 2);
        }
        return cor_sum / (FastMath.sqrt(cor_x * cor_y));
    }
}
