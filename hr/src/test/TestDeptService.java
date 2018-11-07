package test;

import java.io.FileOutputStream;

import org.junit.Test;

import com.icss.hr.dept.service.DeptService;

/**
 * ≤‚ ‘≤ø√≈service
 * @author Administrator
 *
 */
public class TestDeptService {
	
	DeptService service = new DeptService();
	
	@Test
	public void testWriteExcel() throws Exception {
		
		FileOutputStream fos = new FileOutputStream("e:\\dept.xls");
		
		service.writeExcel(fos);
		
		fos.close();
	}
}
