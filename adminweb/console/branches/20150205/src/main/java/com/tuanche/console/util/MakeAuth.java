package com.tuanche.console.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tuanche.console.bean.SysFunc;
import com.tuanche.console.service.SysFuncService;


public class MakeAuth {
	public String makeSupper(SysFuncService sysFuncService,List<SysFunc> funList1) {
		java.lang.StringBuffer sb = new StringBuffer("[");
		
		for(SysFunc func1 : funList1) {
			Map map2 = new HashMap();
			map2.put("parentId",func1.getId());
			map2.put("funcStatus", 1);
			map2.put("cpage", 0);
			map2.put("pageSize", Integer.MAX_VALUE);
			map2.put("orderStr", "func_sort");
			map2.put("isMenu", 1);
			boolean checkFunc=false;
			List<SysFunc> funList2 = sysFuncService.selectSysFuncList(map2); //模块级别
			if(funList2!=null&&funList2.size()>0){
				for(SysFunc func2:funList2){
						sb.append("{id:").append(func2.getId()).append(",pId:").append(func1.getId()).append(",name:'").append(func2.getFuncName());
						if(func2.getFuncUrl()!=null&&func2.getFuncUrl().length()>0){
						sb.append("',url:'").append(func2.getFuncUrl()).append("',target:'manFrame'},") ;
						}else{
						sb.append("'},");
						}
						Map map3 = new HashMap();
						map3.put("parentId",func2.getId());
						map3.put("funcStatus", 1);
						map3.put("cpage", 0);
						map3.put("pageSize", Integer.MAX_VALUE);
						map3.put("orderStr", "func_sort");
						map3.put("isMenu", 1);
						List<SysFunc> funList3 = sysFuncService.selectSysFuncList(map3);
						for(SysFunc func3:funList3)
						{
								sb.append("{id:").append(func3.getId()).append(",pId:").append(func2.getId())
								.append(",name:'").append(func3.getFuncName());
								if(func3.getFuncUrl()!=null&&func3.getFuncUrl().length()>0){
									sb.append("',url:'").append(func3.getFuncUrl()).append("',target:'manFrame'},") ;
									}else{
									sb.append("'},");
								}
						}	
				}
			
			}
				sb.append("{id:").append(func1.getId()).append(",pId:0,name:'").append(func1.getFuncName());
				if(func1.getFuncUrl()!=null&&func1.getFuncUrl().length()>0){
					sb.append("',url:'").append(func1.getFuncUrl()).append("',target:'manFrame'},") ;
					}else{
					sb.append("'},");
				}
			
		}
		// 遍历3类
		sb.append("]");
		return sb.toString();
	}

	public String listAuthByRoleNo(int roleId,	SysFuncService sysFuncService,List<SysFunc> funList1) {
		java.lang.StringBuffer sb = new StringBuffer("[");
		List<SysFunc> authList = sysFuncService.selectAuthByRoleId(roleId);
		Map<Integer,SysFunc> authMap=new HashMap();  //权限id
		Map<Integer,Boolean> authParentMap=new HashMap(); //权限的父id
		for(SysFunc func:authList){
			if(!authMap.containsKey(func.getId())){
				authMap.put(func.getId(),func);
			}
			if(!authParentMap.containsKey(func.getParentId())){
				authParentMap.put(func.getParentId(), true);
			}
		}
		for(SysFunc func1 : funList1) {
			Map map2 = new HashMap();
			map2.put("parentId",func1.getId());
			map2.put("funcStatus", 1);
			map2.put("cpage", 0);
			map2.put("pageSize", Integer.MAX_VALUE);
			map2.put("orderStr", "func_sort");
			map2.put("isMenu", 1);
			boolean checkFunc=false;
			List<SysFunc> funList2 = sysFuncService.selectSysFuncList(map2); //模块级别
			if(authParentMap!=null&&funList2!=null&&funList2.size()>0){
				for(SysFunc func2:funList2){
					if(authParentMap.get(func2.getId())!=null||authMap.get(func2.getId())!=null){
						checkFunc=true;
						sb.append("{id:").append(func2.getId()).append(",pId:").append(func1.getId());
						sb.append(",name:'").append(func2.getFuncName());
						if(func2.getFuncUrl()!=null&&func2.getFuncUrl().length()>0){
						sb.append("',url:'").append(func2.getFuncUrl()).append("',target:'manFrame'},") ;
						}else{
							sb.append("'},");
						}
						Map map3 = new HashMap();
						map3.put("parentId",func2.getId());
						map3.put("funcStatus", 1);
						map3.put("cpage", 0);
						map3.put("pageSize", Integer.MAX_VALUE);
						map3.put("isMenu", 1);
						map3.put("orderStr", "func_sort");
						List<SysFunc> funList3 = sysFuncService.selectSysFuncList(map3);
						for(SysFunc func3:funList3)
						{
							if(authMap.get(func3.getId())!=null){
								sb.append("{id:").append(func3.getId()).append(",pId:").append(func2.getId()).append(",name:'").append(func3.getFuncName());
								if(func3.getFuncUrl()!=null&&func3.getFuncUrl().length()>0){
									sb.append("',url:'").append(func3.getFuncUrl()).append("',target:'manFrame'},") ;
									}else{
									sb.append("'},");
								}
							}
						}
					}
					
					
				}
			
			}
			if(checkFunc){
				sb.append("{id:").append(func1.getId()).append(",pId:0,name:'").append(func1.getFuncName());
				if(func1.getFuncUrl()!=null&&func1.getFuncUrl().length()>0){
					sb.append("',url:'").append(func1.getFuncUrl()).append("',target:'manFrame'},") ;
					}else{
					sb.append("'},");
				}
			}
		}
		sb.append("]");
		// 遍历3类
		return sb.toString();
	}
}
