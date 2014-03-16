package com.od1.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.od1.random.RandomSpots;
import com.od1.random.RandomSpotsV2;

/**
 * Servlet implementation class RandomGenerator
 */
@WebServlet("/RandomGenerator")
public class RandomGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static RandomEquips re = new RandomEquips();
	private static RandomSpots rs = new RandomSpots();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RandomGenerator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String params = (String) request.getParameter("params");
			@SuppressWarnings("unused")
			String load = (String) request.getParameter("load");
			ArrayList<String> od1enses = new ArrayList<String>();
			for (String p : params.split(",")) {
				od1enses.add(p);
			}
			ArrayList<String> list = (ArrayList<String>) RandomSpotsV2.generateRandomCrazy(od1enses);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(rs.createRandomSpots(request, list));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

}
