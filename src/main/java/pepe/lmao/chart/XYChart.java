package pepe.lmao.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.AbstractRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class XYChart extends JFrame {
    final XYSeriesCollection dataset = new XYSeriesCollection();
    public XYPlot prepareChart(double[] f, String key) {
        int i = 0;
        XYSeries series = new XYSeries(key);
        for (double d : f) {
            series.add(i++, d);
        }
        dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createXYLineChart("APPROXIMATION", "X", "Y", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1280, 900));
        setContentPane(chartPanel);
        XYPlot plot = chart.getXYPlot();
        ((AbstractRenderer) plot.getRenderer()).setAutoPopulateSeriesStroke(false);
        ((AbstractRenderer) plot.getRenderer()).setSeriesStroke(0, new BasicStroke(2.0f));
        for (int j = 1; j < 6; j++) {
            ((AbstractRenderer) plot.getRenderer()).setSeriesStroke(j, new BasicStroke(1.0f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {10.0f}, 0.0f));
        }
        plot.setForegroundAlpha(0.5f);
        return plot;
    }
}
