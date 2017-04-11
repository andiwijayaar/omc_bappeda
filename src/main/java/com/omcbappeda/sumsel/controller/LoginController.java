package com.omcbappeda.sumsel.controller;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.omcbappeda.sumsel.constant.Constant;
import com.omcbappeda.sumsel.form.LoginForm;
import com.omcbappeda.sumsel.form.UserForm;
import com.omcbappeda.sumsel.model.DepartementVO;
import com.omcbappeda.sumsel.model.UserVO;
import com.omcbappeda.sumsel.service.GlobalService;
import com.omcbappeda.sumsel.utilities.TryLoginAccess;

@Controller
public class LoginController extends MainController {
	private final static String LOGIN_VIEW = "login/login_view";
	private final static String USER_DASHBOARD = "index";
	private final static String REGISTER_VIEW = "login/register_view";
	private final static String REGISTER_RESULT = "login/register_result";
	private GlobalService globalService = (GlobalService) context.getBean("globalService");
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	public void setGlobalService(GlobalService globalService) {
		this.globalService = globalService;
	}

	@RequestMapping(value = "/loginRequest")
	public String loginRequest(LoginForm form, HttpServletRequest request) throws SQLException {
		request.getSession().removeAttribute(USER_SESSION);
		checkFormError(request, form, "errorLogin");
		
		return LOGIN_VIEW;
	}

	@RequestMapping(value = "/login")
	public String login(LoginForm form, HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		try {
			UserVO userVO = globalService.getUserByUserIdAndPassword(form.getUserId(), form.getPassword());
			redirectAttributes.addFlashAttribute("userVO", userVO);
			String ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
				   ipAddress = request.getRemoteAddr();
			}
			globalService.executeActLog(userVO.getId(), ipAddress, request.getHeader("user-agent"), Constant.LOGIN.toString());
			return "redirect:/";
		} catch (Exception e) {
			logger.error("LOGIN ERROR", e);
			TryLoginAccess brute = new TryLoginAccess();
			brute.write(request, form);
			return "redirect:loginRequest?message=".concat("errorLogin");
		}
	}
	
	
	@RequestMapping(value = "/")
	public String loginSuccess(@ModelAttribute("userVO") UserVO vo, UserForm form, HttpServletRequest request) throws SQLException {
		try {
			setUserFormByUserVO(form, vo);	
		} catch (Exception e) {
			logger.error("INDEX LOGIN ERROR", e);
			return "redirect:loginRequest";
		}
		
		long time = System.currentTimeMillis();
		String signing = DatatypeConverter.printBase64Binary(String.valueOf(time*222).getBytes());
		int random = (int )(Math.random() * 9 + 1);
		
		JSONArray array = new JSONArray();
		
		JSONObject key = new JSONObject();
		key.put("key", String.valueOf(time).substring(random)+signing.substring(random, random+2));
		
		JSONObject sign = new JSONObject();
		sign.put("signing", vo.getId());
		
		JSONObject username = new JSONObject();
		username.put("username", vo.getUserId());
		
		JSONObject signature = new JSONObject();
		signature.put("signature", signing);
		
		array.add(username);
		array.add(sign);
		array.add(signature);
		array.add(key);
		
		JSONObject trash = new JSONObject();
		trash.put("trash", array);
		
		String encoded = DatatypeConverter.printBase64Binary(trash.toJSONString().getBytes());
		request.getSession().setAttribute(USER_SESSION, encoded);
		
		List<DepartementVO> dataDept = globalService.getAllDept();
		
		Map<String, String> mapDept = new HashMap<String, String>();
		Map<String, String> mapRole = new HashMap<String, String>();
		
		if(dataDept != null){
			for(DepartementVO deptVo : dataDept){
				mapDept.put(deptVo.getCode(), deptVo.getName());
			}
		}
		
		
		request.setAttribute("departementListAll", mapDept);
		request.setAttribute("roleListAll", mapRole);
		
		request.setAttribute("idxID", vo.getId());
		request.setAttribute("isAdmin", vo.getIsAdmin());
		if (!Constant.YES.toString().equals(vo.getIsAdmin())) {
			request.setAttribute("deptCode", vo.getDepartementVO().getCode());
		}
		
		File file = new File(request.getServletContext().getRealPath("/public/users/images/"), vo.getId()+".jpg");
		if(file.exists()){
			request.setAttribute("imgPicture", "/public/users/images/"+vo.getId()+".jpg?timestamp=12");
		}else{
			if(vo.getGender().equalsIgnoreCase(Constant.MAN.toString())){
				request.setAttribute("imgPicture", "/resources/img/no-image-1.png");
			}else{
				request.setAttribute("imgPicture", "/resources/img/no-image-2.png");
			}
		}
		
		
		return USER_DASHBOARD;
	}
	
	@RequestMapping(value = "/register")
	public String register(LoginForm form, HttpServletRequest request) {
		checkFormError(request, form, "errorRegister");
		Map<String, Object> dataMap = globalService.getDataMap();
		
		request.setAttribute("departementList", dataMap.get("departementList"));
		request.setAttribute("genderMap", dataMap.get("genderMap"));
		return REGISTER_VIEW;
	}
	
	@RequestMapping(value = "/saveNewUser")
	public String saveNewUser(LoginForm form, HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		try {
			globalService.saveNewUser(form);
			redirectAttributes.addFlashAttribute("registerForm", form);
			return REGISTER_RESULT;
		} catch (Exception e) {
			logger.error("SAVE USER ERROR", e);
			return "redirect:register?message=".concat("errorRegister");
		}
	}
	
	private void setUserFormByUserVO(UserForm form, UserVO vo){
		BeanUtils.copyProperties(vo, form);
		form.setJenisKelamin(vo.getGender());
		if (!vo.getIsAdmin().equals(Constant.YES.toString())) {
			form.setDepartement(vo.getDepartementVO().getName());
		}
	}
	
/*	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getDataTableRequestUser", method = RequestMethod.GET)
	private @ResponseBody void prepareDataTable(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String session = request.getParameter("trash").equals(Constant.EMPTY_STRING.toString()) || request.getParameter("trash").equals(null) ?  "eyJhY2Nlc3MiIDogImRlbmllZCJ9" : request.getParameter("trash") ;

		String key = request.getParameter("key").equals("") ? null : request.getParameter("key");
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		
		JSONObject dataTable = new JSONObject();
		try {
			String decoded = new String(DatatypeConverter.parseBase64Binary(session));
			jsonObject = (JSONObject) parser.parse(decoded);
			String encoded = DatatypeConverter.printBase64Binary(jsonObject.toJSONString().getBytes());
			
			if (checkSession(request)) {
				String limit = request.getParameter("perPage");
				String page = request.getParameter("page");
				
				int offset = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);
				double floor = Math.floor((double)offset/(double)Integer.valueOf(limit));
				int pageIndex = (int) floor + 1;
				List<UserVO> userVOList = new ArrayList<UserVO>();
				int totalItems = 0;
				if(key==null){
					userVOList = globalService.getUserByApproval(Integer.valueOf(limit), offset, Constant.REQUEST.toString());
					totalItems = globalService.countUser(Constant.NO.toString(), Constant.REQUEST.toString());
				} else {
					userVOList = globalService.getUserByUsername(Integer.valueOf(limit), offset, key, Constant.REQUEST.toString());
					totalItems = globalService.countUserByName(Constant.NO.toString(), Constant.REQUEST.toString(), key);
				}
				
				
				dataTable.put("perPage", String.valueOf(limit));
				dataTable.put("startIndex", String.valueOf(offset));
				dataTable.put("pageIndex", String.valueOf(pageIndex));
				
				SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy HH:mm:ss");
				
				JSONArray jsonArray = new JSONArray();
				for (UserVO userVO : userVOList) {
					JSONObject data = new JSONObject();
					data.put("ID", userVO.getId());
					data.put("ID_Permintaan", userVO.getUserId());
					data.put("Nama_Peminta", userVO.getName());
					data.put("Tanggal_Permintaan", String.valueOf(ft.format(userVO.getCreatedDate())));
					data.put("Departement", userVO.getDepartementVO().getName());
					data.put("Email", userVO.getEmail());
					data.put("Jabatan", userVO.getJabatan());
					
					jsonArray.add(data);
				}
				
				dataTable.put("items",jsonArray);
				dataTable.put("totalItems",  String.valueOf(totalItems));
				dataTable.put("start", totalItems > 0 ? String.valueOf(offset) : "0");
				
				double totalPage = Math.ceil((double)totalItems/(double)Integer.valueOf(limit));
				dataTable.put("totalPage", String.valueOf((int)totalPage));
				
				int totalItem = userVOList.size();
				
				dataTable.put("currentItemCount",  String.valueOf(totalItem));
				response.setHeader("Content-Type", "application/json; charset=UTF-8");
				response.getWriter().print(dataTable.toJSONString());
			} else {
				request.getSession().removeAttribute(USER_SESSION);
				response.setHeader("Content-Type", "application/json; charset=UTF-8");
				dataTable.put("access", "denied");
				dataTable.put("msg", "Forbiden Access");
				response.getWriter().print(dataTable.toJSONString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().removeAttribute(USER_SESSION);
			response.setHeader("Content-Type", "application/json; charset=UTF-8");
			dataTable.put("access", "denied");
			dataTable.put("msg", "Forbiden Access");
			response.getWriter().print(dataTable.toJSONString());
		}
	}*/
	
	private void checkFormError(HttpServletRequest request, LoginForm form, String errorMsg) {
		if (request.getParameter("message") != null && request.getParameter("message").equals(errorMsg)) {
			if (errorMsg.equals("errorLogin")) {
				form.setErrorMessage("User/Password salah atau User tidak aktif");
			} else if (errorMsg.equals("errorRegister")) {
				form.setErrorMessage("Email/UserName sudah terdaftar");
			}
		}
	}
}
