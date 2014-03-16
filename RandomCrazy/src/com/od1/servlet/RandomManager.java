package com.od1.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RandomManager
 */
@WebServlet("/RandomManager")
public class RandomManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DIRECTORY_SIMPLE = "randoms/save/simple";

	private enum RandomType {
		SIMPLE, MULTIPLE;
	}

	private enum RandomFunction {
		CREATE, SAVE, LOAD, DELETE, LIST;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RandomManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String params = (String) request.getParameter("params");
			ArrayList<String> tempParams = new ArrayList<String>();
			for (String p : params.split(",")) {
				tempParams.add(p);
			}
			if (tempParams.size() > 1)
				managerRandom(request, response, tempParams);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param type
	 */
	protected void managerRandom(HttpServletRequest request, HttpServletResponse response, ArrayList<String> params) throws ServletException, IOException {
		try{
			StringBuilder sb = new StringBuilder();
			if (params.get(0).equals(RandomType.SIMPLE.toString())) {
				if (params.get(1).equals(RandomFunction.LIST.toString())) {
					File folder = new File(request.getServletContext().getRealPath(DIRECTORY_SIMPLE));
					String[] listOfFiles = folder.list();
					for (int i = 0; i < listOfFiles.length; i++) {
						sb.append(listOfFiles[i].split("\\.")[0] + "~");
					}
				}else if(params.get(1).equals(RandomFunction.LOAD.toString())){
					@SuppressWarnings("resource")
					BufferedReader in = new BufferedReader(new FileReader(request.getServletContext().getRealPath(DIRECTORY_SIMPLE + "/" + params.get(2)+".in")));
					for (String line; (line=in.readLine())!=null;) {
						sb.append(line + "~");
					}
				}
			} else if (params.get(1).equals(RandomType.MULTIPLE.toString())) {
	
			} else {
				response.getWriter().write("ERROR TYPE RANDOM");
			}
			response.getWriter().write(sb.substring(0, sb.length()-1).toString());
			response.setCharacterEncoding("UTF-8");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
