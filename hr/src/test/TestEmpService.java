package test;

import org.junit.Test;

import com.icss.hr.emp.service.EmpService;

/**
 * ≤‚ ‘‘±π§Service
 * @author Administrator
 *
 */
public class TestEmpService {
	
	private EmpService service = new EmpService();
	
	@Test
	public void testCheckLogin() throws Exception {
		int result = service.checkLogin("tom", "222");
		
		System.out.println(result);
	}
	
	@Test
	public void testCheckLoginName() throws Exception {
		
		boolean flag = service.checkLoginName("tom");
		
		System.out.println(flag);
	}
	
	@Test
	public void testQueryEmpPwd() throws Exception {
		
		String pwd = service.queryEmpPwd("tom");
		
		System.out.println(pwd);
		
	}
}
