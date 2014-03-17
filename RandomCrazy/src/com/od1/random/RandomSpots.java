package com.od1.random;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RandomSpots {

	static ArrayList<String> od1enses;
	static boolean vo[];

	@SuppressWarnings({ "unused", "resource" })
	public String createRandomSpots(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new FileReader(request.getServletContext().getRealPath("randoms/default/in.in")));
		od1enses = new ArrayList<String>();
		for (String line; (line = in.readLine()) != null;) {
			od1enses.add(line);
		}
		vo = new boolean[od1enses.size()];
		int spot = 1;
		int min = 0;
		int max = od1enses.size() - 1;
		int cont = od1enses.size();
		Random r = new Random();
		int posOd1 = min + (int) (Math.random() * ((max - min) + 1));

		while (cont > 0) {
			if (!vo[posOd1]) {
				// Thread.sleep(1000);
				sb.append(od1enses.get(posOd1) + "	" + spot + "\n");
				vo[posOd1] = true;
				cont--;
				spot++;
			} else {
				posOd1 = min + (int) (Math.random() * ((max - min) + 1));
			}
		}
		System.out.println();
		in = new BufferedReader(new FileReader(request.getServletContext().getRealPath("randoms/default/oficina.in")));
		for (String line; (line = in.readLine()) != null;) {
			sb.append(line + "\n");
		}

		return new String(sb);
	}

	@SuppressWarnings("resource")
	public String createRandomSpots(HttpServletRequest request, ArrayList<String> od1enses) throws Throwable {
		StringBuilder sb = new StringBuilder();
		int spot = 1;
		for (String od1ense : od1enses) {
			sb.append(od1ense + "	" + spot + "\n");
			spot++;
		}
		System.out.println();
		BufferedReader in = new BufferedReader(new FileReader(request.getServletContext().getRealPath("randoms/default/oficina.in")));
		for (String line; (line = in.readLine()) != null;) {
			sb.append(line + "\n");
		}

		return new String(sb);
	}
}
