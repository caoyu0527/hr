package com.icss.hr.job.service;

import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.icss.hr.job.dao.JobDao;
import com.icss.hr.job.pojo.Job;

/**
 * ְ���Service
 * @author Administrator
 *
 */
public class JobService {
	
	private JobDao dao = new JobDao();
	
	public void addJob(Job job) throws Exception {
		dao.insert(job);
	}
	
	public void updateJob(Job job) throws Exception {
		dao.update(job);
	}
	public void deleteJob(Integer jobId) throws Exception {
		dao.delete(jobId);
	}
	
	public Job queryJobById(Integer jobId) throws Exception {
		return dao.queryById(jobId);
	}
	
	public ArrayList<Job> queryJob() throws Exception {
		return dao.query();
	}
	
	/**
	 * ���ݴ�����������ְ�������ת��Ϊexcel�ļ�����
	 * @throws Exception 
	 * 
	 */
	public void writeJobExcel(OutputStream out) throws Exception {
		//����ְ������ݼ���
		ArrayList<Job> list = dao.query();
		
		//������
		HSSFWorkbook wb = new HSSFWorkbook();
		
		//������
		HSSFSheet sheet = wb.createSheet("ְ������");
		
		//������
		HSSFRow titleRow = sheet.createRow(0);
		
		titleRow.createCell(0).setCellValue("ְ����");
		titleRow.createCell(1).setCellValue("ְ������");
		titleRow.createCell(2).setCellValue("��͹���");
		titleRow.createCell(3).setCellValue("��߹���");
		
		//������
		for (int i = 1;i <= list.size();i ++) {
			Job job = list.get(i - 1);
			HSSFRow row = sheet.createRow(i);
			
			row.createCell(0).setCellValue(job.getJobId());
			row.createCell(1).setCellValue(job.getJobName());
			row.createCell(2).setCellValue(job.getJobMinSal());
			row.createCell(3).setCellValue(job.getJobMaxSal());
			
		}
		
		//д��������
		wb.write(out);
	}
}
