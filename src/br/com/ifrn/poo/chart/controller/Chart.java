package br.com.ifrn.poo.chart.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Chart {
	public void pieChartGen(CsvReader csv) {
		csv.setData();
		ArrayList<String> param1 = csv.getName();
		ArrayList<Integer> param2  = csv.getCout();
		DefaultPieDataset datas = new DefaultPieDataset();
		for(int i = 0; i< param1.size(); i++) {
			datas.setValue(param1.get(i), param2.get(i));
		}
		JFreeChart chart = ChartFactory.createPieChart("Quantidade de quartos/casas disponiveis por bairro", datas, true,false,false);
		PiePlot plot = (PiePlot)chart.getPlot();
		plot.setSimpleLabels(false);
		//plot.setLabelGenerator(null);
		int width = 1280;    /* Width of the image */
		int height = 800;   /* Height of the image */ 
		File pieChart = new File( "PieChart.jpeg" ); 
		try {
			ChartUtilities.saveChartAsJPEG(pieChart ,chart, width ,height);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void ChartGenBar3D(CsvReader csv) {
		csv.setData();
		ArrayList<String> param1 = csv.getName();
		ArrayList<Integer> param2  = csv.getCout();
		DefaultCategoryDataset datas = new DefaultCategoryDataset();
		for(int i = 0; i<param1.size();i++ ) {
			datas.setValue((double)param2.get(i),param1.get(i),"");
		}
		
		
		JFreeChart barChart = ChartFactory.createBarChart3D("Quantidade de quartos/casas disponiveis por bairro", "Bairros", "Quantidade de casas/quartos por bairro", datas);
		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setRange(0, 2000);
		int width = 1280;    /* Width of the image */
		int height = 800;   /* Height of the image */ 
		File barChartt = new File( "BarChart3D.jpeg" ); 
		try {
			ChartUtilities.saveChartAsJPEG(barChartt ,barChart, width ,height);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	public void ChartGenBar(CsvReader csv) {
		csv.setData();
		ArrayList<String> param1 = csv.getName();
		ArrayList<Integer> param2  = csv.getCout();
		DefaultCategoryDataset datas = new DefaultCategoryDataset();
		for(int i = 0; i<param1.size();i++ ) {
			datas.setValue((double)param2.get(i),param1.get(i),"");
		}
		JFreeChart barChart = ChartFactory.createBarChart("Quantidade de quartos/casas disponiveis por bairro", "Bairros", "Quantidade de casas/quartos por bairro", datas);
		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setRange(0, 2000);
		int width = 1280;    /* Width of the image */
		int height = 800;   /* Height of the image */ 
		File barChartt = new File( "BarChart.jpeg" ); 
		try {
			ChartUtilities.saveChartAsJPEG(barChartt ,barChart, width ,height);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


}
}
