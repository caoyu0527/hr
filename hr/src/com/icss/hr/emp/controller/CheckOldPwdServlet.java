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
 *�жϾ������Ƿ���ȷ����ȷ��Ӧyes,������Ӧno
 */
@WebServlet("/CheckOldPwdServlet")
public class CheckOldPwdServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//��������
		PrintWriter out = response.getWriter();
		
		//��õ�ǰ�û���
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		
		//��þ�����
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
