package test;

import java.util.ArrayList;

import org.junit.Test;

import com.icss.hr.dept.dao.DeptDao;
import com.icss.hr.dept.pojo.Dept;

/**
 * 测试部门Dao
 * @author Administrator
 *
 */
public class TestDeptDao {
	
	private DeptDao dao = new DeptDao();
	
	@Test
	public void testInsert() throws Exception {
		Dept dept = new Dept("财务部","上海黄浦区");
		dao.insert(dept);
	}
	
	@Test
	public void testUpdate() throws Exception {
		Dept dept = new Dept(20,"金融部","南京");
		dao.update(dept);
	}
	
	@Test
	public void testdelete() throws Exception {
		dao.delete(30);
	}
	
	@Test
	public void testQueryById() throws Exception {
		
		Dept dept = dao.queryById(20);
		System.out.println(dept);
	}
	
	@Test
	public void testQuery() throws Exception {
		
		ArrayList<Dept> list = dao.query();
		
		for (Dept dept : list) {
			System.out.println(dept);
		}
	}
	
 }
