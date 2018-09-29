package cn.itcast.cloud.dao;

import java.util.List;

import cn.itcast.cloud.domain.LocationInfo;

public interface LocationInfoDao {
	
	
	List<LocationInfo> findAll();
}
