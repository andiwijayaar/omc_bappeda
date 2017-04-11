package com.omcbappeda.sumsel.controller;
 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.omcbappeda.sumsel.model.FileVO;
import com.omcbappeda.sumsel.service.GlobalService;
 
@Controller
public class FileDownloadController extends MainController{
	private GlobalService globalService = (GlobalService) context.getBean("globalService");
   
    @RequestMapping(value="/download/{id}/{trash}", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response, HttpServletRequest request, @PathVariable("id") String id, @PathVariable("trash") String trash) throws IOException {
     
        File file = null;
        if(checkTrash(trash, request)){
            FileVO fileVO = globalService.getFileById(id);
            if(fileVO != null){
            	file = new File(fileVO.getPath());
            	if(!file.exists()){
					String errorMessage = "Sorry. The file you are looking for does not exist";
					System.out.println(errorMessage);
					OutputStream outputStream = response.getOutputStream();
					outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
					outputStream.close();
					return;
				}
            	
            	String mimeType= URLConnection.guessContentTypeFromName(file.getName());
                if(mimeType==null){
                    System.out.println("mimetype is not detectable, will take default");
                    mimeType = "application/octet-stream";
                }
                 
                System.out.println("mimetype : "+mimeType);
                 
                response.setContentType("application/x-download"); 
                
                response.setHeader("Content-Disposition", "attachment; filename=\"" + fileVO.getName()+"\"");
                 
//                response.setHeader("Content-Disposition", String.format("inline; filename=\"" + fileVO.getName()+"\""));
         
                 
                response.setContentLength((int)file.length());
         
                InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
         
                //Copy bytes from source to destination(outputstream in this example), closes both streams.
                FileCopyUtils.copy(inputStream, response.getOutputStream());
            	
            	
            }else{
            	String errorMessage = "Sorry!!! The file you are looking for does not exist";
                System.out.println(errorMessage);
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
                outputStream.close();
                return;
            }
            
        }else{
          String errorMessage = "Sorry!!! You don't have access!";
          System.out.println(errorMessage);
          OutputStream outputStream = response.getOutputStream();
          outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
          outputStream.close();
          return;
        }
        
         
//        if(type.equalsIgnoreCase("internal")){
//            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//            file = new File(classloader.getResource(INTERNAL_FILE).getFile());
//        }else{
//            file = new File(EXTERNAL_FILE_PATH);
//        }
//         
//        if(!file.exists()){
//            String errorMessage = "Sorry. The file you are looking for does not exist";
//            System.out.println(errorMessage);
//            OutputStream outputStream = response.getOutputStream();
//            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
//            outputStream.close();
//            return;
//        }
//         
        
    }
 
}