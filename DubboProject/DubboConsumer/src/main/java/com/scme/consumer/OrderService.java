package com.scme.consumer;

import com.scme.Interface.BlogService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class OrderService {
    public static void addOrder(){
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("consumer.xml");
		applicationContext.start();
		System.out.println("###order服務,开始调用会员服务");
		BlogService userService=(BlogService) applicationContext.getBean("blogService");
		String userName = userService.dubbotest();
		System.out.println("###order服務,结束调用会员服务,userName:" + userName);
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		addOrder();
	}
}
