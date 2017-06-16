package cn.appinfodb.dao.Info;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.appinfodb.pojo.Info;
public interface InfoMapper {
	/**
	 * 根据条件查询并分页，后台
	 * @param map
	 * @return
	 */
		public List<Info> selectInfo(@Param("softwareName")String softwareName,
														@Param("flatformId")int flatformId,
														@Param("categoryLevel1")int categoryLevel1,
														@Param("categoryLevel2")int categoryLevel2,
														@Param("categoryLevel3")int categoryLevel3,
														@Param("index")int index,
														@Param("pageCount")int pageCount);
		/**
		 * 根据条件查询记录数，后台
		 * @param softwareName
		 * @param flatformId
		 * @param categoryLevel1
		 * @param categoryLevel2
		 * @param categoryLevel3
		 * @return
		 */
		public int selectInfoCount(@Param("softwareName")String softwareName,
													 @Param("flatformId")int flatformId,
													 @Param("categoryLevel1")int categoryLevel1,
													 @Param("categoryLevel2")int categoryLevel2,
													 @Param("categoryLevel3")int categoryLevel3);

		/**
		 * 根据id和版本id（versionId）查询info，后台
		 * @param id
		 * @param versionId
		 * @return
		 */
		public Info selectInfosByid(@Param("id")int id,@Param("versionId")int versionId);
		/**
		 * 根据id修改info中的状态（审核.....），后台
		 * @param STATUS
		 * @param id
		 * @return
		 */
		public int changeInfo(@Param("STATUS")int STATUS,@Param("id")int id);	
		/**
		 * 添加info，开发
		 * @return
		 */
		public int addInfo(Info info);	
		/**
		 * 根据APK名称查询info信息，开发
		 * @param APKName
		 * @return
		 */
		public Info selectByAPK(@Param("APKName")String APKName);
		
		
		/**
		 * 根据条件查询并分页，开发
		 * @param softwareName
		 * @param flatformId
		 * @param status
		 * @param categoryLevel1
		 * @param categoryLevel2
		 * @param categoryLevel3
		 * @param index
		 * @param pageCount
		 * @return
		 */
		public List<Info>getInfo(@Param("softwareName")String softwareName ,
				@Param("flatformId")int flatformId ,
				@Param("status")int status ,
				@Param("categoryLevel1")int categoryLevel1 ,
				@Param("categoryLevel2")int categoryLevel2 ,
				@Param("categoryLevel3")int categoryLevel3 ,
				@Param("index")int index,
				@Param("pageCount")int pageCount);
		
		/**
		 * 根据条件查询信息列表数量，《开发》
		 * @param softwareName
		 * @param flatformId
		 * @param status
		 * @param categoryLevel1
		 * @param categoryLevel2
		 * @param categoryLevel3
		 * @return
		 */
		public int getInfoCount(@Param("softwareName")String softwareName,
				 @Param("flatformId")int flatformId,
				 @Param("status")int status ,
				 @Param("categoryLevel1")int categoryLevel1,
				 @Param("categoryLevel2")int categoryLevel2,
				 @Param("categoryLevel3")int categoryLevel3);

		/**
		 * 根据Id查询详情，开发
		 * @param id
		 * @return
		 */
		public Info  getInfoById(@Param("id") int id);
		
		public Info selectInfoByInfoId(@Param("id")int id);		
}
