package com.tuanche.smc.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
















import org.springframework.stereotype.Service;

import com.tuanche.bean.che.CarStyle;
import com.tuanche.commons.util.Resources;
import com.tuanche.commons.util.StringUtils;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.mapper.che.read.CarStylesReadMapper;
import com.tuanche.smc.common.Common;
import com.tuanche.smc.common.Globals;
import com.tuanche.smc.domain.zixun.HotWord;
import com.tuanche.smc.domain.zixun.SimpleZixun;
import com.tuanche.smc.domain.zixun.ZiXun;
import com.tuanche.smc.persistence.read.admin.zixun.HotWordReadMapper;
import com.tuanche.smc.persistence.read.admin.zixun.ZiXunReadMapper;
import com.tuanche.smc.persistence.write.admin.zixun.HotWordWriteMapper;
import com.tuanche.smc.persistence.write.admin.zixun.ZiXunWriteMapper;
import com.tuanche.smc.util.ZiXunDateUtil;
import com.tuanche.upload.FtpUtil;

@Service
public class ZiXunService {

	@Resource
	private ZiXunReadMapper ziXunReadMapper;

	@Resource
	private ZiXunWriteMapper ziXunWriteMapper;

	@Resource
	private HotWordReadMapper hotWordReadMapper;

	@Resource
	private CarStylesReadMapper carStylesReadMapper;
	
	@Resource
	private HotWordWriteMapper hotWordWriteMapper;

	/*
	 * @Resource private TSpecialMapper excelImport;
	 */

	public int addZixunHotWord(ZiXun zixun) {
		initData(zixun);
		addZixunHotWord(zixun.getId(), zixun.getHotWordsTxt());
		return zixun.getId();
	}
//
	
	public int saveZixun(ZiXun zixun, HttpSession session) {
		initData(zixun);
		this.image(zixun, session);
		ziXunWriteMapper.addZixun(zixun);
		ziXunWriteMapper.addZixunContent(zixun);
		return zixun.getId();
	}
	

	
	
	private void image(ZiXun zixun, HttpSession session) {
		String fileName = "";
		String path="";
		// 添加域名保存大图和标准图 _b.jpg
		if (zixun.getListPic() != null && 0 < zixun.getListPic().length()) {
			String listpic = zixun.getListPic();
			// 保存大图
			String listpicmax = zixun.getListPic();
			// 上传
			if(listpic!=null&&listpic.length()>0){
				listpic=listpic.replaceAll("_s.jpg", "").replaceAll("/"+Resources.getString("temfilePath")+"zixun", "");
				listpicmax=listpicmax.replaceAll("_s.jpg", "").replaceAll("/"+Resources.getString("temfilePath")+"zixun", "");;
			}
			path = session.getServletContext().getRealPath("/" + Resources.getString("temfilePath"));
			fileName = listpic.substring(listpic.lastIndexOf("/") + 1,listpic.length());
			FtpUtil.ftpUpload(Resources.getString("ftp.host"),Resources.getString("ftp.username"),Resources.getString("ftp.password"), path+"zixun/"+ZiXunDateUtil.getEndDir()+"/",ZiXunDateUtil.getEndDir(), fileName + "_s.jpg");
			FtpUtil.ftpUpload(Resources.getString("ftp.host"),Resources.getString("ftp.username"),Resources.getString("ftp.password"), path+"zixun/"+ZiXunDateUtil.getEndDir()+"/",ZiXunDateUtil.getEndDir(), fileName + "_b.jpg");
			zixun.setListPic(listpic);
		}
		System.out.println("======================FCK==============================");
		// 提交文版编辑器图片
		if(zixun!=null &&zixun.getContent()!=null&&zixun.getContent().length()>0){
			String[] Strings=zixun.getContent().split("</p>");
			initContent(Strings,session,zixun);
		}
		}
	


	private void initContent(String[] strings, HttpSession session, ZiXun zixun) {
		if(zixun!=null && strings.length>0){
		int num=0;
		String buffSrc="";
		String buffFileName="";
		String StringTem ="";
		String temPath= session.getServletContext().getRealPath("/" + Resources.getString("temfilePath")+"zixun/"+ZiXunDateUtil.getEndDir()+"/");;
		for (String string : strings) {
			num=getFrequency(string, "src=");
				if(num>0&&num==1){
					//有图片
					//一个图片
					StringTem=string.substring(string.indexOf("src=\"")+5,string.length());
					if(StringTem.indexOf("/")<StringTem.indexOf(".jpg")+4){
						if(buffSrc.length()>0){
							buffSrc="";
						}
					buffSrc=(StringTem.substring(0,StringTem.indexOf(".jpg")+4));
					}
					if(StringTem.length()>0 &&buffSrc.toString().trim().startsWith("/pic_tmp")){
						if(buffFileName.length()>0){
							buffFileName="";
						}
					buffFileName=(StringTem.substring(buffSrc.toString().trim().lastIndexOf("/")+1,buffSrc.length()));
					if(new File(temPath+buffFileName.toString().trim()).exists()){
					FtpUtil.ftpUpload(Resources.getString("ftp.host"),Resources.getString("ftp.username"),Resources.getString("ftp.password"),temPath,ZiXunDateUtil.getEndDir(),buffFileName.toString().trim());
					}
					}
					
				}else if(num>1){
				//不止一个
					String srcPath="";
					String imageNmae="";
				//图片路径
					for (String string2 : string.split("title")) {
						if(getFrequency(string2, "src")==1){
							//有图片
							srcPath=getSrcPath(string2);
							if(srcPath.length()>0){
								if(srcPath.startsWith("/pic")){
									imageNmae=srcPath.substring(srcPath.lastIndexOf("/")+1, srcPath.length());
									if(imageNmae.length()>0 && new File(temPath+imageNmae).exists()){
										FtpUtil.ftpUpload(Resources.getString("ftp.host"),Resources.getString("ftp.username"),Resources.getString("ftp.password"),temPath,ZiXunDateUtil.getEndDir(),imageNmae);
									}
								}/*else if(srcPath.startsWith("http://console.tuanche.com") ||srcPath.startsWith("http:localhost")){
									
								}*/
							}
						}
					}
				}
			
		}
		zixun.setContent(zixun.getContent().replaceAll("src=\"/pic_tmp", "src=\"http://pic.tuanche.com"));
		zixun.setContent(zixun.getContent().replaceAll("http://console.tuanche.com/pic_tmp", "http://pic.tuanche.com"));
		zixun.setContent(zixun.getContent().replaceAll("http://localhost/pic_tmp", "http://pic.tuanche.com"));
		}
	}

	private String getSrcPath(String string) {
		int tem=0;
		String tem2="";
		if(string.length()>0){
			tem=string.indexOf("src=\"")+5;
			tem2=string.substring(tem,string.length());
		}
		if(tem<tem2.indexOf(".jpg")+4){
			return tem2.substring(0,tem2.indexOf(".jpg")+4);
		}
		return "";
	}

	private void initData(ZiXun zixun) {
		// 保存车型id
		if (zixun.getPublishDate() == null) {
			zixun.setPublishDate(new Date());
		}
		int classId = zixun.getClassId();
		String code = Common.newsClassifyMap.get(classId + "").getCode();
		zixun.setClassCode(code);
	}
	public void addZixunHotWord(int zixunId, String hotWordsTxt) {
		String[] array = hotWordsTxt.split(",");
		int count = 0;
		try {
			for (String para : array) {
				String[] hotWordInfo = para.split("\\|\\|");
				if (!"||".equalsIgnoreCase(para)
						&& StringUtils.isNotEmpty(hotWordInfo[0])
						&& StringUtils.isNotEmpty(hotWordInfo[1])) {
					HotWord hotWord = new HotWord();
					hotWord.setZiXunId(zixunId);
					hotWord.setKeyword(hotWordInfo[0]);
					hotWord.setUrl(hotWordInfo[1]);
					hotWord.setOrderIndex(count++);
					hotWordWriteMapper.addHotWord(hotWord);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<HotWord> getZixunHotWord(String hotWordsTxt) {
		List<HotWord> result = new ArrayList<HotWord>();
		String[] array = hotWordsTxt.split(",");
		int count = 0;
		for (String para : array) {
			String[] hotWordInfo = para.split("\\|\\|");
			if (!"||".equalsIgnoreCase(para)
					&& StringUtils.isNotEmpty(hotWordInfo[0])
					&& StringUtils.isNotEmpty(hotWordInfo[1])) {
				HotWord hotWord = new HotWord();
				hotWord.setKeyword(hotWordInfo[0]);
				hotWord.setUrl(hotWordInfo[1]);
				hotWord.setOrderIndex(count++);
				result.add(hotWord);
			}
		}
		return result;
	}
	public void updateZixunHouseInfo(ZiXun zixun) {
		hotWordWriteMapper.delHotWordByZixunId(zixun.getId());
		addZixunHotWord(zixun.getId(), zixun.getHotWordsTxt());
	}
	public List<ZiXun> getAllDetaultZixunByPage() {
		return ziXunReadMapper.getAllDefaultZixunByPage();
	}
	public List<ZiXun> getAllZixunByPage(List<String> conditions) {
		return ziXunReadMapper.getAllZixunByPage(conditions);
	}
	public List<ZiXun> getAllTodayZixunByPage() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(new Date());
		return ziXunReadMapper.getAllTodayZixunByPage(format);
	}
	public List<ZiXun> getAllYesterdayZixunByPage() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return ziXunReadMapper.getAllYesterdayZixunByPage(
				sdf.format(new Date(new Date().getTime()
						- Globals.oneDayInMilliSeconds)),
				sdf.format(new Date()));
	}
	public List<ZiXun> getAllMyTodayZixunByPage(int id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return ziXunReadMapper.getAllMyTodayZixunByPage(id,
				sdf.format(new Date()));
	}
	public List<ZiXun> getAllMyYesterdayZixunByPage(int id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return ziXunReadMapper.getAllMyYesterdayZixunByPage(
				id,
				sdf.format(new Date(new Date().getTime()
						- Globals.oneDayInMilliSeconds)),
				sdf.format(new Date()));
	}
	public List<ZiXun> getAllMyZixunByPage(int id) {
		return ziXunReadMapper.getAllMyZixunByPage(id);
	}

	public ZiXun getZixunById(int id) {
		return ziXunReadMapper.getZixunById(id);
	}
	public List<HotWord> getHotWordsByZixunId(int zixunId) {
		return hotWordReadMapper.getHotWordsByZixunId(zixunId);
	}
	public int updateZixunProperties(ZiXun zixun, HttpSession session) {
		initData(zixun);
		// 修改图片
		this.imageUpdate(zixun, session);
		
		ziXunWriteMapper.updateZixunContent(zixun);
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(sessionUser!=null){
		ziXunWriteMapper.updateZixunUpEditorId(sessionUser.getId(), zixun.getId());
		}
		return ziXunWriteMapper.updateZixunProperties(zixun);
	}
	private void imageUpdate(ZiXun zixun, HttpSession session) {
		String path = "";
		String fileName = "";
		String sign = "";
		String subString="";
		String srcString="";
		String listpic = zixun.getListPic();
		if(listpic!=null&&listpic.length()>0){
			listpic=listpic.replaceAll("_s.jpg", "").replaceAll("/"+Resources.getString("temfilePath")+"zixun", "");;
		}
		String pathimg = session.getServletContext().getRealPath("/" + Resources.getString("temfilePath") +"/zixun/"+ listpic + "_s.jpg");
		if (new File(pathimg).exists()) {
			// 新增图片
			path = session.getServletContext().getRealPath("/" + Resources.getString("temfilePath"));
			fileName = listpic.substring(listpic.lastIndexOf("/") + 1,listpic.length());
			FtpUtil.ftpUpload(Resources.getString("ftp.host"),Resources.getString("ftp.username"),Resources.getString("ftp.password"), path+"zixun/"+ZiXunDateUtil.getEndDir()+"/",ZiXunDateUtil.getEndDir(), fileName + "_s.jpg");
			FtpUtil.ftpUpload(Resources.getString("ftp.host"),Resources.getString("ftp.username"),Resources.getString("ftp.password"), path+"zixun/"+ZiXunDateUtil.getEndDir()+"/",ZiXunDateUtil.getEndDir(), fileName + "_b.jpg");
			zixun.setListPic(listpic);
		}
		// 上传
		System.out.println("======================FCK==============================");
		// 提交文版编辑器图片
		if (zixun.getContent() != null && zixun.getContent().length() > 0) {
			String[] Strings=zixun.getContent().split("</p>");
			initContent(Strings,session,zixun);
			}
	}
		


	public int delZixun(int id) {
		hotWordWriteMapper.delHotWordByZixunId(id);
		return ziXunWriteMapper.delZixun(id);
	}

	public int deployZixun(int id, boolean type) {
		if (type) {
			return ziXunWriteMapper.deployZixun(id, ZiXun.DEPLOY_STATUS_OK);
		} else {
			return ziXunWriteMapper.deployZixun(id,
					ZiXun.DEPLOY_STATUS_UNDEPLOY);
		}
	}

	public int incrementClickCount(int id) {
		return ziXunWriteMapper.incrementClickCount(id);
	}

	public List<SimpleZixun> getRelatedZixun(int id, int classId) {
		return ziXunReadMapper.getRelatedZixun(id, classId);
	}

	public List<SimpleZixun> getNewRelatedZixun(int classId) {
		return ziXunReadMapper.getNewRelatedZixun(classId);
	}

	public void batchUpdateZixunstatus(int[] ids, int status) {
		ziXunWriteMapper.batchUpdateZixunstatus(ids, status);
	}

	/*
	 * public void excelImport(HttpServletRequest request) throws BiffException,
	 * IOException { MultipartHttpServletRequest multipartRequest =
	 * (MultipartHttpServletRequest) request; MultipartFile fileExcel =
	 * multipartRequest.getFile("file"); List<TSpecial>
	 * tList=ZiXunExcelImport.readNewExcel(fileExcel.getInputStream());
	 * System.out.println(tList);
	 * 
	 * }
	 */
	public static void main(String[] args) {
		// FtpUtil.ftpUpload(Resources.getString("ftp.host"),
		// Resources.getString("ftp.username"),
		// Resources.getString("ftp.password"),
		// "E:/Users/workspace/console//src/main/webapp/pic_tmp/20140606",
		// ZiXunDateUtil.getEndDir(), "/12861402043885664.jpg");
		boolean b=new File("E:\\Users\\workspace\\console\\src\\main\\webapp\\pic_tmp\\20140610\\36741402363909800.jpg").delete();
		
		System.out.println(b);
	}
	private int getFrequency(String ss,String s) {
		if(ss.length()<s.length()){
			return 0;
		}
		int leng=ss.length();
		int mum=0;
		for (int i = 0; i < leng; i ++){
			  if(ss.indexOf(s)!=-1){
				  if(ss.length()==0){
					  break;  
				  }
				  if(ss.indexOf(s)==0){
					  mum++; 
				  }
				  ss=ss.substring(1);
			  }
		}
		return mum;
	}

	/**
	 * @param conditions
	 * @return
	 * @author liuhg
	 * @Description 点击总数
	 */
	public Integer getClickSum(List<String> conditions) {
		return ziXunReadMapper.getClickSum(conditions);
	}

	/**
	 * @param pid
	 * @return
	 * @author liuhg
	 * @Description 根据品牌id查询车型
	 */
	public List<CarStyle> getCarStyleById(HashMap<String, Object> map) {
		
		return carStylesReadMapper.getCarStyleById(map);
	}
}
