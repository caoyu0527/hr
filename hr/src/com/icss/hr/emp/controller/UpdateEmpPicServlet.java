package com.icss.hr.emp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.hr.emp.service.EmpService;

/**
 * �����û���ͷ��base64����
 */
@WebServlet("/UpdateEmpPicServlet")
public class UpdateEmpPicServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//����û�ͷ������
		String empPic = request.getParameter("empPic");
		
		//����û���
		HttpSession session = request.getSession();
		String empLoginName = (String)session.getAttribute("empLoginName");
		
		//����ҵ����
		EmpService service = new EmpService();
		
		try {
			service.updateEmpPic(empLoginName, empPic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
