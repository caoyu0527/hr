package com.icss.hr.job.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.job.service.JobService;

/**
 * ����ְ��Excel����
 */
@WebServlet("/WriteJobExcelServlet")
public class WriteJobExcelServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�����ļ�ת��
		String filename = new String("ְ������.xls".getBytes(),"iso-8859-1");
		
		//����������Ը�����ʽ�������ݣ������ļ���
		response.setHeader("content-disposition", "attachment;filename=" + filename);
		
		//������Ӧ������Ϊ����������
		response.setContentType("application/octet-stream");
		
		//��Ӧ�������
		OutputStream out = response.getOutputStream();
		
		JobService service = new JobService();
		
		try {
			service.writeJobExcel(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
