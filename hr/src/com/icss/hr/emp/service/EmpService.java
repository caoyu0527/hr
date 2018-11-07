package com.icss.hr.emp.service;

import java.util.ArrayList;

import com.icss.hr.common.Pager;
import com.icss.hr.emp.dao.EmpDao;
import com.icss.hr.emp.pojo.Emp;

/**
 * Ա��Service
 * @author Administrator
 *
 */
public class EmpService {
	
	private EmpDao dao = new EmpDao();
	
	/**
	 * ��½����֤
	 * ����1�û������� 2������� 3��¼�ɹ�
	 * @throws Exception 
	 */
	public int checkLogin(String empLoginName,String empPwd) throws Exception {
		
		Emp emp = dao.queryByName(empLoginName);
		
		if (emp == null) {
			return 1;
		}else if (!empPwd.equals(emp.getEmpPwd())) {
			return 2;
		}
		return 3;
	}
	
	/**
	 * ����û����Ƿ����
	 * ���ڷ���true�������ڷ���false
	 * @throws Exception 
	 */
	
	public boolean checkLoginName(String  empLoginName) throws Exception{
		
		Emp emp = dao.queryByName(empLoginName);
		
		if (emp == null) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * ������Ա��
	 * @throws Exception 
	 */
	
	public void addEmp(Emp emp) throws Exception {
		
		dao.insert(emp);
	}
	
	/**
	 * ͨ��id��ѯԱ������
	 * @throws Exception 
	 */
	public Emp queryEmpById(Integer empId) throws Exception {
		return dao.queryById(empId);
	}
	
	/**
	 * ����Ա���ܼ�¼��
	 * @throws Exception 
	 */
	public int getEmpCount() throws Exception {
		return dao.getCount();
	}
 	
	/**
	 * ��ҳ��ѯԱ��
	 * @throws Exception 
	 */
	public ArrayList<Emp> queryEmpByPager(Pager pager) throws Exception {
		return dao.queryByPage(pager);
	}
	
	/**
	 * �޸�Ա��
	 * @throws Exception 
	 */
	public void updateEmp(Emp emp) throws Exception {
		dao.update(emp);
	}
	
	/**
	 * ɾ��Ա��
	 * @throws Exception 
	 */
	public void deleteEmp(Integer empId) throws Exception {
		dao.delete(empId);
	}
	
	/**
	 * ����ͷ��
	 * @throws Exception 
	 */
	public void updateEmpPic(String  empLoginName,String empPic) throws Exception {
		dao.updateEmpPic(empLoginName, empPic);
	}
	
	/**
	 * ��ѯͷ��
	 * @throws Exception 
	 */
	public String queryEmpPic(String empLoginName) throws Exception {
		return dao.queryEmpPic(empLoginName);
	}
	/**
	 * �޸�����
	 * @throws Exception 
	 */
	public void updateEmpPwd(String empLoginName,String empPwd) throws Exception {
		dao.updateEmpPwd(empLoginName, empPwd);
	}
	
	/**
	 * ��ѯ���ص�ǰ����
	 * @throws Exception 
	 */
	public String queryEmpPwd(String empLoginName) throws Exception {
		
		return dao.queryByName(empLoginName).getEmpPwd();
	}
}
