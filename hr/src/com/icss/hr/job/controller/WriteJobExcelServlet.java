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
 * 下载职务Excel报表
 */
@WebServlet("/WriteJobExcelServlet")
public class WriteJobExcelServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//中文文件转码
		String filename = new String("职务数据.xls".getBytes(),"iso-8859-1");
		
		//设置浏览器以附件形式接收数据（下载文件）
		response.setHeader("content-disposition", "attachment;filename=" + filename);
		
		//设置响应的类型为二进制类型
		response.setContentType("application/octet-stream");
		
		//响应的输出流
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
