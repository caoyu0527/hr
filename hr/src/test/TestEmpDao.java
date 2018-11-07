package test;


import java.sql.Date;
import java.util.ArrayList;

import org.junit.Test;

import com.icss.hr.common.Pager;
import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.emp.dao.EmpDao;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.job.pojo.Job;

/**
 * 测试员工Dao
 * @author Administrator
 *
 */
public class TestEmpDao {
	
	private EmpDao dao = new EmpDao();
	
	@Test
	public void testInsert() throws Exception {
		Dept dept = new Dept();
		dept.setDeptId(10);
		
		Job job = new Job();
		job.setJobId(1);
		
		Emp emp = new Emp("tom","tom","123456","tom@163.com",
					"10086",Date.valueOf("1995-01-01"),8000.0,dept,job,null,"无");
		dao.insert(emp);
		
		
	}
 	
	@Test
	public void testInsert2() throws Exception {
		Dept dept = new Dept();
		dept.setDeptId(10);
		
		Job job = new Job();
		job.setJobId(2);
		
		for (int i = 1;i <= 20;i ++) {
		Emp emp = new Emp("jack","jack","123456","jack@163.com",
					"13842278956",Date.valueOf("1997-01-01"),7000.0,dept,job,null,"无");
		dao.insert(emp);
		}
		
	}
	
	@Test
	public void testDelete() throws Exception {
		dao.delete(20);
			
	}
	
	@Test
	public void testQueryByPage() throws Exception {
		
		Pager pager = new Pager(19, 7, 1);
		
		ArrayList<Emp> list = dao.queryByPage(pager);
		
		for (Emp emp : list) {
			System.out.println(emp);
		}
			
	}
	
	@Test
	public void testUpDate() throws Exception {
		
		Dept dept = new Dept();
		dept.setDeptId(10);
		
		Job job = new Job();
		job.setJobId(1);
		
		Emp emp = new Emp();
		emp.setEmpPhone("2222");
		emp.setEmpHiredate(Date.valueOf("1998-01-01"));
		emp.setEmpSalary(5000.0);
		emp.setDept(dept);
		emp.setJob(job);
		emp.setEmpInfo("大家好啊啊啊");
		emp.setEmpId(1);
		
		dao.update(emp);
			
	}
	
	@Test
	public void testQueryById() throws Exception {
		
		Emp emp = dao.queryById(5);
		
		System.out.println(emp);
			
	}
	
	@Test
	public void testgetCount() throws Exception {
		int count = dao.getCount();
		System.out.println(count);
			
	}
	
	@Test
	public void testqueryByName() throws Exception {
		Emp emp = dao.queryByName("tom");
		System.out.println(emp.getEmpPwd());
			
	}
	
	@Test
	public void testupdateEmpPwd() throws Exception {
		
		dao.updateEmpPwd("tom", "222");
			
	}
	
	@Test
	public void testupdateEmpPic() throws Exception {
		
		dao.updateEmpPic("tom", "金泫雅");
			
	}
	
	@Test
	public void testqueryEmpPic() throws Exception {
		dao.queryEmpPic("tom");
		System.out.println(dao.queryEmpPic("tom"));
		
			
	}
	
	
}
