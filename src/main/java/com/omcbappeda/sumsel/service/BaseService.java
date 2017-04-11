package com.omcbappeda.sumsel.service;

import com.omcbappeda.sumsel.model.ActivityLogVO;

public abstract interface BaseService {
	ActivityLogVO prepareActivityLogVO(String userId, String ipAddress, String userAgent, String activity);
}
