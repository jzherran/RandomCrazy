package com.od1.random;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomSpotsV2 {
	static List<String> od1enses;
	static int[][] m;
	static BufferedReader conf1;
	static BufferedReader conf2;

	public static List<String> generateRandomCrazy(List<String> od1enses) throws Throwable {
		try {
			readByFile();
			RandomSpotsV2.od1enses = od1enses;
			readConfiguration();
			RandomSpotsV2.od1enses = generateConfiguration();
			renameAndWriteConfFiles();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RandomSpotsV2.od1enses;
	}

	private static void renameAndWriteConfFiles() throws Throwable {
		if( conf1 != null ) conf1.close();
		if( conf2 != null ) conf2.close();
		String name = "conf1.conf";
		
		File file = new File("conf1.conf");
		if(file.exists()){
			System.out.println("conf1 existe");
			File file2 = new File("conf2.conf");
			if( file2.exists() ){
				if( file.delete() ) System.out.println("BORRE");
				file = new File("conf1.conf");
				if( file2.renameTo(file) ){
					System.out.println("renombre! " +file2.getName());;
					name = "conf2.conf";
				}
			}else
				name = "conf2.conf";
		}
		StringBuilder sb = new StringBuilder( );
		for (String line : od1enses) {
			sb.append(line+"\n");
		}
		writeFile(sb, name);
	}

	public static void readByFile() throws Throwable {
		try {
			conf1 = new BufferedReader(new FileReader("conf1.conf"));
			conf2 = new BufferedReader(new FileReader("conf2.conf"));
		} catch (FileNotFoundException e) {
			if (conf1 == null)
				readByConsole();
		}
	}

	public static void readConfiguration() throws Throwable {
		if (od1enses == null && od1enses.isEmpty()) {
			@SuppressWarnings("resource")
			BufferedReader in = new BufferedReader(new FileReader("in.in"));
			od1enses = new ArrayList<String>();
			for (String line; (line = in.readLine()) != null;) {
				od1enses.add(line);
			}
		}

		m = new int[2][od1enses.size()];
		for (int i = 0; i < m.length; i++) {
			Arrays.fill(m[i], -1);
		}

		if (conf1 != null) {
			int k = 0;
			for (String line; (line = conf1.readLine()) != null; k++) {
				boolean encontre = false;
				for (int i = 0; i < od1enses.size() && !encontre; i++) {
					if (od1enses.get(i).equals(line)) {
						m[0][i] = k + 1;
						encontre = true;
					}
				}
			}
		}
		if (conf2 != null) {
			int k = 0;
			for (String line; (line = conf2.readLine()) != null; k++) {
				boolean encontre = false;
				for (int i = 0; i < od1enses.size() && !encontre; i++) {
					if (od1enses.get(i).equals(line)) {
						m[1][i] = k + 1;
						encontre = true;
					}
				}
			}
		}
	}

	public static ArrayList<String> generateConfiguration() {
		ArrayList<String> nLista = new ArrayList<String>();
		boolean[] visitados = new boolean[od1enses.size()];
		int min = 0;
		int max = od1enses.size() - 1;
		int cont = od1enses.size();
		int posOd1 = min + (int) (Math.random() * ((max - min) + 1));
		while (cont > 0) {
			if ((!visitados[posOd1] && (nLista.size() + 1) != m[0][posOd1] && (nLista.size()) != m[1][posOd1]) || cont == 1) {
				if (cont == 1)
					for (int i = 0; i < visitados.length; i++) {
						if (!visitados[i]) {
							posOd1 = i;
							break;
						}
					}
				nLista.add(od1enses.get(posOd1));
				visitados[posOd1] = true;
				cont--;
			}
			posOd1 = min + (int) (Math.random() * ((max - min) + 1));
		}
		return nLista;
	}

	public static void readByConsole() throws Throwable {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (String line; (line = in.readLine()) != null;) {
			sb.append(line + "\n");
		}
		writeFile(sb, "in.in");
	}

	public static void writeFile(StringBuilder data, String nameFile) {
		System.out.println(data + " " + nameFile);
		File file = new File(nameFile);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			PrintWriter pw = new PrintWriter(bw);
			pw.write(new String(data));
			pw.close();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
