package com.ai2020lab.pigadopted.model.statistic;

import com.ai2020lab.pigadopted.model.base.ResponseData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rocky on 16/3/18.
 */
public class WeightStaticResponse extends ResponseData<WeightStaticResponse.DataList> {

    public class DataList {
        @Expose
        @SerializedName("data_list")
        public List<WeightData> dataList;
    }
}
