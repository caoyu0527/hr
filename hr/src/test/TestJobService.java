package test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.junit.Test;

import com.icss.hr.job.service.JobService;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class TestJobService {
	
	private JobService service = new JobService();
	
	/**
	 * @throws Exception
	 */
	@Test
	public void textWriteExcel() throws Exception {
		
		FileOutputStream fos = new FileOutputStream("e://11.txt");
		
		service.writeJobExcel(fos);
	}
}
