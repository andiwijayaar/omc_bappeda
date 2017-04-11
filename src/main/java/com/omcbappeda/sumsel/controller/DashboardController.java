package com.omcbappeda.sumsel.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.omcbappeda.sumsel.constant.Constant;
import com.omcbappeda.sumsel.form.LoginForm;
import com.omcbappeda.sumsel.model.DepartementVO;
import com.omcbappeda.sumsel.model.UserVO;
import com.omcbappeda.sumsel.service.GlobalService;

@Controller
public class DashboardController extends MainController {
	
	private GlobalService globalService = (GlobalService) context.getBean("globalService");
	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
	
	public void setGlobalService(GlobalService globalService) {
		this.globalService = globalService;
	}
	
	@RequestMapping (value = "/addNewDepartement")
	public void addNewDepartement(HttpServletRequest request) {

		String departementName = request.getParameter("departementName");
		// String userSession = request.getParameter(USER_SESSION);

		DepartementVO departementVO = new DepartementVO();
		departementVO.setCreatedDate(new Date());

		DateFormat df = new SimpleDateFormat("ddMMyy");
		departementVO.setCode(departementName.substring(0, 3).concat(df.format(departementVO.getCreatedDate())));
		departementVO.setName(departementName);
		departementVO.setIsActive(Constant.YES.toString());
		departementVO.setCreatedBy("Hardcode");

		globalService.saveNewDepartement(departementVO);
	}
	
	@RequestMapping (value = "/logOut")
	public String logOut(HttpServletRequest request) {
		return "redirect:loginRequest";
	}
	
	@RequestMapping(value = "/approval", method = RequestMethod.GET)
	private @ResponseBody void approveUser(LoginForm form, HttpServletRequest request, HttpServletResponse response) throws IOException{
		String message = Constant.EMPTY_STRING.toString();
		JSONObject status = new JSONObject();
		if(checkSession(request)){
			try {
				String trash = request.getParameter("adminId");
				String state = request.getParameter("state");
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
				}
				
				UserVO userVO = globalService.getUserById(request.getParameter("userId"));
				
				userVO.setApproveBy(adminID);
				userVO.setApproveDate(new Date());
				
				if (state.equals("1")) {
					userVO.setApproval(Constant.APPROVED.toString());
					userVO.setIsActive(Constant.YES.toString());
					globalService.updateUser(userVO);
				} else {
					userVO.setApproval(Constant.REJECT.toString());
					globalService.deleteUser(userVO.getId());
				}
				
				
				message = userVO.getApproval();
				String ipAddress = request.getHeader("X-FORWARDED-FOR");
				if (ipAddress == null) {
					   ipAddress = request.getRemoteAddr();
				}
				
				globalService.executeApprove(adminID, ipAddress, request.getHeader("user-agent"), message, userVO.getId(), userVO.getName());
				
				status.put("status", message);
				
				
			} catch (Exception e) {
				message = "Access denied";
				status.put("status", message);
				logger.error("APPROVAL", e);
			}
		}else{
			message = "Failed";
			status.put("status", message);
		}
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		response.getWriter().print(status.toJSONString());
	}
			
}
