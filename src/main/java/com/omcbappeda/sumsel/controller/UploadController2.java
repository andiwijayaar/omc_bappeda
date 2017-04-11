package com.omcbappeda.sumsel.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.omcbappeda.sumsel.form.LoginForm;
import com.omcbappeda.sumsel.model.FileVO;
import com.omcbappeda.sumsel.service.GlobalService;
import com.omcbappeda.sumsel.utilities.UploadProfilePicture;

@Controller
public class UploadController2 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	private GlobalService globalService = (GlobalService) context.getBean("globalService");
	private static final Logger logger = LoggerFactory.getLogger(UploadController2.class);
//	public static final long MAX_UPLOAD_IN_MEGS = 50;

	@RequestMapping(value = "/save-product", method = RequestMethod.POST)
	public @ResponseBody void saveFile(HttpServletRequest servletRequest, HttpServletResponse response, @ModelAttribute UploadedFile uploadedFile,
			BindingResult bindingResult, Model model) throws IOException {
		
		MultipartFile multipartFile = uploadedFile.getMultipartFile();
		int status = 0;
		
		String fileName = multipartFile.getOriginalFilename();
		//File mdir = new File(servletRequest.getServletContext().getRealPath("/public/uploaded"));
		File mdir = new File("/public/uploaded/");
		if(!mdir.exists())
			mdir.mkdirs();
		
		try {
			System.out.println("======== UPLOADING :"+fileName);
			
			String id = UUID.randomUUID().toString();
			String[] bits = fileName.split("\\.");
			String ext = bits[bits.length-1];
			
			//File file = new File(servletRequest.getServletContext().getRealPath("/public/uploaded/"), id+"."+ext);
			File file = new File("/public/uploaded/", id+"."+ext);
			
			System.out.println("**** "+file);
			
			multipartFile.transferTo(file);
			saveFileInfo(uploadedFile, file, ext, id);
			System.out.println("=========== SUCCESS :"+fileName);
			status = 200;
		} catch (IOException e) {
			System.out.println("======== FAILED :"+fileName);
			status = 201;
			logger.error("SAVE PRODUCT",e);
			e.printStackTrace();
		}
		
		response.setHeader("Content-Type", "application/text; charset=UTF-8");
		response.getWriter().print(status);
	}
	
	private void saveFileInfo(UploadedFile uploadedFile, File file, String ext, String id) throws IOException {
		FileVO fileVO = new FileVO();
		
		try{
			fileVO.setId(id);
			fileVO.setDirectoryVO(globalService.getDirById(uploadedFile.getIdFolder()));
			fileVO.setName(uploadedFile.getMultipartFile().getOriginalFilename());
			fileVO.setCreatedBy(globalService.getUserById(uploadedFile.getIdUser()));
			fileVO.setCreatedDate(new Date());
			fileVO.setExtension(ext);
			fileVO.setPath(file.getAbsolutePath());
			fileVO.setIsActive("Y");
			
			globalService.saveNewFile(fileVO);
		} catch (Exception e) {
			logger.error("SAVE FILE", e);
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/save-profile", method = RequestMethod.POST)
	public @ResponseBody void saveProfileImage(HttpServletRequest servletRequest, @ModelAttribute UploadProfilePicture uploadedFile,
			BindingResult bindingResult, Model model) {
		
		String trash = uploadedFile.getTrash();
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		JSONArray arr = null;
		String idx = null;
		
		try {
			String decoded = new String(DatatypeConverter.parseBase64Binary(trash));
			jsonObject = (JSONObject) parser.parse(decoded);
			arr = (JSONArray) jsonObject.get("trash");
			
			for(int i=0; i<arr.size(); i++){
				JSONObject obj = (JSONObject) parser.parse(arr.get(i).toString());
				if(obj.containsKey("signing")){
					idx = obj.get("signing").toString();
				}
				
				if(idx != null)
					continue;
			}
		} catch (ParseException e) {
			logger.error("SAVE PROFILE", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MultipartFile multipartFile = uploadedFile.getMultipartFile();
		String fileName = multipartFile.getOriginalFilename();
		File mdir = new File(servletRequest.getServletContext().getRealPath("/public/users/images/"));
		if(!mdir.exists())
			mdir.mkdirs();
		
		File mdirNew = new File(servletRequest.getServletContext().getRealPath("/public/users/images/new"));
		if(!mdirNew.exists())
			mdirNew.mkdirs();
		
		try {
			System.out.println("======== UPLOADING :"+fileName);
			File file = new File(servletRequest.getServletContext().getRealPath("/public/users/images/"), idx+".jpg");
			File fileNew = new File(servletRequest.getServletContext().getRealPath("/public/users/images/new/"), idx+".jpg");
			System.out.println("**** "+file);
			multipartFile.transferTo(file);
			multipartFile.transferTo(fileNew);
		} catch (IOException e) {
			System.out.println("======== FAILED :"+fileName);
			logger.error("SAVE PROFILE",e);
			e.printStackTrace();
		}
		System.out.println(idx);
		System.out.println("=========== SUCCESS :"+fileName);
	}

	@RequestMapping(value = "/product-input-form")
	public String inputProduct(Model model) {
		return "productForm";
	}
	
	/* ========================================================================================================= */
	@RequestMapping(value = "/upload2")
	public void doPost(HttpServletRequest request, HttpServletResponse response, LoginForm form) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("Hello<br/>");

		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if (!isMultipartContent) {
			out.println("You are not trying to upload<br/>");
			return;
		}
		out.println("You are trying to upload<br/>");

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
//		upload.setSizeMax(MAX_UPLOAD_IN_MEGS * 1024 * 1024);
		
		TestProgressListener testProgressListener = new TestProgressListener();
		upload.setProgressListener(testProgressListener);
		
		HttpSession session = request.getSession();
		session.setAttribute("testProgressListener", testProgressListener);
		
		try {
			List<FileItem> fields = upload.parseRequest(request);
			out.println("Number of fields: " + fields.size() + "<br/><br/>");
			Iterator<FileItem> it = fields.iterator();
			if (!it.hasNext()) {
				out.println("No fields found");
				return;
			}
			out.println("<table border=\"1\">");
			while (it.hasNext()) {
				out.println("<tr>");
				FileItem fileItem = it.next();
				boolean isFormField = fileItem.isFormField();
				if (isFormField) {
					out.println("<td>regular form field</td><td>FIELD NAME: " + fileItem.getFieldName() + 
							"<br/>STRING: " + fileItem.getString()
							);
					out.println("</td>");
				} else {
					out.println("<td>file form field</td><td>FIELD NAME: " + fileItem.getFieldName() +
//							"<br/>STRING: " + fileItem.getString() +
							"<br/>NAME: " + fileItem.getName() +
							"<br/>CONTENT TYPE: " + fileItem.getContentType() +
							"<br/>SIZE (BYTES): " + fileItem.getSize() +
							"<br/>TO STRING: " + fileItem.toString()
							);
					out.println("</td>");
				}
				out.println("</tr>");
			}
			out.println("</table>");
		} catch (FileUploadException e) {
			out.println("Error: " + e.getMessage());
			logger.error("UPLOAD2",e);
			e.printStackTrace();
		}
	}
}