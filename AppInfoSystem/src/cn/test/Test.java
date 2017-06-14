package cn.test;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.appinfodb.pojo.DataDictionary;
import cn.appinfodb.service.DataDictionary.DataDictionaryService;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("SpringConfig.xml");
		DataDictionaryService dataDictionaryService =(DataDictionaryService )context.getBean("dataDictionaryService");
		
		List<DataDictionary> ds= dataDictionaryService.getStatuss();
		
		for (Iterator iterator = ds.iterator(); iterator.hasNext();) {
			DataDictionary dataDictionary = (DataDictionary) iterator.next();
			System.out.println(dataDictionary.getValueName());
		}
		
	}
	
	
	
}
