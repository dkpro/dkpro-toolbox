package dkpro.toolbox.tools.chart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.ConditionalFrequencyDistribution;

public class FrequencyChart
    extends ApplicationFrame
{

    private static final long serialVersionUID = 1L;

    static class CfdPanel
        extends JPanel
    {
        private static final long serialVersionUID = 1L;
        
        private CategoryDataset data;
        private String title;

        private CategoryDataset createData(ConditionalFrequencyDistribution<String, String> cfd)
        {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            for (String condition : cfd.getConditions()) {
                List<String> keys = new ArrayList<String>(cfd.getFrequencyDistribution(condition).getKeys());
                Collections.sort(keys);
                for (String key : keys) {
                    dataset.addValue(cfd.getFrequencyDistribution(condition).getCount(key), condition, key);
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

        public CfdPanel(String title, ConditionalFrequencyDistribution<String, String> cfd)
        {
            super(new BorderLayout());
            data = createData(cfd);
            this.title = title;
            add(createContent());
        }
    }

    public FrequencyChart(String title, ConditionalFrequencyDistribution<String, String> cfd)
    {
        super(title);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        JPanel jpanel = createPanel(title, cfd);
        getContentPane().add(jpanel);
    }

    public static JPanel createPanel(String title, ConditionalFrequencyDistribution<String, String> cfd)
    {
        return new CfdPanel(title, cfd);
    }
}