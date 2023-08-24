import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import java.io.IOException;
import java.util.Arrays;

public class Chart {
    private final String title;
    private final double[] xAxis;

    private final XYChart chart;

    public Chart(String title, int[] xAxis, String xAxisTitle, String yAxisTitle) {
        this.title = title;
        this.xAxis = Arrays.stream(xAxis).asDoubleStream().toArray();

        chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .title(title)
                .yAxisTitle(yAxisTitle)
                .xAxisTitle(xAxisTitle)
                .build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
    }

    public void addData(String name, double[] data){
        chart.addSeries(name, xAxis, data);
    }

    public void save(){
        try {
            BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            System.err.print("Error in saving chart");
        }
    }

    public void show(){
        new SwingWrapper(chart).displayChart();
    }
}
