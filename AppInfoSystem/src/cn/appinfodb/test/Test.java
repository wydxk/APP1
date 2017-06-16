package cn.appinfodb.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.appinfodb.pojo.BackendUser;
import cn.appinfodb.pojo.Info;
import cn.appinfodb.service.BackendUser.BackendUserService;
import cn.appinfodb.service.Info.InfoService;



public class Test {
		@org.junit.Test
		public void show(){
			ApplicationContext context =new ClassPathXmlApplicationContext("SpringConfig.xml");
			InfoService infoService=(InfoService)context.getBean("infoService");
			Info info=infoService.getInfById(49);
			System.out.println(info.getAPKName());
		}
}
