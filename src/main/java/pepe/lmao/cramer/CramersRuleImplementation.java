package pepe.lmao.cramer;

import lombok.Data;

@Data
public class CramersRuleImplementation {
    public static double a0;
    public static double a1;
    public static double a2;

    private static double determinantOfMatrix(double[][] mat) {
        double ans;
        ans = mat[0][0] * (mat[1][1] * mat[2][2] - mat[2][1] * mat[1][2])
                - mat[0][1] * (mat[1][0] * mat[2][2] - mat[1][2] * mat[2][0])
                + mat[0][2] * (mat[1][0] * mat[2][1] - mat[1][1] * mat[2][0]);
        return ans;
    }

    public void findSolution(double[][] coeff) {
        double[][] d = {
                {coeff[0][0], coeff[0][1], coeff[0][2]},
                {coeff[1][0], coeff[1][1], coeff[1][2]},
                {coeff[2][0], coeff[2][1], coeff[2][2]},
        };
        double[][] d1 = {
                {coeff[0][3], coeff[0][1], coeff[0][2]},
                {coeff[1][3], coeff[1][1], coeff[1][2]},
                {coeff[2][3], coeff[2][1], coeff[2][2]},
        };
        double[][] d2 = {
                {coeff[0][0], coeff[0][3], coeff[0][2]},
                {coeff[1][0], coeff[1][3], coeff[1][2]},
                {coeff[2][0], coeff[2][3], coeff[2][2]},
        };
        double[][] d3 = {
                {coeff[0][0], coeff[0][1], coeff[0][3]},
                {coeff[1][0], coeff[1][1], coeff[1][3]},
                {coeff[2][0], coeff[2][1], coeff[2][3]},
        };
        double D = determinantOfMatrix(d);
        double D1 = determinantOfMatrix(d1);
        double D2 = determinantOfMatrix(d2);
        double D3 = determinantOfMatrix(d3);
        if (D != 0) {
            a0 = D1 / D;
            a1 = D2 / D;
            a2 = D3 / D;
        } else {
            if (D1 == 0 && D2 == 0 && D3 == 0)
                System.out.println("Infinite solutions");
            else if (D1 != 0 || D2 != 0 || D3 != 0)
                System.out.println("No solutions");
        }
    }
}
