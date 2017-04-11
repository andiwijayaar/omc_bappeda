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
import javax.xml.bind.DatatypeConverter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.omcbappeda.sumsel.constant.Constant;
import com.omcbappeda.sumsel.model.DepartementVO;
import com.omcbappeda.sumsel.model.UserVO;
import com.omcbappeda.sumsel.service.GlobalService;
import com.omcbappeda.sumsel.utilities.DecryptPassword;

@Controller
public class PengaturanUserController extends MainController {
	private GlobalService globalService = (GlobalService) context.getBean("globalService");
	
	/*@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getDataTableUser", method = RequestMethod.GET)
	private @ResponseBody void prepareDataTableUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject dataTable = new JSONObject();
		
		String limit = request.getParameter("perPage");
		String page = request.getParameter("page");
		int offset = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);
		double floor = Math.floor((double)offset/(double)Integer.valueOf(limit));
		int pageIndex = (int) floor + 1;
		
		List<UserVO> userVOList = globalService.getUserByPaging(Integer.valueOf(limit), offset);
		
		dataTable.put("perPage", String.valueOf(limit));
		dataTable.put("startIndex", String.valueOf(offset));
		dataTable.put("pageIndex", String.valueOf(pageIndex));
		
		JSONArray jsonArray = new JSONArray();
		for (UserVO userVO : userVOList) {
			JSONObject data = new JSONObject();
			data.put("Username", userVO.getUserId());
			data.put("Nama", userVO.getName());
			data.put("Departement", userVO.getDepartement().getName());
			data.put("Email", userVO.getEmail());
			data.put("Nomor_HP", userVO.getPhone());
			data.put("ID", userVO.getId());
			
			jsonArray.add(data);
		}
		
		int totalItem = userVOList.size();
		
		dataTable.put("items",jsonArray);
		dataTable.put("totalItems",  String.valueOf(totalItem));
		dataTable.put("start", totalItem > 0 ? String.valueOf(offset) : "0");
		
		double totalPage = Math.ceil((double)totalItem/(double)Integer.valueOf(limit));
		dataTable.put("totalPage", String.valueOf((int)totalPage));
		
		dataTable.put("currentItemCount",  String.valueOf(totalItem));
		
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		response.getWriter().print(dataTable.toJSONString());
	}*/
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getUserById", method = RequestMethod.GET)
	private @ResponseBody void prepareUserById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		super.preventCaching(response);
		String id = request.getParameter("id");
		UserVO userVO = globalService.getUserById(id);
		JSONObject dataTable = new JSONObject();
		JSONObject data = new JSONObject();
		DateFormat df = new SimpleDateFormat(Constant.DATEFORMAT.toString());
		
		data.put("ID", userVO.getId());
		data.put("Username", userVO.getUserId());
		data.put("Nama", userVO.getName());
		data.put("Gender", userVO.getGender());
		data.put("Email", userVO.getEmail());
		data.put("Nomor_HP", userVO.getPhone());
		data.put("Jabatan", userVO.getJabatan());
		data.put("Departement", userVO.getDepartementVO().getCode());
		data.put("Is_Admin", userVO.getIsAdmin());
		data.put("CreatedDate", df.format(userVO.getCreatedDate()));
		data.put("Is_Active", userVO.getIsActive());
		data.put("Approval", userVO.getApproval());
		data.put("ApproveDate",  userVO.getApproveDate()!=null ? df.format(userVO.getApproveDate()) : "");
		data.put("ApproveBy", userVO.getApproveBy()!=null ? userVO.getApproveBy() : "");
		

		dataTable.put("items",data);
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		response.getWriter().print(dataTable.toJSONString());
	}
	
	/*@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getUserByName", method = RequestMethod.GET)
	private @ResponseBody void prepareUserByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String key = request.getParameter("key").equals("") ? request.getParameter("key") : null;
		JSONObject dataTable = new JSONObject();
		
		String limit = request.getParameter("perPage");
		String page = request.getParameter("page");
		int offset = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);
		double floor = Math.floor((double)offset/(double)Integer.valueOf(limit));
		int pageIndex = (int) floor + 1;		

		List<UserVO> userVOList = globalService.getUserByUsername(Integer.valueOf(limit), offset, key, Constant.APPROVED.toString());
		
		dataTable.put("perPage", String.valueOf(limit));
		dataTable.put("startIndex", String.valueOf(offset));
		dataTable.put("pageIndex", String.valueOf(pageIndex));
		DateFormat df = new SimpleDateFormat(Constant.DATEFORMAT.toString());
		
		JSONArray jsonArray = new JSONArray();
		for (UserVO userVO : userVOList) {
			JSONObject data = new JSONObject();
			
			data.put("Username", userVO.getUserId());
			data.put("Nama", userVO.getName());
			data.put("Gender", userVO.getGender());
			data.put("Email", userVO.getEmail());
			data.put("Nomor_HP", userVO.getPhone());
			data.put("Jabatan", userVO.getJabatan());
			data.put("Departement", userVO.getDepartementVO().getName());
			data.put("Is_Admin", userVO.getIsAdmin());
			data.put("ID", userVO.getId());
			data.put("CreatedDate", df.format(userVO.getCreatedDate()));
			data.put("Is_Active", userVO.getIsActive());
			data.put("Approval", userVO.getApproval());
			data.put("ApproveDate",  df.format(userVO.getApproveDate()));
			data.put("ApproveBy", userVO.getApproveBy());
			
			jsonArray.add(data);
		}
		
		int totalItem = globalService.countUserByName(Constant.YES.toString(), Constant.APPROVED.toString(), key);
		
		dataTable.put("items",jsonArray);
		dataTable.put("totalItems",  String.valueOf(totalItem));
		dataTable.put("start", totalItem > 0 ? String.valueOf(offset) : "0");
		
		double totalPage = Math.ceil((double)totalItem/(double)Integer.valueOf(limit));
		dataTable.put("totalPage", String.valueOf((int)totalPage));
		
		dataTable.put("currentItemCount",  String.valueOf(totalItem));
		
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		response.getWriter().print(dataTable.toJSONString());
	}*/
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getUserByPaging", method = RequestMethod.GET)
	private @ResponseBody void prepareUserByPaging(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject dataTable = new JSONObject();
		
		int limit = Integer.valueOf(request.getParameter("perPage"));
		String page = request.getParameter("page");
		String key = request.getParameter("key").equals("") ? null : request.getParameter("key");
		String approval = request.getParameter("approval");
		
		int offset = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);
		double floor = Math.floor((double)offset/(double)Integer.valueOf(limit));
		int pageIndex = (int) floor + 1;
		String isActive = request.getParameter("isActive");

		if(approval.equalsIgnoreCase(Constant.APPROVED.toString())){
			isActive = Constant.YES.toString();
		} else if (approval.equalsIgnoreCase(Constant.REQUEST.toString())){
			isActive = Constant.NO.toString();			
		}
		
		List<UserVO> userVOList = new ArrayList<UserVO>();
		int totalItem = 0;
		
		if(key==null){
			userVOList = globalService.getUserByPaging(isActive, approval, limit, offset);
			totalItem = globalService.countUser(isActive, approval);
		} else {
			userVOList = globalService.getUserByUsername(isActive, approval, key, limit, offset);
			totalItem = globalService.countUserByName(isActive, approval, key);
		}
		
		dataTable.put("perPage", String.valueOf(limit));
		dataTable.put("startIndex", String.valueOf(offset));
		dataTable.put("pageIndex", String.valueOf(pageIndex));
		DateFormat df = new SimpleDateFormat(Constant.DATEFORMAT.toString());
		
		JSONArray jsonArray = new JSONArray();
		for (UserVO userVO : userVOList) {
			JSONObject data = new JSONObject();
			
			data.put("Username", userVO.getUserId());
			data.put("Nama", userVO.getName());
			data.put("Gender", userVO.getGender());
			data.put("Email", userVO.getEmail());
			data.put("Nomor_HP", userVO.getPhone());
			data.put("Jabatan", userVO.getJabatan());
			data.put("Departement", userVO.getDepartementVO().getCode());
			data.put("Is_Admin", userVO.getIsAdmin());
			data.put("ID", userVO.getId());
			data.put("CreatedDate", df.format(userVO.getCreatedDate()));
			data.put("Is_Active", userVO.getIsActive());
			data.put("Approval", userVO.getApproval());
			data.put("ApproveDate",  userVO.getApproveDate()!=null ? df.format(userVO.getApproveDate()) : "");
			data.put("ApproveBy", userVO.getApproveBy()!=null ? userVO.getApproveBy() : "");
			
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
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/updateUserById", method = RequestMethod.GET)
	private @ResponseBody void updateUserById(HttpServletRequest request, HttpServletResponse response) throws IOException {	
		JSONObject dataTable = new JSONObject();
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		if(checkSession(request)){
			if(request.getParameter("ID") != null && !request.getParameter("ID").equals("")){
				UserVO userVO = globalService.getUserById(request.getParameter("ID"));
				
				if(userVO != null){
					//editable
					List<String> changed= new ArrayList<String>();
					if(!userVO.getName().equals(request.getParameter("Nama") != null ? request.getParameter("Nama") : userVO.getName()))
						changed.add("{Nama: "+userVO.getName()+" => "+request.getParameter("Nama")+"}");
					if(request.getParameter("password") != null && !request.getParameter("password").equals(Constant.EMPTY_STRING.toString()))
						changed.add("{Password: telah diubah}");
					if(!userVO.getGender().equals(request.getParameter("Gender") != null ? request.getParameter("Gender") : userVO.getGender()))
						changed.add("{Gender: "+userVO.getGender()+" => "+request.getParameter("Gender")+"}");
					if(!userVO.getEmail().equals(request.getParameter("Email") != null ? request.getParameter("Email") : userVO.getEmail()))
						changed.add("{Email: "+userVO.getEmail()+" => "+request.getParameter("Email")+"}");
					if(!userVO.getPhone().equals(request.getParameter("Nomor_HP") != null ? request.getParameter("Nomor_HP") : userVO.getPhone()))
						changed.add("{No HP: "+userVO.getPhone()+" => "+request.getParameter("Nomor_HP")+"}");
					if(!userVO.getJabatan().equals(request.getParameter("Jabatan") != null ? request.getParameter("Jabatan") : userVO.getJabatan()))
						changed.add("{Jabatan: "+userVO.getJabatan()+" => "+request.getParameter("Jabatan")+"}");
					if(!userVO.getIsAdmin().equals(request.getParameter("isAdmin") != null ? request.getParameter("isAdmin") : userVO.getIsAdmin()))
						changed.add("{Admin: "+userVO.getIsAdmin()+" => "+request.getParameter("isAdmin")+"}");
							
					userVO.setId(request.getParameter("ID") != null ? request.getParameter("ID") : userVO.getId());
					userVO.setName(request.getParameter("Nama") != null ? request.getParameter("Nama") : userVO.getName());
					userVO.setGender(request.getParameter("Gender") != null ? request.getParameter("Gender") : userVO.getGender());
					userVO.setEmail(request.getParameter("Email") != null ? request.getParameter("Email") : userVO.getEmail());
					userVO.setPhone(request.getParameter("Nomor_HP") != null ? request.getParameter("Nomor_HP") : userVO.getPhone());
					userVO.setJabatan(request.getParameter("Jabatan") != null ? request.getParameter("Jabatan") : userVO.getJabatan());
					userVO.setIsAdmin(request.getParameter("isAdmin") != null ? request.getParameter("isAdmin") : userVO.getIsAdmin());
					
					DepartementVO departementVO = new DepartementVO();
					if (request.getParameter("Departement") != null) {
						departementVO = globalService.getDeptByCode(request.getParameter("Departement"));
						
						if(!userVO.getDepartementVO().getCode().equals(departementVO.getCode()))
							changed.add("{Departemen: "+userVO.getDepartementVO().getName()+" => "+departementVO.getName()+"}");
						
					} else {
						departementVO = userVO.getDepartementVO();
					}
					
					userVO.setDepartementVO(departementVO);
					
					if(request.getParameter("password") != null && !request.getParameter("password").equals(Constant.EMPTY_STRING.toString())){
						DecryptPassword enc = new DecryptPassword();
						userVO.setPassword(enc.encryptPassword(request.getParameter("password")));
					}
					
					try {
						String activity = "";
						if(request.getParameter("Delete")!=null){
							if (request.getParameter("Approval").equals(Constant.REJECT.toString())){
								//reject
								activity = Constant.REJECT_USER.toString() + " " + userVO.getUserId();
							} else {
								//delete
								userVO.setIsActive(Constant.NO.toString());
								activity = Constant.DELETE_USER.toString() + " " + userVO.getUserId();
							}
						} else {
							if (request.getParameter("Approval").equals(Constant.APPROVED.toString())){
								//approve
								/*String trash = request.getParameter("adminId");
								String decoded = new String(DatatypeConverter.parseBase64Binary(trash));
								
								JSONParser parser = new JSONParser();
								JSONObject newObj = (JSONObject) parser.parse(decoded);
								JSONArray newArr = (JSONArray) newObj.get("trash");
								
								String adminID = "";
								for (int i = 0; i < newArr.size(); i++) {
									JSONObject objID = (JSONObject) parser.parse(newArr.get(i).toString());
									if(objID.containsKey("signing")){
										adminID = objID.get("signing").toString();
										continue;
									}
								}*/
								
								userVO.setIsActive(Constant.YES.toString());
								userVO.setApproval(Constant.APPROVED.toString());
								userVO.setApproveBy(request.getParameter("adminId"));
								userVO.setApproveDate(new Date());
								activity = Constant.APPROVE_USER.toString() + " " + userVO.getName();
							} else {
								String username = request.getParameter("Username");
								String email = request.getParameter("Email");
								try{
									if (username!=null && username.equalsIgnoreCase(userVO.getUserId())){
										if (globalService.isUserIdExist(username)){
											dataTable.put("message", "205");
											dataTable.put("status", "Username is exist");
											throw new Exception();										
										} else {
											userVO.setUserId(username);
										}
									} else if (email!=null && username.equalsIgnoreCase(userVO.getEmail())){
										if (globalService.isEmailExist(email)){
											dataTable.put("message", "206");
											dataTable.put("status", "Email is exist");
											throw new Exception();										
										} else {
											userVO.setEmail(email);
										}								
									}
								} catch (Exception e) {
									System.out.println(dataTable.get("status"));
								}
								//update
								activity = Constant.UPDATE_USER.toString() + " " + userVO.getUserId() +"\n LOG:" +changed.toString();
							}
						}
						
						if (activity.equals(Constant.REJECT_USER.toString())){
							globalService.deleteUser(request.getParameter("ID"));							
						} else {
							globalService.updateUser(userVO);							
						}
						String ipAddress = request.getHeader("X-FORWARDED-FOR");
						if (ipAddress == null) {
							   ipAddress = request.getRemoteAddr();
						}
						globalService.executeActLog(request.getParameter("adminId"), ipAddress, request.getHeader("user-agent"), activity);
						dataTable.put("message", "200");
						response.getWriter().print(dataTable.toJSONString());
					} catch (Exception e) {
						e.printStackTrace();
						dataTable.put("message", "201");
						response.getWriter().print(dataTable.toJSONString());
					}
				}else{
					dataTable.put("message", "202");
					response.getWriter().print(dataTable.toJSONString());
				}	
			}else{
				dataTable.put("message", "203");
				response.getWriter().print(dataTable.toJSONString());
			}
		}
	}
}
