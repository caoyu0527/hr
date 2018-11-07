package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.icss.hr.common.Pager;
import com.icss.hr.pic.dao.PicDao;
import com.icss.hr.pic.pojo.Pic;

/**
 * ≤‚ ‘Õºø‚Dao
 * @author Administrator
 *
 */
public class TestPicDao {
	
	PicDao dao = new PicDao();
	
	@Test
	public void testInsesrt() throws Exception {
		
		File file = new File("e:\\1.jpg");
		FileInputStream fis = new FileInputStream(file);
		Pic pic = new Pic("1.jpg", "Œ‚“‡∑≤", file.length(), "tom", fis, new Date());
		dao.insert(pic);
	
 	
	} 
	
	@Test
	public void testDelete() throws Exception {
		
		dao.delete(1);
		
	}
	
	@Test
	public void testGetCount() throws Exception {
		
		int count = dao.getCount();
		System.out.println(count);
		
	}
	
	@Test
	public void testQueryByPage() throws Exception {
		
		Pager pager = new Pager(dao.getCount(), 3);
		
		ArrayList<Pic> list = dao.queryByPage(pager);
		
		for(Pic pic : list) {
			System.out.println(pic);
		}
		
	}
	@Test
	public void testQueryById() throws Exception {
		
		Pic pic = dao.queryById(21);
		
		InputStream in = pic.getPicData();
		
		FileOutputStream fos = new FileOutputStream("e:\\111.jpg");
		
		byte[] b = new byte[1024 * 8];
		
		int len = in.read(b);
		
		while(len != -1) {
			fos.write(b,0,len);
			len = in.read(b);
		}
		
		fos.close();
		in.close();
	}
}
