package com.ai2020lab.pigadopted.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ai2020lab.pigadopted.R;
import com.ai2020lab.pigadopted.base.AIBaseActivity;
import com.ai2020lab.pigadopted.common.IntentExtra;
import com.ai2020lab.pigadopted.model.hogpen.HogpenInfo;
import com.ai2020lab.pigadopted.model.pig.GrowthInfo;
import com.ai2020lab.pigadopted.model.pig.HealthInfo;
import com.ai2020lab.pigadopted.model.pig.PigDetailInfoForBuyer;
import com.ai2020lab.pigadopted.model.pig.PigInfo;
import com.ai2020lab.pigadopted.model.pig.PigStatus;
import com.ai2020lab.pigadopted.model.user.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 买家主页
 * Created by Justin on 2016/3/4.
 * Email:502953057@qq.com,zhenghx3@asiainfo.com
 */
public class BuyerMainActivity extends AIBaseActivity {
	/**
	 * 日志标题
	 */
	private final static String TAG = BuyerMainActivity.class.getSimpleName();
	/**
	 * 买家用户信息
	 */
	private UserInfo userInfo;
	/**
	 * 买家猪列表RecyclerView
	 */
	private RecyclerView buyerPigListRv;

	private BuyerPigListRvAdapter buyerPigListRvAdapter;

	private List<PigDetailInfoForBuyer> pigDetailInfos = new ArrayList<>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		userInfo = (UserInfo) getIntent().getExtras().get(IntentExtra.USER_INFO);
		setContentView(R.layout.activity_main_buyer);
		setToolbar();
		assignViews();
		setBuyerPigListRv();
		loadData();
	}

	private void setToolbar() {
		supportToolbar(true);
		setToolbarTitle(String.format(getString(R.string.activity_title_buyer_main), userInfo.userName));
	}


	private void assignViews() {
		buyerPigListRv = (RecyclerView) findViewById(R.id.buyer_pig_list_rv);
	}

	/**
	 * 设置买家猪列表RecyclerView
	 */
	private void setBuyerPigListRv() {
		buyerPigListRvAdapter = new BuyerPigListRvAdapter(getActivity());
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		buyerPigListRv.setLayoutManager(layoutManager);
		buyerPigListRv.setAdapter(buyerPigListRvAdapter);
	}


	private void loadData() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				loadTestListData();

			}
		}, 1000);

	}

	// TODO:加载买家猪列表测试数据
	private void loadTestListData() {
		pigDetailInfos.clear();
		PigDetailInfoForBuyer pigDetailForBuyer;

		pigDetailForBuyer = new PigDetailInfoForBuyer();
		pigDetailForBuyer.pigInfo = new PigInfo();
		// 初始化卖家名字
		pigDetailForBuyer.pigInfo.hogpenInfo = new HogpenInfo();
		pigDetailForBuyer.pigInfo.hogpenInfo.userInfo = new UserInfo();
		pigDetailForBuyer.pigInfo.hogpenInfo.userInfo.userName = "刘司机";
		// 初始化入栏时间，入栏体重，入栏年龄
		pigDetailForBuyer.pigInfo.attendedWeight = 40;
		pigDetailForBuyer.pigInfo.attendedAge = 5.34f;
		pigDetailForBuyer.pigInfo.attendedTime = 1445508052553l;
		// 初始化猪状态
		pigDetailForBuyer.healthInfo = new HealthInfo();
		pigDetailForBuyer.healthInfo.status = PigStatus.EATING;
		// 初始化猪成长历程
		pigDetailForBuyer.growthInfo = new GrowthInfo();
		pigDetailForBuyer.growthInfo.pigWeight = 140;
		pigDetailInfos.add(pigDetailForBuyer);
		///////////////////////////////////////////////////////////
		pigDetailForBuyer = new PigDetailInfoForBuyer();
		pigDetailForBuyer.pigInfo = new PigInfo();
		// 初始化卖家名字
		pigDetailForBuyer.pigInfo.hogpenInfo = new HogpenInfo();
		pigDetailForBuyer.pigInfo.hogpenInfo.userInfo = new UserInfo();
		pigDetailForBuyer.pigInfo.hogpenInfo.userInfo.userName = "乔帮主";
		// 初始化入栏时间，入栏体重，入栏年龄
		pigDetailForBuyer.pigInfo.attendedWeight = 80;
		pigDetailForBuyer.pigInfo.attendedAge = 8.25f;
		pigDetailForBuyer.pigInfo.attendedTime = 1446372052553l;
		// 初始化猪状态
		pigDetailForBuyer.healthInfo = new HealthInfo();
		pigDetailForBuyer.healthInfo.status = PigStatus.SLEEPING;
		// 初始化猪成长历程
		pigDetailForBuyer.growthInfo = new GrowthInfo();
		pigDetailForBuyer.growthInfo.pigWeight = 100;
		pigDetailInfos.add(pigDetailForBuyer);
		///////////////////////////////////////////////////////////
		pigDetailForBuyer = new PigDetailInfoForBuyer();
		pigDetailForBuyer.pigInfo = new PigInfo();
		// 初始化卖家名字
		pigDetailForBuyer.pigInfo.hogpenInfo = new HogpenInfo();
		pigDetailForBuyer.pigInfo.hogpenInfo.userInfo = new UserInfo();
		pigDetailForBuyer.pigInfo.hogpenInfo.userInfo.userName = "小马哥";
		// 初始化入栏时间，入栏体重，入栏年龄
		pigDetailForBuyer.pigInfo.attendedWeight = 100;
		pigDetailForBuyer.pigInfo.attendedAge = 9.35f;
		pigDetailForBuyer.pigInfo.attendedTime = 1451642452553l;
		// 初始化猪状态
		pigDetailForBuyer.healthInfo = new HealthInfo();
		pigDetailForBuyer.healthInfo.status = PigStatus.WALKING;
		// 初始化猪成长历程
		pigDetailForBuyer.growthInfo = new GrowthInfo();
		pigDetailForBuyer.growthInfo.pigWeight = 110;
		pigDetailInfos.add(pigDetailForBuyer);
		// 刷新列表
		buyerPigListRvAdapter.addAll(pigDetailInfos);
	}


}
