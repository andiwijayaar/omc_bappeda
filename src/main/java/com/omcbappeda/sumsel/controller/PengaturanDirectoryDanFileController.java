package com.omcbappeda.sumsel.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import com.omcbappeda.sumsel.model.DirectoryVO;
import com.omcbappeda.sumsel.model.FileVO;
import com.omcbappeda.sumsel.service.GlobalService;

@Controller
public class PengaturanDirectoryDanFileController extends MainController {
	private GlobalService globalService = (GlobalService) context.getBean("globalService");
	private static final Logger logger = LoggerFactory.getLogger(PengaturanDirectoryDanFileController.class);
	private String parentName = "";

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getFIleAndDirectoryInside", method = RequestMethod.GET)
	private @ResponseBody void getFIleAndDirectoryInside(HttpServletRequest request, HttpServletResponse response) throws IOException {
		super.preventCaching(response);
		JSONObject dataTable = new JSONObject();
		if(checkSession(request)){
			String id = request.getParameter("ID");
			
			DateFormat df = new SimpleDateFormat(Constant.DATEFORMAT.toString());		

			List<DirectoryVO> directoryVOs = new ArrayList<DirectoryVO>();
			List<FileVO> fileVOs = new ArrayList<FileVO>();
			String key = request.getParameter("key").equals("") ? null : request.getParameter("key");
			if(key == null){
				directoryVOs = globalService.getDirectoryByParent(id);
				fileVOs = globalService.getFileByDir(id);
			} else {
				directoryVOs = globalService.getDirectoryByName(key);
				fileVOs = globalService.getFileByName(key);
			}
			
			
			JSONArray jsonArray = new JSONArray();
			for (DirectoryVO directoryVO : directoryVOs) {
				JSONObject dataDir = new JSONObject();
				dataDir.put("Parent", directoryVO.getParent());
				dataDir.put("Nama", directoryVO.getName());
				dataDir.put("ID", directoryVO.getId());
				dataDir.put("isDirectory", true);
				dataDir.put("CreatedBy", directoryVO.getCreatedBy().getId());
				dataDir.put("CreatedDate", df.format(directoryVO.getCreatedDate()));
				dataDir.put("isActive", directoryVO.getIsActive());
				jsonArray.add(dataDir);
			}
			
			
			for (FileVO fileVO : fileVOs) {
				JSONObject dataFile = new JSONObject();
				dataFile.put("Directory", fileVO.getDirectoryVO().getId());
				dataFile.put("Nama", fileVO.getName());
				dataFile.put("ID", fileVO.getId());
				dataFile.put("isDirectory", false);
				dataFile.put("Extension", fileVO.getExtension());
				dataFile.put("CreatedBy", fileVO.getCreatedBy().getId());
				dataFile.put("CreatedDate", df.format(fileVO.getCreatedDate()));
				dataFile.put("isActive", fileVO.getIsActive());
				jsonArray.add(dataFile);
			}
			dataTable.put("msg","200");
			dataTable.put("items",jsonArray);
			
		}else{
			dataTable.put("access","denied");
			dataTable.put("msg","203");
		}
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		response.getWriter().print(dataTable.toJSONString());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getDirectoryInside", method = RequestMethod.GET)
	private @ResponseBody void getDirectoryInside(HttpServletRequest request, HttpServletResponse response) throws IOException {
		super.preventCaching(response);
		JSONObject dataTable = new JSONObject();
		if(checkSession(request)){
			String id = request.getParameter("ID");			
			DateFormat df = new SimpleDateFormat(Constant.DATEFORMAT.toString());
			List<DirectoryVO> directoryVOs = new ArrayList<DirectoryVO>();
			
			String key = request.getParameter("key").equals("") ? null : request.getParameter("key");
			if(key == null){
				directoryVOs = globalService.getDirectoryByParent(id);
			} else {
				directoryVOs = globalService.getDirectoryByName(key);
			}
			
			JSONArray jsonArray = new JSONArray();
			for (DirectoryVO directoryVO : directoryVOs) {
				JSONObject dataDir = new JSONObject();
				dataDir.put("Parent", directoryVO.getParent());
				dataDir.put("Nama", directoryVO.getName());
				dataDir.put("ID", directoryVO.getId());
				dataDir.put("isDirectory", true);
				dataDir.put("CreatedBy", directoryVO.getCreatedBy().getId());
				dataDir.put("CreatedDate", df.format(directoryVO.getCreatedDate()));
				dataDir.put("isActive", directoryVO.getIsActive());
				jsonArray.add(dataDir);
			}
			dataTable.put("msg","200");
			dataTable.put("items",jsonArray);
			
		}else{
			dataTable.put("access","denied");
			dataTable.put("msg","203");
		}
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		response.getWriter().print(dataTable.toJSONString());
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateDirectoryById", method = RequestMethod.GET)
	private @ResponseBody void updateDirectoryById(HttpServletRequest request, HttpServletResponse response) throws IOException {

		JSONObject dataTable = new JSONObject();
		
		if (checkSession(request)) {
			
			DirectoryVO directoryVO = globalService.getDirById(request.getParameter("id"));
			
			if(directoryVO != null){
				String activity = "";
				String tmpName = directoryVO.getName();
				try {
					if (request.getParameter("Delete").equalsIgnoreCase(Constant.YES.toString())){
						directoryVO.setIsActive("N");
						activity = Constant.DELETE_DIR.toString() + " (" + directoryVO.getId() + ") " + directoryVO.getName();
					} else{
						if (request.getParameter("Directory") != null && !request.getParameter("Directory").equalsIgnoreCase(Constant.EMPTY_STRING.toString())){
							if(request.getParameter("Directory").equalsIgnoreCase(directoryVO.getId()))							
								throw new Exception();
	
							DirectoryVO directoryParentVO = globalService.getDirById(request.getParameter("Directory"));
							
							directoryVO.setParent(request.getParameter("Directory"));
							activity = Constant.MOVE_DIRECTORY.toString() + " " + tmpName + " => " + directoryParentVO.getName();
						}else if (request.getParameter("name") != null){
							directoryVO.setName(request.getParameter("name"));
							activity = Constant.UPDATE_DIR.toString() + " " + tmpName + " => " + request.getParameter("name");
						}
					}
					
					try {
						globalService.updateDirectory(directoryVO);
						String ipAddress = request.getHeader("X-FORWARDED-FOR");
						if (ipAddress == null) {
							   ipAddress = request.getRemoteAddr();
						}
						
						globalService.executeActLog(request.getParameter("userId"), ipAddress, request.getHeader("user-agent"), activity);
						dataTable.put("msg", "200");
					} catch (Exception e) {
						dataTable.put("msg", "202");
						logger.error("UPDATE DIR", e);
						e.printStackTrace();
					}
				} catch (Exception e) {
					dataTable.put("msg", "204");
					logger.error("UPDATE DIR", e);
					e.printStackTrace();
				}
			}else{
				dataTable.put("msg", "203");
			}
			
		}
		
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		response.getWriter().print(dataTable.toJSONString());
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getDirById", method = RequestMethod.GET)
	private @ResponseBody void getDirById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		super.preventCaching(response);
		JSONObject dataTable = new JSONObject();
		if(checkSession(request)){
			JSONObject data = new JSONObject();
			String id = request.getParameter("id");
			if(!id.equalsIgnoreCase("root") && !id.equalsIgnoreCase("") ){
				DirectoryVO directoryVO = globalService.getDirById(id);
				
				DateFormat df = new SimpleDateFormat(Constant.DATEFORMAT.toString());
				
				data.put("Kode", directoryVO.getParent());
				data.put("Nama", directoryVO.getName());
				data.put("CreatedBy", directoryVO.getCreatedBy().getId());
				data.put("CreatedDate", df.format(directoryVO.getCreatedDate()));
				data.put("isActive", directoryVO.getIsActive());
				
			}else{
				data.put("Kode", "root");
				data.put("Nama", "");
			}
			dataTable.put("items",data);
			dataTable.put("msg","200");
			
		}else{
			dataTable.put("msg","201");
			dataTable.put("access","denied");
		}
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		response.getWriter().print(dataTable.toJSONString());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getDirByIdProperties", method = RequestMethod.GET)
	private @ResponseBody void getDirByIdProperties(HttpServletRequest request, HttpServletResponse response) throws IOException {
		super.preventCaching(response);
		JSONObject dataTable = new JSONObject();
		if(checkSession(request)){
			JSONObject data = new JSONObject();
			String id = request.getParameter("id");
			if(!id.equalsIgnoreCase("root") && !id.equalsIgnoreCase("") ){
				DirectoryVO directoryVO = globalService.getDirById(id);
				
				DateFormat df = new SimpleDateFormat(Constant.DATEFORMAT.toString());

				data.put("Nama", directoryVO.getName());
				data.put("CreatedBy", directoryVO.getCreatedBy().getName());
				data.put("CreatedDate", df.format(directoryVO.getCreatedDate()));
				data.put("isActive", directoryVO.getIsActive());
				
			}else{
				data.put("Kode", "root");
				data.put("Nama", "");
			}
			dataTable.put("items",data);
			dataTable.put("msg","200");
			
		}else{
			dataTable.put("msg","201");
			dataTable.put("access","denied");
		}
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		response.getWriter().print(dataTable.toJSONString());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getFileByIdProperties", method = RequestMethod.GET)
	private @ResponseBody void getFileByIdProperties(HttpServletRequest request, HttpServletResponse response) throws IOException {
		super.preventCaching(response);
		JSONObject dataTable = new JSONObject();
		parentName = "";
		if(checkSession(request)){
			JSONObject data = new JSONObject();
			String id = request.getParameter("id");
			if(!id.equalsIgnoreCase("root") && !id.equalsIgnoreCase("") ){
				FileVO fileVO = globalService.getFileById(id);
				
				DateFormat df = new SimpleDateFormat(Constant.DATEFORMAT.toString());
				
				data.put("Nama", fileVO.getName());
				data.put("pathName", getDirectoryParentByDirectoryId(fileVO.getDirectoryVO().getId()));
				data.put("CreatedBy", fileVO.getCreatedBy().getName());
				data.put("CreatedDate", df.format(fileVO.getCreatedDate()));
				data.put("isActive", fileVO.getIsActive());
				
			}else{
				data.put("Kode", "root");
				data.put("Nama", "");
			}
			dataTable.put("items",data);
			dataTable.put("msg","200");
			
		}else{
			dataTable.put("msg","201");
			dataTable.put("access","denied");
		}
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		response.getWriter().print(dataTable.toJSONString());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveDir", method = RequestMethod.GET)
	private @ResponseBody void saveDir(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DirectoryVO directoryVO = new DirectoryVO();
		JSONObject dataTable = new JSONObject();
		if (checkSession(request)) {
			try{
				String id = UUID.randomUUID().toString();
				directoryVO.setId(id);
				directoryVO.setParent(request.getParameter("Parent"));
				directoryVO.setName(request.getParameter("Nama"));
				directoryVO.setCreatedBy(globalService.getUserById(request.getParameter("userId")));
				directoryVO.setCreatedDate(new Date());
				directoryVO.setIsActive("Y");
				
				globalService.saveNewDirectory(directoryVO);
				String ipAddress = request.getHeader("X-FORWARDED-FOR");
				if (ipAddress == null) {
					   ipAddress = request.getRemoteAddr();
				}
				globalService.executeActLog(request.getParameter("userId"), ipAddress, request.getHeader("user-agent"), Constant.SAVE_DIR.toString() + " " + request.getParameter("Nama")+" (" + id + ")");
				
				response.setHeader("Content-Type", "application/json; charset=UTF-8");
				dataTable.put("id", id);
				dataTable.put("name", request.getParameter("Nama"));
				dataTable.put("msg", "200");
			} catch (Exception e) {
				response.setHeader("Content-Type", "application/json; charset=UTF-8");
				dataTable.put("access", "denied");
				dataTable.put("msg", "201");
				logger.error("SAVE DIR", e);
				e.printStackTrace();
			}
		}else {
			request.getSession().removeAttribute(USER_SESSION);
			response.setHeader("Content-Type", "application/json; charset=UTF-8");
			dataTable.put("access", "denied");
			dataTable.put("msg", "Forbiden Access");
		}
		
		response.getWriter().print(dataTable.toJSONString());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateFileById", method = RequestMethod.GET)
	private @ResponseBody void updateFileById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject dataTable = new JSONObject();
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		
		if(checkSession(request)){	
			FileVO fileVO = globalService.getFileById(request.getParameter("id"));
			if(fileVO != null){
				String activity = "";
				
				if (request.getParameter("Delete") != null && request.getParameter("Delete").equalsIgnoreCase(Constant.YES.toString())){
					fileVO.setIsActive("N");
					activity = Constant.DELETE_FILE.toString() + " (" + fileVO.getId() + ") " + fileVO.getName();
				} else {
					if (request.getParameter("Directory") != null && !request.getParameter("Directory").equals(Constant.EMPTY_STRING.toString())){
						String tmpParent = fileVO.getDirectoryVO().getName();
						fileVO.setDirectoryVO(globalService.getDirById(request.getParameter("Directory")));
						activity = Constant.MOVE_FILE.toString() + " " + tmpParent + " => " + fileVO.getDirectoryVO().getName();
					} else if (request.getParameter("Nama") != null){
						String tmpName = fileVO.getName();
						fileVO.setName(request.getParameter("Nama"));
						activity = Constant.UPDATE_FILE.toString() + " " + tmpName + " => " + fileVO.getName();	
					}
				}
				try {
					globalService.updateFile(fileVO);
					String ipAddress = request.getHeader("X-FORWARDED-FOR");
					if (ipAddress == null) {
						   ipAddress = request.getRemoteAddr();
					}
					globalService.executeActLog(request.getParameter("userId"), ipAddress, request.getHeader("user-agent"), activity);
					dataTable.put("msg", "200");
				} catch (Exception e) {
					dataTable.put("msg", "201");
					logger.error("UPDATE FILE", e);
					e.printStackTrace();
				}
			}else{
				dataTable.put("msg", "202");
			}
		}else{
			dataTable.put("msg", "203");
			request.getSession().removeAttribute(USER_SESSION);
		}
		
		response.getWriter().print(dataTable.toJSONString());
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveFile", method = RequestMethod.GET)
	private @ResponseBody void saveFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		FileVO fileVO = new FileVO();
		
		try{
			fileVO.setId(UUID.randomUUID().toString());
			fileVO.setDirectoryVO(globalService.getDirById(request.getParameter("Directory")));
			fileVO.setName(request.getParameter("Nama"));
			fileVO.setCreatedBy(globalService.getUserById(request.getParameter("CreatedBy")));
			fileVO.setCreatedDate(new Date());
			fileVO.setIsActive("Y");
			
			globalService.saveNewFile(fileVO);
			String ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
				   ipAddress = request.getRemoteAddr();
			}
			globalService.executeActLog(request.getParameter("userId"), ipAddress, request.getHeader("user-agent"), Constant.SAVE_FILE.toString());
		} catch (Exception e) {
			logger.error("SAVE FILE",e);
			e.printStackTrace();
		}
	}
	
	private String getDirectoryParentByDirectoryId(String id){
		DirectoryVO directoryVO = globalService.getDirById(id);
		
		if(directoryVO.getId().equals(Constant.ROOT_FOLDER.toString())){
			parentName = "ROOT/" + parentName;
		}else{
			parentName = directoryVO.getName() + "/" + parentName;
			getDirectoryParentByDirectoryId(directoryVO.getParent());
		}
		return parentName;
	}
}
