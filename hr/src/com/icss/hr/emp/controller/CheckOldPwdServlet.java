package com.icss.hr.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.hr.emp.service.EmpService;

/**
 *判断旧密码是否正确，正确响应yes,错误响应no
 */
@WebServlet("/CheckOldPwdServlet")
public class CheckOldPwdServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//获得输出流
		PrintWriter out = response.getWriter();
		
		//获得当前用户名
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		
		//获得旧密码
		String oldPwd = request.getParameter("oldPwd");
		
		EmpService service = new EmpService();
		
		try {
			String empPwd = service.queryEmpPwd(empLoginName);
			
			if(empPwd.equals(oldPwd)) {
				out.print("yes");
			}else {
				out.print("no");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
