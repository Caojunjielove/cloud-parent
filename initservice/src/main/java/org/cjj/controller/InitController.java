package org.cjj.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cjj.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InitController {
	protected final Log log = LogFactory.getLog(this.getClass());
	@Autowired
	public QueryService queryService;
	@RequestMapping(value="/init")
	public String init(@RequestBody String name){
		log.info("接收信息：" + name);
		return queryService.query(name);
	}
}
