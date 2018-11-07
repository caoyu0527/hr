package com.icss.hr.analyze.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.icss.hr.analyze.dto.DeptEmpCount;
import com.icss.hr.analyze.service.AnaService;

/**
 * 部门员工人数查询
 * @author Administrator
 *
 */
@WebServlet("/QueryEmpCountServlet")
public class QueryEmpCountServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
		AnaService service = new AnaService();
		
		ArrayList<DeptEmpCount> list;
		try {
			list = service.queryEmpCount();
			Gson gson = new Gson();
			
			String json = gson.toJson(list);
			
			out.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
