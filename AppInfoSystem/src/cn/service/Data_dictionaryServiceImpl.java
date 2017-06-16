package cn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.Data_dictionaryMapper;
import cn.pojo.data_dictionary;
@Service("data_dictionaryService")
public class Data_dictionaryServiceImpl implements Data_dictionaryService {
	@Autowired
	public Data_dictionaryMapper data_dictionarymapper;
	@Override
	public List<data_dictionary> getdictionary() {
		
		return data_dictionarymapper.getdata_dictionary();
	}
	@Override
	public List<data_dictionary> getdata_dictionarys() {
		
		return data_dictionarymapper.getdata_dictionarys();
	}
	
}	
