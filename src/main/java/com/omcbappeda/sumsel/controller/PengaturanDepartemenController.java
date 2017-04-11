package com.omcbappeda.sumsel.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.omcbappeda.sumsel.constant.Constant;
import com.omcbappeda.sumsel.model.DepartementVO;
import com.omcbappeda.sumsel.model.UserVO;
import com.omcbappeda.sumsel.service.GlobalService;

@Controller
public class PengaturanDepartemenController extends MainController {
	private GlobalService globalService = (GlobalService) context.getBean("globalService");
	private static final Logger logger = LoggerFactory.getLogger(PengaturanDepartemenController.class);
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getDataTableDepartement", method = RequestMethod.GET)
	private @ResponseBody void prepareDataTableDepartement(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<DepartementVO> departementVOList = globalService.getAllDept();
		int count = globalService.countDept();
		JSONObject dataTable = new JSONObject();
		DateFormat df = new SimpleDateFormat(Constant.DATEFORMAT.toString());
		
		JSONArray jsonArray = new JSONArray();
		for (DepartementVO departementVO : departementVOList) {
			JSONObject data = new JSONObject();
			data.put("Kode", departementVO.getCode());
			data.put("Nama", departementVO.getName());
			data.put("CreatedBy", departementVO.getCreatedBy());
			data.put("CreatedDate", df.format(departementVO.getCreatedDate()));
			data.put("isActive", departementVO.getIsActive());
			
			jsonArray.add(data);
		}
		
		int totalItem = departementVOList.size();
		
		dataTable.put("items",jsonArray);
		dataTable.put("totalItems",  String.valueOf(totalItem));
		
		dataTable.put("currentItemCount",  String.valueOf(totalItem));
		dataTable.put("count", count);
		
//		String key = Constant.EMPTY_STRING.toString();
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		response.getWriter().print(dataTable.toJSONString());
//		request.setAttribute("dataTable", dataTable.toJSONString());
//		return INDEX_AJAX;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getDataTableDepartementByPaging", method = RequestMethod.GET)
	private @ResponseBody void prepareDataTableDepartementByPaging(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String limit = request.getParameter("perPage");
		String page = request.getParameter("page");
		int offset = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);
		double floor = Math.floor((double)offset/(double)Integer.valueOf(limit));
		int pageIndex = (int) floor + 1;
		int totalItem = 0;
		
		List<DepartementVO> departementVOList = new ArrayList<DepartementVO>();
		String key = request.getParameter("key").equals("") ? null : request.getParameter("key");
		if(key==null){
			departementVOList = globalService.getDeptByPaging(Integer.valueOf(limit), offset);
			totalItem = globalService.countDept();		
		} else {
			departementVOList = globalService.getDeptByName(key, Integer.valueOf(limit), offset);
			totalItem = globalService.countDeptByName(key);					
		}
		
		JSONObject dataTable = new JSONObject();
//		int limit = 10;
//		int page = 1;
//		int offset = (page - 1) * limit;
//		double floor = Math.floor((double)offset/(double)limit);
//		int pageIndex = (int) floor + 1;
		
		dataTable.put("perPage", String.valueOf(limit));
		dataTable.put("startIndex", String.valueOf(offset));
		dataTable.put("pageIndex", String.valueOf(pageIndex));
		DateFormat df = new SimpleDateFormat(Constant.DATEFORMAT.toString());
		
		JSONArray jsonArray = new JSONArray();
		for (DepartementVO departementVO : departementVOList) {
			JSONObject data = new JSONObject();
			data.put("Kode", departementVO.getCode());
			data.put("Nama", departementVO.getName());
			data.put("CreatedBy", departementVO.getCreatedBy());
			data.put("CreatedDate", df.format(departementVO.getCreatedDate()));
			data.put("isActive", departementVO.getIsActive());
			
			jsonArray.add(data);
		}
				
		dataTable.put("items",jsonArray);
		dataTable.put("totalItems",  String.valueOf(totalItem));
		dataTable.put("start", totalItem > 0 ? String.valueOf(offset) : "0");
		
		double totalPage = Math.ceil((double)totalItem/(double)Integer.valueOf(limit));
		dataTable.put("totalPage", String.valueOf((int)totalPage));
		
		dataTable.put("currentItemCount",  String.valueOf(totalItem));
		
//		String key = Constant.EMPTY_STRING.toString();
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		response.getWriter().print(dataTable.toJSONString());
//		request.setAttribute("dataTable", dataTable.toJSONString());
//		return INDEX_AJAX;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getDeptByCode", method = RequestMethod.GET)
	private @ResponseBody void prepareDeptById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		super.preventCaching(response);
		String code = request.getParameter("code");
		DepartementVO departementVO = globalService.getDeptByCode(code);
		JSONObject dataTable = new JSONObject();
		JSONObject data = new JSONObject();
		DateFormat df = new SimpleDateFormat(Constant.DATEFORMAT.toString());
		
		data.put("Kode", departementVO.getCode());
		data.put("Nama", departementVO.getName());
		data.put("CreatedBy", departementVO.getCreatedBy());
		data.put("CreatedDate", df.format(departementVO.getCreatedDate()));
		data.put("isActive", departementVO.getIsActive());
		

		dataTable.put("items",data);
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		response.getWriter().print(dataTable.toJSONString());
	}
	
	/*@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getDeptByName", method = RequestMethod.GET)
	private @ResponseBody void prepareDeptByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		super.preventCaching(response);
		JSONObject dataTable = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		String limit = request.getParameter("perPage");
		String page = request.getParameter("page");
		String key = request.getParameter("key").equals("") ? request.getParameter("key") : null;
		int offset = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);
		double floor = Math.floor((double)offset/(double)Integer.valueOf(limit));
		DateFormat df = new SimpleDateFormat(Constant.DATEFORMAT.toString());
		List<DepartementVO> departementVOs = globalService.getDeptByName(key, Integer.valueOf(limit), offset);
		
		for (DepartementVO departementVO : departementVOs) {
			JSONObject data = new JSONObject();
			data.put("Kode", departementVO.getCode());
			data.put("Nama", departementVO.getName());
			data.put("CreatedBy", departementVO.getCreatedBy());
			data.put("CreatedDate", df.format(departementVO.getCreatedDate()));
			data.put("isActive", departementVO.getIsActive());

			jsonArray.add(data);
		}

		dataTable.put("items",jsonArray);
		dataTable.put("totalItems",  String.valueOf(departementVOs.size()));
		dataTable.put("start", departementVOs.size() > 0 ? String.valueOf(offset) : "0");
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		response.getWriter().print(dataTable.toJSONString());
	}*/
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateDeptById", method = RequestMethod.GET)
	private @ResponseBody void updateDeptById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DepartementVO departementVO = new DepartementVO();
		JSONObject status = new JSONObject();
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		if(checkSession(request)){
			departementVO = globalService.getDeptByCode(request.getParameter("Kode"));
			if(departementVO != null){
				String tmpDeptName = departementVO.getName();
				departementVO.setCode(request.getParameter("Kode"));
				departementVO.setName(request.getParameter("Nama"));
				
				String activity = "";
				if (request.getParameter("Delete") != null){
					departementVO.setIsActive("N");
					activity = Constant.DELETE_DEPT.toString()+ " " + request.getParameter("Kode") + "-" + request.getParameter("Nama");
				} else {
					activity = Constant.UPDATE_DEPT.toString() + " " + request.getParameter("Kode") + "-" + tmpDeptName + " => " +request.getParameter("Nama");
				}
				
				try {
					globalService.updateDepartement(departementVO);
					String ipAddress = request.getHeader("X-FORWARDED-FOR");
					if (ipAddress == null) {
						   ipAddress = request.getRemoteAddr();
					}
					globalService.executeActLog(request.getParameter("ID_User"), ipAddress, request.getHeader("user-agent"), activity);
					status.put("status", "200");
				} catch (Exception e) {
					status.put("status", "201");
					status.put("message", "Error");
					logger.error("UPDATE DEPARTMENT", e);
					e.printStackTrace();
				}
			}else{
				status.put("status", "202");
				status.put("message", "Not Found");
			}
		}else{
			status.put("status", "203");
			status.put("message", "Access denied");
		}
		
		response.getWriter().print(status.toJSONString());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveDept", method = RequestMethod.GET)
	private @ResponseBody void saveDept(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DepartementVO departementVO = new DepartementVO();
		JSONObject status = new JSONObject();
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		
		if(checkSession(request)){
			try{
				departementVO.setCode(request.getParameter("Kode"));
				departementVO.setName(request.getParameter("Nama"));
				departementVO.setCreatedBy(request.getParameter("ID_User"));
				departementVO.setCreatedDate(new Date());
				departementVO.setIsActive("Y");

				globalService.saveNewDepartement(departementVO);
				String ipAddress = request.getHeader("X-FORWARDED-FOR");
				if (ipAddress == null) {
					   ipAddress = request.getRemoteAddr();
				}
				globalService.executeActLog(request.getParameter("ID_User"), ipAddress, request.getHeader("user-agent"), Constant.SAVE_DEPT.toString()+ " " + request.getParameter("Kode") + "-" + request.getParameter("Nama"));
				status.put("status", "200");
				
			} catch (Exception e) {
				status.put("status", "201");
				status.put("message", "Kode is exist");
				logger.error("SAVE DEPARTEMENT", e);
				e.printStackTrace();
			}
		}else{
			status.put("status", "202");
			status.put("message", "Access denied");
		}
		
		response.getWriter().print(status.toJSONString());
		
	}
}
