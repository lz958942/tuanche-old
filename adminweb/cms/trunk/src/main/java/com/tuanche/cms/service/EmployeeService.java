package com.tuanche.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.cms.bean.Brand;
import com.tuanche.cms.bean.City;
import com.tuanche.cms.bean.Employee;
import com.tuanche.cms.bean.MapBean;
import com.tuanche.cms.bean.User;
import com.tuanche.cms.cheread.CarStyleMapper;
import com.tuanche.cms.cheread.CityMapper;
import com.tuanche.cms.cheread.CityReadMapper;
import com.tuanche.cms.read.EmployeeReadMapper;
import com.tuanche.commons.util.StringUtils;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeReadMapper employeeReadMapper;  
	@Autowired
	private CityReadMapper cityReadMapper;
	
	
	@Autowired
	private CarStyleMapper carStyleMapper;
	
	public Employee selectEmployeeById(Integer id){
    	return employeeReadMapper.selectEmployeeById(id);
    }
	
	public List<MapBean> selectEmployeeInit(Map map){
		return employeeReadMapper.selectEmployeeInit(map);
	}
	public Employee  selectEmployeeByEmpLogin(String empLogin){
    	return  employeeReadMapper.selectEmployeeByEmpLogin(empLogin);
    }
	
	public Employee getUserById(int id){
		List<Employee> user= employeeReadMapper.selectEmployeeByIds(id+"");
		Employee emp=null;
		if(user!=null&&user.size()>0){
			emp=user.get(0);
			if(StringUtils.isNotEmpty(emp.getBrandIds())){
				List<Brand>  brands=carStyleMapper.getBrandsById(emp.getBrandIds());
				if(brands!=null){
					emp.setBrands(brands);
				}
			}
		}
		return emp;
	}
	public List<Employee>  getUserById(String  ids){
		if(StringUtils.isNotEmpty(ids)){
			if(ids.endsWith(",")){
				ids=ids.substring(0,ids.length()-1);
			}
			List<Employee>  list= employeeReadMapper.selectEmployeeByIds(ids);
			if(list!=null){
				List<Brand>  brands=null;
				for(Employee tmp:list){
					if(StringUtils.isNotEmpty(tmp.getBrandIds())){
						brands=carStyleMapper.getBrandsById(tmp.getBrandIds());
						if(brands!=null){
							tmp.setBrands(brands);
						}	
					}
					
				}
			}
			return list;
		}
		return null;
	}
	
	public List<Employee>  getUserByCityId(Integer  cityId){
		if(cityId!=null){
			City cityById = cityReadMapper.getCityById(cityId==-1?10:cityId);
			if(cityById.getCityCode()!=null){
				List<Employee>  list= employeeReadMapper.selectEmployeeByCityId(cityById.getCityCode(),-1);
				if(list==null){//没有团长信息
					City cityByIdbj = cityReadMapper.getCityById(10);
					list= employeeReadMapper.selectEmployeeByCityId(cityByIdbj.getCityCode(),4);
				}else{//本城市不够，取北京
					if(list.size() < 4){
						City cityByIdbj = cityReadMapper.getCityById(10);
						List<Employee> bjList= employeeReadMapper.selectEmployeeByCityId(cityByIdbj.getCityCode(),4-list.size());
						if(bjList!=null){
							list.addAll(bjList);
						}
					}
				}
				if(list!=null){
					List<Brand>  brands=null;
					for(Employee tmp:list){
						if(StringUtils.isNotEmpty(tmp.getBrandIds())){
							brands=carStyleMapper.getBrandsById(tmp.getBrandIds());
							if(brands!=null){
								tmp.setBrands(brands);
							}	
						}
						
					}
				}
				return list;
			}
		}
		return null;
	}
}
