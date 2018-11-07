package com.icss.hr.emp.service;

import java.util.ArrayList;

import com.icss.hr.common.Pager;
import com.icss.hr.emp.dao.EmpDao;
import com.icss.hr.emp.pojo.Emp;

/**
 * 员工Service
 * @author Administrator
 *
 */
public class EmpService {
	
	private EmpDao dao = new EmpDao();
	
	/**
	 * 登陆了验证
	 * 返回1用户不存在 2密码错误 3登录成功
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
	 * 检查用户名是否存在
	 * 存在返回true，不存在返回false
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
	 * 增加新员工
	 * @throws Exception 
	 */
	
	public void addEmp(Emp emp) throws Exception {
		
		dao.insert(emp);
	}
	
	/**
	 * 通过id查询员工数据
	 * @throws Exception 
	 */
	public Emp queryEmpById(Integer empId) throws Exception {
		return dao.queryById(empId);
	}
	
	/**
	 * 返回员工总记录数
	 * @throws Exception 
	 */
	public int getEmpCount() throws Exception {
		return dao.getCount();
	}
 	
	/**
	 * 分页查询员工
	 * @throws Exception 
	 */
	public ArrayList<Emp> queryEmpByPager(Pager pager) throws Exception {
		return dao.queryByPage(pager);
	}
	
	/**
	 * 修改员工
	 * @throws Exception 
	 */
	public void updateEmp(Emp emp) throws Exception {
		dao.update(emp);
	}
	
	/**
	 * 删除员工
	 * @throws Exception 
	 */
	public void deleteEmp(Integer empId) throws Exception {
		dao.delete(empId);
	}
	
	/**
	 * 更新头像
	 * @throws Exception 
	 */
	public void updateEmpPic(String  empLoginName,String empPic) throws Exception {
		dao.updateEmpPic(empLoginName, empPic);
	}
	
	/**
	 * 查询头像
	 * @throws Exception 
	 */
	public String queryEmpPic(String empLoginName) throws Exception {
		return dao.queryEmpPic(empLoginName);
	}
	/**
	 * 修改密码
	 * @throws Exception 
	 */
	public void updateEmpPwd(String empLoginName,String empPwd) throws Exception {
		dao.updateEmpPwd(empLoginName, empPwd);
	}
	
	/**
	 * 查询返回当前密码
	 * @throws Exception 
	 */
	public String queryEmpPwd(String empLoginName) throws Exception {
		
		return dao.queryByName(empLoginName).getEmpPwd();
	}
}
