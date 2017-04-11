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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.omcbappeda.sumsel.constant.Constant;
import com.omcbappeda.sumsel.model.ActivityLogVO;
import com.omcbappeda.sumsel.service.GlobalService;

@Controller
public class PengaturanActLogController extends MainController {
	private GlobalService globalService = (GlobalService) context.getBean("globalService");
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getDataTableActivityLogByPaging", method = RequestMethod.GET)
	private @ResponseBody void prepareDataTableActivityLogByPaging(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String limit = request.getParameter("perPage");
		String page = request.getParameter("page");
		int offset = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);
		double floor = Math.floor((double)offset/(double)Integer.valueOf(limit));
		int pageIndex = (int) floor + 1;
		
		JSONObject dataTable = new JSONObject();
		
		if(checkSession(request)){
			List<ActivityLogVO> activityLogVOList = new ArrayList<ActivityLogVO>();
			String fromDate = request.getParameter("fromDate").equals("") ? "0000-00-00 00:00:00" : request.getParameter("fromDate");
			String toDate = request.getParameter("toDate").equals("") ? "0000-00-00 00:00:00" : request.getParameter("toDate");
			String isAdmin = request.getParameter("isAdmin");
			String id = request.getParameter("id");
			int totalItem = 0;
			if(isAdmin != null){
				activityLogVOList = globalService.getActLogByDate(fromDate, toDate, id, Integer.valueOf(limit), offset);
				totalItem = globalService.countActLogByDate(fromDate, toDate, id);				
			} else {
				activityLogVOList = globalService.getAllActLogByUser(id, fromDate, toDate, Integer.valueOf(limit), offset);
				totalItem = globalService.countActLogByUser(id, fromDate, toDate);
			}
			dataTable.put("msg","200");
			dataTable.put("perPage", String.valueOf(limit));
			dataTable.put("startIndex", String.valueOf(offset));
			dataTable.put("pageIndex", String.valueOf(pageIndex));
			DateFormat df = new SimpleDateFormat(Constant.DATEFORMAT.toString());
			
			JSONArray jsonArray = new JSONArray();
			for (ActivityLogVO activityLogVO : activityLogVOList) {
				JSONObject data = new JSONObject();
				data.put("Id", activityLogVO.getId());
				if(isAdmin != null){
					data.put("name", activityLogVO.getCreatedBy().getUserId());
				}
				data.put("Ip", activityLogVO.getIpAddress());
				data.put("activity", activityLogVO.getActivity());
				data.put("userAgent", activityLogVO.getUserAgent());
				data.put("CreatedDate", df.format(activityLogVO.getCreatedDate()));
				
				jsonArray.add(data);
			}		
			
			dataTable.put("items",jsonArray);
			dataTable.put("totalItems",  String.valueOf(totalItem));
			dataTable.put("start", totalItem > 0 ? String.valueOf(offset) : "0");
			
			double totalPage = Math.ceil((double)totalItem/(double)Integer.valueOf(limit));
			dataTable.put("totalPage", String.valueOf((int)totalPage));
			
			dataTable.put("currentItemCount",  String.valueOf(totalItem));
		}else{
			dataTable.put("msg","201");
			dataTable.put("access","denied");
		}
		
		
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		response.getWriter().print(dataTable.toJSONString());
	}
}
