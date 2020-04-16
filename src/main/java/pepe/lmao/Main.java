package pepe.lmao;

import pepe.lmao.method.SmallSquare;

import java.io.*;
import java.util.Random;

public class Main {
    static final double[] x = new double[14];
    static final double[] y = new double[14];

    public static void main(String[] args) throws IOException {
        readFromFile("input.txt");
        SmallSquare smallSquare = new SmallSquare(x,y);
        smallSquare.solve();
        smallSquare.plot();
        writeToFile(smallSquare.print());



    }

    public static void readFromFile(String filename) throws IOException {
        String row;
        String[] data;
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while ((row = reader.readLine()) != null) {
            data = row.split(",");
            for (int i = 0; i < data.length; i++) {
                if (x[i] == 0.0)
                    x[i] = Double.parseDouble(data[i]);
                else
                    y[i] = Double.parseDouble(data[i]);
            }
        }

    }

    private static void writeToFile(String data) throws IOException {
        Random random = new Random();
        String filename = "output_" + Math.abs(random.nextInt()) + ".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(data);
        writer.close();
        System.out.println("-->Done! Check the file: " + filename);
    }
}
