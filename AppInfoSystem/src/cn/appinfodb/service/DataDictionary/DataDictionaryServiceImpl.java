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
	public List<DataDictionary> selectData() {
		
		return dataDictionaryMapper.selectData();

	
	
	}
	public void setDataDictionaryMapper(DataDictionaryMapper dataDictionaryMapper) {
		this.dataDictionaryMapper = dataDictionaryMapper;
	}

	@Override
	public List<DataDictionary> getStatuss() {
		return dataDictionaryMapper.getStatus();
	}


	@Override
	public List<DataDictionary> getflatFoList() {
		// TODO Auto-generated method stub
		return dataDictionaryMapper.getflatFormList();
	}

}
