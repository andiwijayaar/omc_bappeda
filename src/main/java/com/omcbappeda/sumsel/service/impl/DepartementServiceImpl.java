package com.omcbappeda.sumsel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.omcbappeda.sumsel.dao.DepartementDAO;
import com.omcbappeda.sumsel.model.Departement;
import com.omcbappeda.sumsel.model.DepartementVO;
import com.omcbappeda.sumsel.model.User;
import com.omcbappeda.sumsel.model.UserVO;
import com.omcbappeda.sumsel.service.DepartementService;

public class DepartementServiceImpl implements DepartementService {
	private DepartementDAO departementDAO;

	public void setDepartementDAO(DepartementDAO departementDAO) {
		this.departementDAO = departementDAO;
	}

	@Override
	public void save(DepartementVO departementVO) {
		Departement departement = setModelDepartemenVO(departementVO);
		departementDAO.save(departement);
	}

	@Override
	public DepartementVO get(String code) {
		return setDeptVOByDeptModel(departementDAO.get(code));
	}

	@Override
	public void update(DepartementVO departementVO) {
		this.departementDAO.update(setDeptModelByDeptVO(departementVO));
	}

	@Override
	public void delete(DepartementVO departementVO) {
		this.departementDAO.delete(setDeptModelByDeptVO(departementVO));
	}

	@Override
	public List<DepartementVO> getAll(int limit, int offset) {
		List<Departement> departementList = departementDAO.getDeptByPaging(limit, offset);
		List<DepartementVO> departementVOList = new ArrayList<DepartementVO>();
		
		for (Departement dep : departementList) {
			DepartementVO departementVO = setDeptVOByDeptModel(dep);
			departementVOList.add(departementVO);
		}

		return departementVOList;
	}

	@Override
	public List<DepartementVO> getAll() {
		List<Departement> departementList = departementDAO.getAll();
		List<DepartementVO> departementVOList = new ArrayList<DepartementVO>();
		
		for (Departement dep : departementList) {
			DepartementVO departementVO = setDeptVOByDeptModel(dep);
			departementVOList.add(departementVO);
		}

		return departementVOList;
	}

	@Override
	public List<DepartementVO> getDeptByName(String key, int limit, int offset) {
//		List<Departement> departements = departementDAO.getDeptByName(key, limit, offset);
//		List<DepartementVO> departementVOs = new ArrayList<DepartementVO>();
//		BeanUtils.copyProperties(departements, departementVOs);
		return setDepartementVOListByNameModel(departementDAO.getDeptByName(key, limit, offset));
	}
	
	private List<DepartementVO> setDepartementVOListByNameModel(List<Departement> departements){
		List<DepartementVO> departementVOs = new ArrayList<DepartementVO>();
		for (Departement departement : departements){
			departementVOs.add(setDeptVOByDeptModel(departement));
		}
		return departementVOs;
	}
	
	private DepartementVO setDeptVOByDeptModel(Departement departement) {
		DepartementVO DepartementVO = new DepartementVO();
		BeanUtils.copyProperties(departement, DepartementVO);
		
		return DepartementVO;
	}
	
	private Departement setModelDepartemenVO(DepartementVO departementVO) {
		Departement departement = new Departement();
		BeanUtils.copyProperties(departementVO, departement);
		
		return departement;
	}
	
	private Departement setDeptModelByDeptVO(DepartementVO departementVO) {
		Departement departement = departementDAO.get(departementVO.getCode());
		BeanUtils.copyProperties(departementVO, departement);
		
		return departement;
	}
	
	@Override
	public int countDept(){
		return departementDAO.countDept();
	}
	
	@Override
	public int countDeptByName(String key){
		return departementDAO.countDeptByName(key);
	}
}
