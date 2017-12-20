package org.cjj.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class QueryController {
	protected final Log log = LogFactory.getLog(this.getClass());
	@RequestMapping(value="/query")
	public String init(@RequestBody String name){
		log.info("接收信息：" + name);
		return "hello " + name;
	}
}
