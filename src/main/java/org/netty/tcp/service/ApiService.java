package org.netty.tcp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: meng
 * @Description: API服务类
 * @Date: 2022/5/8 15:07
 * @Version: 1.0
 */
@Slf4j
@Service
public class ApiService {

	public String callApi(String data){
		log.info("data:{}", data);
		return "服务端已收到信息";
	}
}
