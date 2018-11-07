package com.icss.hr.pic.service;

import java.util.ArrayList;

import com.icss.hr.common.Pager;
import com.icss.hr.pic.dao.PicDao;
import com.icss.hr.pic.pojo.Pic;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Í¼¿âservice
 * @author Administrator
 *
 */
public class PicService {

	private PicDao dao = new PicDao();
	
	public void addPic(Pic pic) throws Exception {
		dao.insert(pic);
	}
	
	public void deletePic(Integer picId) throws Exception {
		dao.delete(picId);
	}
	
	public ArrayList<Pic> queryPicByPage(Pager pager) throws Exception {
		return dao.queryByPage(pager);
	}
	
	public int getPicCount() throws Exception {
		return dao.getCount();
	}
	
	public Pic queryPicById(Integer picId) throws Exception {
		return dao.queryById(picId);
	}
}
