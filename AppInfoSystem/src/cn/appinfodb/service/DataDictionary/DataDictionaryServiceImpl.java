package cn.appinfodb.service.DataDictionary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfodb.dao.DataDictionary.DataDictionaryMapper;
import cn.appinfodb.pojo.DataDictionary;

@Service("dataDictionaryService")
public class DataDictionaryServiceImpl implements DataDictionaryService {
	@Autowired
	DataDictionaryMapper dataDictionaryMapper;
	
	@Override
	public DataDictionary selectDataById(int id){	
		return dataDictionaryMapper.selectDataById(id);
	}
	@Override
	public List<DataDictionary> selectDataBytypeCode(String typeCode) {
		return dataDictionaryMapper.selectDataBytypeCode(typeCode);
	}
	@Override
	public List<DataDictionary> getflatFoList() {
		return dataDictionaryMapper.getflatFormList();
	}
	@Override
	public List<DataDictionary> getStatuss() {
		return dataDictionaryMapper.getStatus();
	}
}
