package com.icss.hr.analyze.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.icss.hr.analyze.dao.AnaDao;
import com.icss.hr.analyze.dto.DeptEmpCount;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Êý¾Ý·ÖÎöservice
 * @author Administrator
 *
 */
public class AnaService {

	private AnaDao dao = new AnaDao();
	
	public ArrayList<DeptEmpCount> queryEmpCount() throws Exception {
		
		return dao.queryEmpCount();
	}
	
	public ArrayList<HashMap<String, Object>> queryAvgSal() throws Exception {
		
		return dao.queryAvgSal();
	}
	
	public ArrayList<HashMap<String, Object>> queryDeptMinMaxSal() throws Exception {
		
		return dao.queryMinMaxSal();
	}
	
}
