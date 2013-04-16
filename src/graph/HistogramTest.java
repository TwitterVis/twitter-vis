package graph;

import core.clusterObject;
import core.clusterXml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.xml.sax.SAXException;

public class HistogramTest extends JFrame {

    private static final long serialVersionUID = 1L;
    public ArrayList<Integer> value = new ArrayList();

    public HistogramTest(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        // This will create the dataset 
        // PieDataset dataset = createDataset();
        // based on the dataset we create the chart


        HistogramDataset dataset3 = new HistogramDataset();
        dataset3 = createDataset();
        dataset3.setType(HistogramType.SCALE_AREA_TO_1);
        JFreeChart chart = createChart(dataset3, chartTitle);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(1366, 768));
        // add it to our application
        setContentPane(chartPanel);
        setVisible(true);

    }

    /**
     * Creates a sample dataset
     */
    private HistogramDataset createDataset() {
        clusterXml cluster = new clusterXml("STC");
        ArrayList<clusterObject> clusterObjects = new ArrayList<clusterObject>();
        try {
            clusterObjects = cluster.clusterXmlFile("src//core//master.xml");
        } catch (IOException ex) {
            Logger.getLogger(PieChart.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(PieChart.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(PieChart.class.getName()).log(Level.SEVERE, null, ex);
        }

        HistogramDataset result = new HistogramDataset();
        for (int i = 0; i < clusterObjects.size(); i++) {
            value.add(clusterObjects.get(i).getNumberRetweets());
        }
        result.setType(HistogramType.RELATIVE_FREQUENCY);

        double[] value2 = new double[value.size()];
        for (int i = 0; i < value2.length; i++) {
            value2[i] = value.get(i).intValue();
        }
        result.addSeries("Histogram", value2, value2.length);
        return result;

    }

    /**
     * Creates a chart
     */
    private JFreeChart createChart(HistogramDataset dataset2, String title) {


        String plotTitle = "Histogram";
        String xaxis = "number";
        String yaxis = "value";
        PlotOrientation orientation = PlotOrientation.VERTICAL;
        boolean show = false;
        boolean toolTips = false;
        boolean urls = false;
        JFreeChart chart = ChartFactory.createHistogram(plotTitle, xaxis, yaxis,
                dataset2, orientation, show, toolTips, urls);

        return chart;

    }
}
