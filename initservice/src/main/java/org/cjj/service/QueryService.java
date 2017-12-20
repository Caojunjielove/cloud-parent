package org.cjj.service;

import org.cjj.config.FeignConfig;
import org.cjj.service.impl.QueryServiceImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value="queryservice", configuration = FeignConfig.class, fallback = QueryServiceImpl.class)
public interface QueryService {
	@RequestMapping(value="/query",method=RequestMethod.POST)
	String query(@RequestBody String name);
}
