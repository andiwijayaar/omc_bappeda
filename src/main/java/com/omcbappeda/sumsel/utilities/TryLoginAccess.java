package com.omcbappeda.sumsel.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.omcbappeda.sumsel.form.LoginForm;

public class TryLoginAccess {
	public void write(HttpServletRequest request, LoginForm form){
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		File mdir = new File("/public/access/");
		if(!mdir.exists())
			mdir.mkdirs();

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
			SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
			String date = sdf.format(new Date());
			File file = new File("/public/access/", date+".txt");
			
			String ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
				   ipAddress = request.getRemoteAddr();
			}
			
			String content = time.format(new Date()) + "  >> IP=" + ipAddress + " , USER="+ form.getUserId()+" , PASSWORD="+form.getPassword()+" , AGENT="+request.getHeader("user-agent")+" ::\n";

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(content);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}

}
