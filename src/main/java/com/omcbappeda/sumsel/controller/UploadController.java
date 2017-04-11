package com.omcbappeda.sumsel.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omcbappeda.sumsel.utilities.FileMeta;
import com.omcbappeda.sumsel.utilities.MultipartRequestHandler;

//this to be used with Java Servlet 3.0 API
@MultipartConfig 
@Controller
public class UploadController extends MainController {

	// this will store uploaded files
	private static List<FileMeta> files = new LinkedList<FileMeta>();
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	/***************************************************
	 * URL: /upload
	 * doPost(): upload the files and other parameters
	 ****************************************************/
	
	@RequestMapping(value = "/upload")
	public void upload (HttpServletRequest request, HttpServletResponse response) {
	    
		// 1. Upload File Using Java Servlet API
		//files.addAll(MultipartRequestHandler.uploadByJavaServletAPI(request));			
		
		// 1. Upload File Using Apache FileUpload
		try {
			files.addAll(MultipartRequestHandler.uploadByApacheFileUpload(request));
			// Remove some files
			while(files.size() > 20)
			{
				files.remove(0);
			}
			
			// 2. Set response type to json
			response.setContentType("application/json");
			
			// 3. Convert List<FileMeta> into JSON format
	    	ObjectMapper mapper = new ObjectMapper();
	    	
	    	// 4. Send resutl to client
	    	mapper.writeValue(response.getOutputStream(), files);
		} catch (IOException e) {
			logger.error("UPLOAD", e);
			e.printStackTrace();
		} catch (ServletException e) {
			logger.error("UPLOAD", e);
			e.printStackTrace();
		}
	
	}
	
	@RequestMapping(value = "/downloadFile")
	public void view (HttpServletRequest request, HttpServletResponse response) {

		 // 1. Get f from URL upload?f="?"
		 String value = request.getParameter("f");
		 
		 // 2. Get the file of index "f" from the list "files"
		 FileMeta getFile = files.get(Integer.parseInt(value));
		 
		 try {
			 	// 3. Set the response content type = file content type 
			 	response.setContentType(getFile.getFileType());
			 	
			 	// 4. Set header Content-disposition
			 	response.setHeader("Content-disposition", "attachment; filename=\""+getFile.getFileName()+"\"");
			 	
			 	// 5. Copy file inputstream to response outputstream
		        InputStream input = getFile.getContent();
		        OutputStream output = response.getOutputStream();
		        byte[] buffer = new byte[1024*10];
		        
		        for (int length = 0; (length = input.read(buffer)) > 0;) {
		            output.write(buffer, 0, length);
		        }
		        
		        output.close();
		        input.close();
		 }catch (IOException e) {
			 logger.error("DOWNLOAD FILE", e);
			 e.printStackTrace();
		 }
	}
	
	@RequestMapping(value = "/uploadImage", method = RequestMethod.GET)
	public void uploadImage (HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			
			if (!ServletFileUpload.isMultipartContent(request)) {
				out.print("Nothing to Upload");
				return;
			}
			
			FileItemFactory itemFactory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(itemFactory);
			
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem fileItem : items) {
//					String contentType = fileItem.getContentType();
//					if(!contentType.equals("image/png") || !contentType.equals("image/jpg") || !contentType.equals("image/jpeg")) {
//						out.println("only png format files supported");
//						continue;
//					}
				
				String url = request.getRequestURL().toString();
				URL aURL = new URL(url);
				
				File uploadDir = new File(System.getProperty( "catalina.home" )+"/omc-bappeda/public/upload/users/images");
				if(!uploadDir.exists()) {
					uploadDir.mkdirs();
				}
				System.out.println(">> " +System.getProperty( "catalina.base" ));
				System.out.println("==================LOCATION=====  : "+uploadDir.getAbsolutePath());
				File file = File.createTempFile("img",".png", uploadDir);
				fileItem.write(file);
				out.print("File Saved Successfully");
				
				JSONObject res = new JSONObject();
				res.put("code", 200);
				res.put("response", DatatypeConverter.printBase64Binary(uploadDir.getAbsolutePath().getBytes()));
			} 
			
		} catch (Exception e) {
			logger.error("UPLOAD IMAGE", e);
			e.printStackTrace();
		}
		
	}
}