package com.test.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog4j {
	
	//将不同类型的日志输出到不同的文件中
	public static void toSingleFile(){
		org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("sendMsg");
		logger.debug("测试写入");
		logger.info("测试写入");
		logger.warn("sssss");
		Logger logger2 = LoggerFactory.getLogger("sendMsg");
		logger2.error("错误");
		
		Logger logger3 = LoggerFactory.getLogger(TestLog4j.class);
		logger3.debug("测试写入2");
	}
	
	public static void main(String[] args) {
		toSingleFile();
	}

}
