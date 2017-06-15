package cn.appinfodb.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.appinfodb.pojo.BackendUser;
import cn.appinfodb.pojo.Version;
import cn.appinfodb.service.BackendUser.BackendUserService;
import cn.appinfodb.service.Version.VersionService;



public class Test {
		
		public void show(){
			ApplicationContext context =new ClassPathXmlApplicationContext("SpringConfig.xml");
			BackendUserService backendUserService=(BackendUserService)context.getBean("backendUserService");
			BackendUser	backendUser=backendUserService.fountBackendUserBy("admin", "123456");
			System.out.println(backendUser);
		}
		@org.junit.Test
		public void info(){
			ApplicationContext context =new ClassPathXmlApplicationContext("SpringConfig.xml");
			VersionService versionService =(VersionService)context.getBean("versionService");
			List<Version> v= versionService.getVersionByInfoIdAndVersionid(38, 52);
			System.out.println(v.size());
			System.out.println(v.get(0).getAppName()+","+v.get(0).getModifyDate());
			
		}
		
		
		
}
