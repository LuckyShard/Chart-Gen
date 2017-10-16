package br.com.ifrn.poo.chart.view;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import br.com.ifrn.poo.chart.controller.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		CsvReader file = null;
		String fbConfirm;
		String nome;
		boolean loop = true;

		while (loop) {
			imprimirMenu();
			System.out.println("--------------------------------------------------");
			switch (sc.nextInt()) {
			case 0:
				loop = false;
				break;
			case 1:
				System.out.println("--------------------------------------------------");
				System.out.println("Digite o nome do csv:");
				nome = sc.next();
				System.out.println("--------------------------------------------------");
				file = new CsvReader(nome + ".csv");
				break;
			case 2:
				System.out.println("--------------------------------------------------");
				imprimirBairro(file);
				System.out.println("--------------------------------------------------");
				break;
			case 3:
				chartGenPie(file);
				System.out.println("Deseja publicar esse grafico no Facebook? (S/N) ");
				fbConfirm = sc.next();
				switch(fbConfirm) {
				case "S" :
					String file_name = "PieChart.jpeg";
					String path = "";
					Post(path,file_name);
					
					break;
				case "N" :
					System.out.println("Grafico nao postado, mas salvo ;)");
					break;
				
				}
				
				break;
			case 4:
				ChartGenBar3D(file);
				System.out.println("Deseja publicar esse grafico no Facebook? (S/N) ");
				fbConfirm = sc.next();
				switch(fbConfirm) {
				case "S" :
					String file_name = "BarChart3D.jpeg";
					String path = "";
					Post(path,file_name);
					
					break;
				case "N" :
					System.out.println("Grafico nao postado, mas salvo ;)");
					break;
				
				}
				break;
			case 5:
				ChartGenBar(file);
				System.out.println("Deseja publicar esse grafico no Facebook? (S/N) ");
				fbConfirm = sc.next();
				switch(fbConfirm) {
				case "S" :
					String file_name = "BarChart.jpeg";
					String path = "";
					Post(path,file_name);
					
					break;
				case "N" :
					System.out.println("Grafico nao postado, mas salvo ;)");
					break;
				
				}
				break;
			
			}
		}
		sc.close();

	}

	private static String moreThanAWord() { // ler mais de uma linha para a inserção da msg
		Scanner scanner = new Scanner(System.in);
	    return scanner.nextLine();
	}
	private static void imprimirMenu () {
		System.out.println("--------------------------------------------------");
		System.out.println("1 - Escolher Dataset");
		System.out.println("2 - Relacao Bairro x quantidade de casas/quartos");
		System.out.println("3 - Grafico Pizza/Torta");
		System.out.println("4 - Grafico em Barras 3D");
		System.out.println("5 - Grafico em Barras 2D");
		System.out.println("0 - Sair");
	}
	private static void imprimirBairro(CsvReader csv) {
		csv.setData();
		ArrayList<String> param1 = csv.getName();
		ArrayList<Integer> param2  = csv.getCout();
		for(int i=0; i< param1.size();i++) {
			System.out.println(param1.get(i) + " : "+param2.get(i));

		}
	}
	private static void chartGenPie(CsvReader csv) {
		Chart c = new Chart();
		c.pieChartGen(csv);

	}
	private static void ChartGenBar3D(CsvReader csv) {
		Chart c = new Chart();
		c.ChartGenBar3D(csv);
	}
	private static void ChartGenBar(CsvReader csv) {
		Chart c = new Chart();
		c.ChartGenBar(csv);
	}
	private static void Post(String path , String file_name) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Insira o seu token de acesso:");
		String to = sc.next();
		System.out.println("ID do amiguinho que deseja marcar:");
		String idf = sc.next();
		System.out.println("Nome do amiguinho que vai ser marcado:");
		String namef = sc.next();
		PostTag pho = new PostTag(idf,namef);
		System.out.println("Post :");
		String mes = moreThanAWord();
		FacebookController fb = new FacebookController();
		try {
			fb.postIt(to, path, file_name, mes, pho);
			System.out.println("Grafico postado com sucesso!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

