package br.com.ifrn.poo.chart.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
	private ArrayList<String> name = new ArrayList<String>();
	private ArrayList<Integer> cout = new ArrayList<Integer>();
	private String atributes[];
	private ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
	private String file;
	public CsvReader(String csvFile) {
    	this.setFile(csvFile);

    	BufferedReader br = null;
    	
    	String line = "";

    	boolean l = false;

    	try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				if (l) {
					ArrayList<String> parsedLine = parseLine(line,br);
					lines.add(parsedLine);
				} else {
					setAtributes(new String[line.split(",").length]);
					setAtributes(line.split(","));
					l = !l;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	 public String[] getAtributes() {
		return atributes;
	}
	public void setAtributes(String atributes[]) {
		this.atributes = atributes;
	}
	public ArrayList<Integer> getCout() {
		return cout;
	}
	public ArrayList<String> getName() {
		return name;
	}
	private static ArrayList<String> parseLine (String csvLine, BufferedReader br) {
		// Tratamento de cada linha do .csv;
    	char line[] = csvLine.toCharArray();
    	char separator = ',';
    	char blockQuote = '\"';
    	boolean inQuotes = false;
    	ArrayList<String> parsedLine = new ArrayList<String>();
    	String current = "";

    	for (int i = 0; i < line.length; i++) {
    		if (!inQuotes && line[i] != blockQuote) {
    			if (line[i] == separator) {
	    			parsedLine.add(current);
	    			current = "";
	    		} else {
	    			current += line[i];
	    		}
    		} else {
    			if (line[i] == blockQuote) {
    				inQuotes = !inQuotes;
    			} else {
    				current += line[i];
                    if (i == line.length-1) { // // reseta o loop caso o final da linha terminar com blockQuote ou caso a linha tiver um /n ou /r
                        try {
                            
                            if ((csvLine = br.readLine()) != null) {
                            	line = csvLine.toCharArray();
                            	i = -1;
                            	current += '\n';
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
    			}
    		}
    	}
        parsedLine.add(current);

    	return parsedLine;
    }
	public void setData() {
		int idx = 5;
				//java.util.Arrays.asList(atributes).indexOf("neighbourhood");
		name = new ArrayList<String>();
		cout = new ArrayList<Integer>();
		for(List<String> i : this.lines) {
			try { // caso exista alguma linha mal formatada e que não foi tratada pelo parseLine()
				String flag = i.get(idx);
				 if (name.indexOf(flag) >= 0) {
		                cout.set(name.indexOf(flag), cout.get(name.indexOf(flag)) + 1);
		            } else {
		                name.add(flag);
		                cout.add(1);
		            }
				
			} catch(Exception e) {
				System.out.println("Problema no arquivo, linha: " + i);
			}
		}
	}
	public ArrayList<ArrayList<String>> getLines() {
		return lines;
	}
	
	

}
