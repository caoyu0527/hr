package com.icss.hr.emp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.icss.hr.common.DbUtil;
import com.icss.hr.common.Pager;
import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.job.pojo.Job;
import com.sun.org.apache.regexp.internal.recompile;

/**
 * 员工Dao
 * @author Administrator
 *
 */
public class EmpDao {
	/**
	 * 插入数据
	 * @throws Exception 
	 */
	public void insert(Emp emp) throws Exception {
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "insert into emp values (emp_seq.nextval,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, emp.getEmpName());
		pstmt.setString(2, emp.getEmpLoginName());
		pstmt.setString(3, emp.getEmpPwd());
		pstmt.setString(4, emp.getEmpEmail());
		pstmt.setString(5, emp.getEmpPhone());
		pstmt.setDate(6, emp.getEmpHiredate());
		pstmt.setDouble(7, emp.getEmpSalary());
		pstmt.setInt(8, emp.getDept().getDeptId());
		pstmt.setInt(9, emp.getJob().getJobId());
		pstmt.setString(10, null);
		pstmt.setString(11, emp.getEmpInfo());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
	}
	
	/**
	 * 删除数据
	 * @throws Exception 
	 */
	
	public void delete(Integer empId) throws Exception {
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "delete from emp where emp_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, empId);
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	/**
	 * 分页查询
	 * @throws Exception 
	 */
	
	public ArrayList<Emp> queryByPage(Pager pager) throws Exception {
		ArrayList<Emp> list = new ArrayList<>();
		
		Connection conn = DbUtil.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM (SELECT ROWNUM RNUM,ee.* FROM (SELECT ");
		sql.append("               e.emp_id,");
		sql.append("               e.emp_name,");
		sql.append("               e.emp_email,");
		sql.append("               e.emp_phone,");
		sql.append("               d.dept_name,");
		sql.append("               j.job_name");
		sql.append("        FROM   emp e ");
		sql.append("LEFT   OUTER JOIN dept d ON e.emp_dept_id = d.dept_id ");
		sql.append("LEFT   OUTER JOIN job j ON e.emp_job_id = j.job_id ");
		sql.append("ORDER BY e.emp_id) ee) ");
		sql.append("WHERE  rnum BETWEEN ? AND ?");
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		
		pstmt.setInt(1, pager.getStart());
		pstmt.setInt(2, pager.getEnd());
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			
			Dept dept = new Dept();
			dept.setDeptName(rs.getString(6));
			
			Job job = new Job();
			job.setJobName(rs.getString(7));
			
			Emp emp = new Emp();
			emp.setEmpId(rs.getInt(2));
			emp.setEmpName(rs.getString(3));
			emp.setEmpEmail(rs.getString(4));
			emp.setEmpPhone(rs.getString(5));
			emp.setDept(dept);
			emp.setJob(job);
			
			list.add(emp);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return list;
	}
	/**
	 * 修改数据(电话，入职日期，工资，部门，职务，自我介绍)
	 * @throws Exception 
	 */
	
	public void update(Emp emp) throws Exception{
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "update emp set emp_phone=?,emp_hiredate=?,emp_salary=?,emp_dept_id=?,emp_job_id=?,emp_info=? where emp_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, emp.getEmpPhone());
		pstmt.setDate(2, emp.getEmpHiredate());
		pstmt.setDouble(3, emp.getEmpSalary());
		pstmt.setInt(4, emp.getDept().getDeptId());
		pstmt.setInt(5, emp.getJob().getJobId());
		pstmt.setString(6, emp.getEmpInfo());
		pstmt.setInt(7, emp.getEmpId());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	/**
	 * 根据id查询员工单条数据（单表查询）
	 * @throws Exception 
	 */
	
	public Emp queryById(Integer empId) throws Exception {
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "select * from emp where emp_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, empId);
		
		ResultSet rs = pstmt.executeQuery();
		
		Emp emp = null;
		
		if (rs.next()) {
			
			Dept dept = new Dept();
			dept.setDeptId(rs.getInt(9));
			
			Job job = new Job();
			job.setJobId(rs.getInt(10));
			
			emp = new Emp(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
					rs.getString(6),rs.getDate(7),rs.getDouble(8),
					dept,job,rs.getString(11),rs.getString(12));
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return emp;
	}
	
	/**
	 * 返回总记录数
	 * @throws Exception 
	 */
	
	public int getCount() throws Exception {
		Connection conn = DbUtil.getConnection();
		
		String sql = "select count(*) from emp";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		int count = rs.getInt(1);
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return count;
	}
	
	/**
	 * 根据登录名emp_login_name查询返回对应员工数据（密码）
	 * @throws Exception 
	 */
	public Emp queryByName(String empLoginName) throws Exception {
		
		Connection conn = DbUtil.getConnection();

		String sql = "select emp_pwd from emp where emp_login_name=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, empLoginName);

		ResultSet rs = pstmt.executeQuery();

		Emp emp = null;

		if (rs.next()) {
			emp = new Emp();
			emp.setEmpPwd(rs.getString(1));
		}

		rs.close();
		pstmt.close();
		conn.close();

		return emp;

	}
	
	/**
	 * 通过登录名修改密码
	 * @throws Exception 
	 */
	
	public void updateEmpPwd(String empLoginName,String empPwd) throws Exception {
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "update emp set emp_pwd=? where emp_login_name=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, empPwd);
		pstmt.setString(2, empLoginName);
	
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	/**
	 * 通过登录名更新头像数据
	 * @throws Exception 
	 */
	public void updateEmpPic(String empLoginName,String emPic) throws Exception {
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "update emp set emp_pic=? where emp_login_name=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, emPic);
		pstmt.setString(2, empLoginName);
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	/**
	 * 通过登录名查询头像数据
	 * 如果用户不存在，返回null
	 * @throws Exception 
	 */
	
	public String queryEmpPic(String empLoginName) throws Exception {
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "select emp_pic from emp where emp_login_name=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, empLoginName);
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) 
			return	rs.getString(1);
		
		return null;
		
	}
 }
