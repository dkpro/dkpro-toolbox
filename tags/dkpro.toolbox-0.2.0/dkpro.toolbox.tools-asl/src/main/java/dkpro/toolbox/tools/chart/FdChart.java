package dkpro.toolbox.tools.chart;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;

import dkpro.toolbox.core.util.FD;

public class FdChart
    extends ApplicationFrame
{

    private static final long serialVersionUID = 1L;

    static class FdPanel
        extends JPanel
    {
        private static final long serialVersionUID = 1L;
        
        private CategoryDataset data;
        private String title;

        private CategoryDataset createData(FD<String> fd, boolean cumulative)
        {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            int cumulativeCount = 0;
            for (String key : fd.getMostFrequentSamples(fd.getKeys().size())) {
                if (cumulative) {
                    cumulativeCount += fd.getCount(key);
                    dataset.addValue(cumulativeCount, "fd", key);
                }
                else {
                    dataset.addValue(fd.getCount(key), "fd", key);
                }
            }
            return dataset;
        }

        private JTabbedPane createContent()
        {
            JTabbedPane jtabbedpane = new JTabbedPane();
            jtabbedpane.add("Chart:", createChart());
            return jtabbedpane;
        }

        private ChartPanel createChart()
        {
            CategoryAxis xAxis = new CategoryAxis("Keys");
            ValueAxis yAxis = new NumberAxis("Frequency");
            CategoryItemRenderer renderer = new LineAndShapeRenderer();
            CategoryPlot plot = new CategoryPlot(data, xAxis, yAxis, renderer);
            plot.setBackgroundPaint(Color.white);
            plot.setDomainGridlinePaint(Color.white);
            plot.setRangeGridlinePaint(Color.white);
            plot.setAxisOffset(new RectangleInsets(4D, 4D, 4D, 4D));
            JFreeChart jfreechart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, true);
            jfreechart.setBackgroundPaint(Color.white);
            ChartPanel chartpanel = new ChartPanel(jfreechart, false);
            return chartpanel;
        }

        public FdPanel(String title, FD<String> fd, boolean cumulative)
        {
            super(new BorderLayout());
            data = createData(fd, cumulative);
            this.title = title;
            add(createContent());
        }
    }

    public FdChart(String title, FD<String> fd, boolean cumulative)
    {
        super(title);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        JPanel jpanel = createPanel(title, fd, cumulative);
        getContentPane().add(jpanel);
    }

    public static JPanel createPanel(String title, FD<String> fd, boolean cumulative)
    {
        return new FdPanel(title, fd, cumulative);
    }
}