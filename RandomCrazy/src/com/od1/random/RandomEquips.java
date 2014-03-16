package com.od1.random;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class RandomEquips {

	private static ArrayList<String> equipos;
	private static ArrayList<String> od1enses;
	private static boolean v[];
	private static boolean vEquipos[];
	private static int cont;
	private static int min = 0;
	private static BufferedReader in;

	public String createRandomEquipos() throws Throwable {
		StringBuilder sb = new StringBuilder();
		try {
			File x = new File("in.in");
			x.createNewFile();
			in = new BufferedReader(new FileReader("randoms/default/in.in"));
			od1enses = new ArrayList<String>();
			for (String line; (line=in.readLine())!=null;) {
				od1enses.add(line);
			}
			in = new BufferedReader(new FileReader("randoms/default/equipos.in"));
			equipos = new ArrayList<String>();
			for (String line; (line=in.readLine())!=null;) {
				equipos.add(line);
			}

			if( od1enses.size() > equipos.size() )
				throw new Exception("Los equipos deben ser suficientes para los jugadores...");
			else {
				int max = od1enses.size()-1;
				int maxE = equipos.size()-1;
				v = new boolean[od1enses.size()];
				vEquipos = new boolean[equipos.size()];
				cont = 0;
				do {
					int posOd1 = min + (int)(Math.random() * ((max - min) + 1));
					int posEqu = min + (int)(Math.random() * ((maxE - min) + 1));
					if( !v[posOd1] && !vEquipos[posEqu] ){
						cont++;
						v[posOd1] = true;
						vEquipos[posEqu] = true;
						sb.append( od1enses.get(posOd1) + " --> " + equipos.get(posEqu) + "\n");
					}
				}while( cont < od1enses.size() );
			}
		} finally {
			if(in != null)
			in.close();
		}
		return new String(sb);
	}
}
