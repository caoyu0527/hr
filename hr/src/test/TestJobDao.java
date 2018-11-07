package test;

import java.util.ArrayList;

import org.junit.Test;

import com.icss.hr.job.dao.JobDao;
import com.icss.hr.job.pojo.Job;

/**
 * 测试职务Dao
 * @author Administrator
 *
 */
public class TestJobDao {

	private JobDao dao = new JobDao();
	
	@Test
	public void testInsert() throws Exception {
		Job job = new Job("总裁",100000,200000);
		dao.insert(job);
	}
	
	@Test
	public void testUpdate() throws Exception {
		Job job = new Job(8,"老总",100000,200000);
		dao.update(job);
	}
	
	@Test
	public void testDelete() throws Exception {
		dao.delete(2);
	}
	
	@Test
	public void testqueryById() throws Exception {
		Job job = dao.queryById(5);
		System.out.println(job);
	}
	
	@Test
	public void testquery() throws Exception {
		
		ArrayList<Job> list = dao.query();
		
		for (Job job : list) {
			System.out.println(job);
		}
	}
	
	
}
