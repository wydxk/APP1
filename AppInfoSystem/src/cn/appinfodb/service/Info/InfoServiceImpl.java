package cn.appinfodb.service.Info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfodb.dao.Info.InfoMapper;
import cn.appinfodb.pojo.Info;

@Service("infoService")
public class InfoServiceImpl implements InfoService {
	@Autowired
	InfoMapper infoMapper;

	public void setInfoMapper(InfoMapper infoMapper) {
		this.infoMapper = infoMapper;
	}

	@Override
	/**
	 * 根据条件查询信息并分页,管理
	 */
	public List<Info> selectInfo(String softwareName, int flatformId,
			int categoryLevel1, int categoryLevel2, int categoryLevel3,
			int cnindex, int pageCount) {

		return infoMapper.selectInfo(softwareName, flatformId, categoryLevel1,
				categoryLevel2, categoryLevel3, (cnindex - 1) * pageCount,
				pageCount);
	}

	@Override
	public int selectInfoCount(String softwareName, int flatformId,
			int categoryLevel1, int categoryLevel2, int categoryLevel3) {

		return infoMapper.selectInfoCount(softwareName, flatformId,
				categoryLevel1, categoryLevel2, categoryLevel3);
	}

	@Override
	public List<Info> selectFeClass() {

		return infoMapper.selectFeClass();

	}

	/**
	 * 根据条件查询信息并分页，开发
	 * 
	 * @param softwareName
	 * @param flatformId
	 * @param status
	 * @param categoryLevel1
	 * @param categoryLevel2
	 * @param categoryLevel3
	 * @return
	 */
	public List<Info> getInfos(String softwareName, int flatformId, int status,
			int categoryLevel1, int categoryLevel2, int categoryLevel3,
			int cnindex, int pageCount) {

		return infoMapper.getInfo(softwareName, flatformId, status,
				categoryLevel1, categoryLevel2, categoryLevel3, (cnindex - 1)
						* pageCount, pageCount);
	}

	@Override
	public int getInfoCount(String softwareName, int flatformId, int status,
			int categoryLevel1, int categoryLevel2, int categoryLevel3) {
		// TODO Auto-generated method stub
		return infoMapper.getInfoCount(softwareName, flatformId, status, categoryLevel1, categoryLevel2, categoryLevel3);
	}

	@Override
	public Info getInfById(int id) {
		// TODO Auto-generated method stub
		return infoMapper.getInfoById(id);
	}

	@Override
	public boolean delApp(int id) {
		int row= infoMapper.deleteApp(id);
		if(row==1){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean changeVersionId(Info info) {
		int row =infoMapper.changeVersionId(info);
		if(row ==1){
			return true;
		}else{
			return false;
		}
	}
	

}
