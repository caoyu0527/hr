package com.icss.hr.dept.service;

import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.icss.hr.dept.dao.DeptDao;
import com.icss.hr.dept.pojo.Dept;

/**
 * ����Service
 * @author Administrator
 *
 */
public class DeptService {
	
	private DeptDao dao = new DeptDao();
	
	public void addDept(Dept dept) throws Exception {
		dao.insert(dept);
	}
	
	public void updateDept(Dept dept) throws Exception {
		dao.update(dept);
	}
	
	public void deleteDept(Integer deptId) throws Exception {
		dao.delete(deptId);
	}
	
	public Dept queryDeptById(Integer deptId) throws Exception {
		return dao.queryById(deptId);
	}
	
	public ArrayList<Dept> queryDept() throws Exception {
		return dao.query();
	}
	
	/**
	 * ���ݴ����������Ѳ��ű�����ת��Ϊexcel�ļ�����
	 * @throws Exception 
	 * 
	 */
	public void writeExcel(OutputStream out) throws Exception {
		//���ز��ŵ����ݼ���
		ArrayList<Dept> list = dao.query();
		
		//������
		HSSFWorkbook wb = new HSSFWorkbook();
		
		//������
		HSSFSheet sheet = wb.createSheet("��������");
		
		//������
		HSSFRow titleRow = sheet.createRow(0);
		
		titleRow.createCell(0).setCellValue("���ű��");
		titleRow.createCell(1).setCellValue("��������");
		titleRow.createCell(2).setCellValue("���ŵ�ַ");
		
		//������
		for (int i = 1;i <= list.size();i ++) {
			Dept dept = list.get(i - 1);
			HSSFRow row = sheet.createRow(i);
			row.createCell(0).setCellValue(dept.getDeptId());
			row.createCell(1).setCellValue(dept.getDeptName());
			row.createCell(2).setCellValue(dept.getDeptLoc());
		}
		
		//д��������
		wb.write(out);
		
		
	}
}
