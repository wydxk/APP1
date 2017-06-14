package cn.appinfodb.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.appinfodb.pojo.BackendUser;
import cn.appinfodb.service.BackendUser.BackendUserService;



public class Test {
		@org.junit.Test
		public void show(){
			ApplicationContext context =new ClassPathXmlApplicationContext("SpringConfig.xml");
			BackendUserService backendUserService=(BackendUserService)context.getBean("backendUserService");
			BackendUser	backendUser=backendUserService.fountBackendUserBy("admin", "123456");
			System.out.println(backendUser);
		}
}
