package com.icss.hr.job.service;

import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.icss.hr.job.dao.JobDao;
import com.icss.hr.job.pojo.Job;

/**
 * 职务的Service
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
	 * 根据传入的输出流把职务表数据转换为excel文件数据
	 * @throws Exception 
	 * 
	 */
	public void writeJobExcel(OutputStream out) throws Exception {
		//返回职务的数据集合
		ArrayList<Job> list = dao.query();
		
		//工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		
		//工作表
		HSSFSheet sheet = wb.createSheet("职务数据");
		
		//标题行
		HSSFRow titleRow = sheet.createRow(0);
		
		titleRow.createCell(0).setCellValue("职务编号");
		titleRow.createCell(1).setCellValue("职务名称");
		titleRow.createCell(2).setCellValue("最低工资");
		titleRow.createCell(3).setCellValue("最高工资");
		
		//数据行
		for (int i = 1;i <= list.size();i ++) {
			Job job = list.get(i - 1);
			HSSFRow row = sheet.createRow(i);
			
			row.createCell(0).setCellValue(job.getJobId());
			row.createCell(1).setCellValue(job.getJobName());
			row.createCell(2).setCellValue(job.getJobMinSal());
			row.createCell(3).setCellValue(job.getJobMaxSal());
			
		}
		
		//写入数据流
		wb.write(out);
	}
}
