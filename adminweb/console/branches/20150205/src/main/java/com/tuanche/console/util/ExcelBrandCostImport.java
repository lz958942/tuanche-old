package com.tuanche.console.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.tuanche.bean.che.Brand;
import com.tuanche.bean.che.Search;
import com.tuanche.bean.sem.DayStatistics;
import com.tuanche.console.bean.City;
/**
 * 品牌 消费   excel导入
 * @author zjl
 *
 */
public class ExcelBrandCostImport {
	
	public static void downLoadExcel03(Map<String,DayStatistics> map ,Map<String,String> accountMap,
			Map<Integer,String> brandMap,HttpServletRequest request,HttpServletResponse response,String cityCode,String stringZZ){
		try {
			com.tuanche.bean.che.City city = GlobalConstants.districtMap.get(cityCode);
			String cityName = city==null?"合计":city.getName();
			String fileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"-"+cityName+".xls";
			OutputStream os = response.getOutputStream();   
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ ExportExcel.getCorrectFileName(request, fileName));
			response.setContentType("application/msexcel");
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			WritableSheet sheet = workbook.createSheet("Sheet1", 0);
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);
		    WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);
		    WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
		    
			int len = accountMap.size()*3 +1;
			sheet.mergeCells(len, 0, len+2, 0);
			sheet.addCell(new Label(len, 0,"合计",wcf_center));
			sheet.addCell(new Label(len,1,"消费",wcf_center));
			sheet.addCell(new Label(len+1,1,"报名",wcf_center));
			sheet.addCell(new Label(len+2,1,"成本",wcf_center));
			
			sheet.mergeCells(0, 0, 0, 1);
			sheet.addCell(new Label(0, 0,"品牌",wcf_center));
			Set<String> keySet = accountMap.keySet();
			Iterator<String> iterator = keySet.iterator();
			int j=1;//i  行     j  列   
			while (iterator.hasNext()) {
				String accountName = iterator.next();//id
				sheet.mergeCells(j, 0, j+2, 0);
				sheet.addCell(new Label(j, 0,GlobalConstants.accountCodeMaps.get(accountName).getAccountName(),wcf_center));
				sheet.addCell(new Label(j,1,"消费",wcf_center));
				sheet.addCell(new Label(j+1,1,"报名",wcf_center));
				sheet.addCell(new Label(j+2,1,"成本",wcf_center));
				int i=2;
				Iterator<Integer> brandIter = brandMap.keySet().iterator();
				while(brandIter.hasNext()){
					Integer next2 = brandIter.next();//id
					Brand brand = GlobalConstants.brandMap.get(next2);
					String brandName = next2==-1||brand==null?"其他":brand.getName();
					sheet.addCell(new Label(0,i,brandName,wcf_center));
					DayStatistics dayStatistics = map.get(accountName+"$"+next2);
					if(dayStatistics!=null){
						sheet.addCell(new Label(j,i,dayStatistics!=null && dayStatistics.getCostMoney()!=null?dayStatistics.getCostMoney()+"":"0",wcf_center));
						sheet.addCell(new Label(j+1,i,dayStatistics!=null ?dayStatistics.getApplyNum()+"":"0",wcf_center));
						String avg = ""; 
						if(dayStatistics.getApplyNum() == 0){
							avg = dayStatistics.getCostMoney()+"";
						}else{
							avg = dayStatistics.getCostMoney().divide(new BigDecimal(dayStatistics.getApplyNum()),2,BigDecimal.ROUND_HALF_UP)+"";
						}
						sheet.addCell(new Label(j+2,i,avg,wcf_center));
						String brandZ = brandMap.get(next2);
						System.out.println(brandZ+"---"+brandZ);
						String[] splitZ = brandZ.split("-");
						sheet.addCell(new Label(len,i,splitZ[1],wcf_center));
						sheet.addCell(new Label(len+1,i,splitZ[0],wcf_center));
						String zBrandAvg = ""; 
						if(Integer.parseInt(splitZ[0]) == 0){
							zBrandAvg = splitZ[1]+"";
						}else{
							zBrandAvg = new BigDecimal(splitZ[1]).setScale(2, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(splitZ[0]),2,BigDecimal.ROUND_HALF_UP)+"";
						}
						sheet.addCell(new Label(len+2,i,zBrandAvg,wcf_center));
					}
					i++;
				}
				sheet.addCell(new Label(0,i,"合计",wcf_center));
				String string = accountMap.get(accountName);
				String[] split = string.split("-");
				sheet.addCell(new Label(j,i,split[1],wcf_center));
				sheet.addCell(new Label(j+1,i,split[0],wcf_center));
				String zavg = ""; 
				if(Integer.parseInt(split[0]) == 0){
					zavg = split[1]+"";
				}else{
					zavg = new BigDecimal(split[1]).setScale(2, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(split[0]),2,BigDecimal.ROUND_HALF_UP)+"";
				}
				sheet.addCell(new Label(j+2,i,zavg,wcf_center));
				j+=3;
			}
			String[] splitZZ = stringZZ.split("-");
			int iz = brandMap.size()+2;
			sheet.addCell(new Label(len,iz,splitZZ[1],wcf_center));
			sheet.addCell(new Label(len+1,iz,splitZZ[0],wcf_center));
			String zzavg = ""; 
			if(Integer.parseInt(splitZZ[0]) == 0){
				zzavg = splitZZ[1]+"";
			}else{
				zzavg = new BigDecimal(splitZZ[1]).setScale(2, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(splitZZ[0]),2,BigDecimal.ROUND_HALF_UP)+"";
			}
			sheet.addCell(new Label(len+2,iz,zzavg,wcf_center));
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}

	public static Map <String,DayStatistics> uploadExcel03(InputStream stream,Search search) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(search.getStarttime());
		Map <String,DayStatistics> map = new  HashMap<String,DayStatistics> ();
		Workbook workbook=Workbook.getWorkbook(stream);
	    Sheet sheet=workbook.getSheet(0);
	    for(int i=1;i<sheet.getRows();i++){
    		String cost = sheet.getCell(2, i).getContents();
    		int brandId = getbrandId(sheet.getCell(1, i).getContents());
    		int cityId = getCityId(sheet.getCell(0, i).getContents());
			String key = cityId+"-"+brandId;
			DayStatistics sda = map.get(key);	
			if(sda == null){
				sda = new  DayStatistics();
				sda.setBrandId(brandId);
				sda.setDistrictId(cityId);
				City city = GlobalConstants.cityMaps.get(sda.getDistrictId());
				sda.setCityCode(city!=null?city.getCitycode():"");
				sda.setCompanyCode(!StringUtils.isEmpty(search.getAccountCode())?search.getAccountCode().substring(0,2):null);
				sda.setDatetime(date);
				sda.setCostMoney(new BigDecimal(cost).setScale(2, BigDecimal.ROUND_DOWN));
				sda.setAccountId(GlobalConstants.accountCodeMaps.get(search.getAccountCode()).getId());
				sda.setAccountCode(search.getAccountCode());
				map.put(key, sda);
			}else{
				sda.setCostMoney(sda.getCostMoney().add(new BigDecimal(cost).setScale(2, BigDecimal.ROUND_DOWN)));
			}
			
	    }
	    return map;
	}
	
	private static int getCityId(String cityName){
		cityName = cityName.trim();
		cityName = cityName.replaceAll("([0-9]?)", "");
		if(cityName.indexOf("-") != -1){
			cityName = cityName.substring(0,cityName.indexOf("-"));
		}
		City city = GlobalConstants.all_cityNameMaps.get(cityName);
		if(city == null){
			return -1;
		}
		return city.getId();
	}
	
	private static int getbrandId(String brand){
		int brandId = -1;
		if(!StringUtils.isEmpty(brand) && brand.indexOf("-") != -1){
			brand= brand.substring(0,brand.indexOf("-"));
		}		
		Pattern pattern = Pattern.compile("[0-9]*");
		if(pattern.matcher(brand).matches()){
			brandId = Integer.parseInt(brand);
		}
		return brandId;
	}
	
}
