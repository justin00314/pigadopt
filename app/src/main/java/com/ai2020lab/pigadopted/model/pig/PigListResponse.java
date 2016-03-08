package com.ai2020lab.pigadopted.model.pig;

import com.ai2020lab.pigadopted.model.base.ResponseData;
import com.ai2020lab.pigadopted.model.user.UserInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 查询买家猪列表响应实体类
 * Created by Justin on 2016/3/8.
 * Email:502953057@qq.com,zhenghx3@asiainfo.com
 */
public class PigListResponse extends ResponseData<PigListResponse.PigListResult> {

	public class PigListResult {
		/**
		 * 买家信息
		 */
		@Expose
		@SerializedName("user_info")
		public UserInfo userInfo;
		/**
		 * 买家猪列表
		 */
		@Expose
		@SerializedName("pig_list")
		public List<PigDetailInfoForBuyer> pigInfos;
	}
}
