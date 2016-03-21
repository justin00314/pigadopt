/*
 * Copyright (c) 2016. Justin Z All rights Reserved
 */

package com.ai2020lab.pigadopted.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ai2020lab.aiutils.common.LogUtils;
import com.ai2020lab.aiutils.common.ViewUtils;
import com.ai2020lab.aiutils.system.DisplayUtils;
import com.ai2020lab.aiviews.dialog.BaseDialog;
import com.ai2020lab.aiviews.wheelview.WheelView;
import com.ai2020lab.pigadopted.R;
import com.ai2020lab.pigadopted.common.DataManager;
import com.ai2020lab.pigadopted.model.pig.PigCategory;
import com.ai2020lab.pigadopted.model.pig.PigInfo;
import com.ai2020lab.pigadopted.view.pickerview.DatePickerView;
import com.ai2020lab.pigadopted.view.pickerview.PigAgePickerView;
import com.ai2020lab.pigadopted.view.pickerview.PigWeightPickerView;
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;

import java.util.ArrayList;

/**
 * 添加猪对话框
 * Created by Justin Z on 2016/3/18.
 * 502953057@qq.com
 */
public class AddPigDialog extends DialogFragment {
	private final static String TAG = AddPigDialog.class.getSimpleName();

	private OnClickDialogBtnListener<PigInfo> onClickDialogBtnListener;

	private boolean loadAnim = false;

	private ImageView addPigTitleIv;
	private TextView pigAddCategoryTv;
	private TextView pigAddAgeTv;
	private TextView pigAddWeightTv;
	private TextView pigAddTimeTv;
	private Button cancelBtn;
	private Button ensureBtn;
	private WheelView pigAddCategoryWv;
	private PigAgePickerView pigAddAgePv;
	private PigWeightPickerView pigAddWeightPv;
	private ArrayWheelAdapter<PigCategory> pigCategoryWvAdapter;
	private DatePickerView pigAddDatePv;

	/**
	 * 创建对话框方法
	 *
	 * @param loadAnim                 是否加载动画
	 * @param onClickDialogBtnListener 点击按钮事件监听
	 * @return AddPigDialog
	 */
	public static AddPigDialog newInstance(boolean loadAnim,
	                                       OnClickDialogBtnListener onClickDialogBtnListener) {
		AddPigDialog addPigFragment = new AddPigDialog();
		addPigFragment.onClickDialogBtnListener = onClickDialogBtnListener;
		addPigFragment.loadAnim = loadAnim;
		return addPigFragment;
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		long startTime = System.currentTimeMillis();
		View contentView = ViewUtils.makeView(getActivity(), R.layout.dialog_add_pig);
		BaseDialog dialog = createDialog(contentView);
		assignViews(contentView);
		setTextFonts();
		setPigAddCategoryWv();
		setPigAddDatePv();
		setDialogBtnClickListener(dialog);
		long endTime = System.currentTimeMillis();
		LogUtils.i(TAG, "消耗时间：" + (endTime - startTime) / 1000 + "秒");

//		if (loadAnim) {
//			setVisible();
//			loadAnimation();
//		} else {
//			setVisible();
//		}
		return dialog;
	}

	/**
	 * 创建Dialog
	 */
	private BaseDialog createDialog(View contentView) {
		BaseDialog.Builder builder = new BaseDialog.Builder(getActivity(), contentView);
		builder.setWidth(DisplayUtils.getScreenWidth(getActivity()));
		builder.setHeight(DisplayUtils.getScreenHeight(getActivity()));
		builder.setGravity(Gravity.CENTER);
		builder.setStyle(R.style.BaseAlertDialog);
		builder.setAnimStyle(R.style.DialogWindowAnimation_Scale);
		BaseDialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(true);
		return dialog;
	}

	/**
	 * 分配各个View
	 */
	private void assignViews(View contentView) {
		addPigTitleIv = (ImageView) contentView.findViewById(R.id.add_pig_title_iv);
		pigAddCategoryTv = (TextView) contentView.findViewById(R.id.pig_add_category_tv);
		pigAddAgeTv = (TextView) contentView.findViewById(R.id.pig_add_age_tv);
		pigAddWeightTv = (TextView) contentView.findViewById(R.id.pig_add_weight_tv);
		pigAddCategoryWv = (WheelView) contentView.findViewById(R.id.pig_add_category_wv);
		pigAddAgePv = (PigAgePickerView) contentView.findViewById(R.id.pig_add_age_pv);
		pigAddWeightPv = (PigWeightPickerView) contentView.findViewById(R.id.pig_add_weight_pv);
		pigAddTimeTv = (TextView) contentView.findViewById(R.id.pig_add_time_tv);
		pigAddDatePv = (DatePickerView) contentView.findViewById(R.id.pig_add_date_pv);
		cancelBtn = (Button) contentView.findViewById(R.id.cancel_btn);
		ensureBtn = (Button) contentView.findViewById(R.id.ensure_btn);
	}

	/**
	 * 分配View
	 */
	private void setTextFonts() {
		// 标题和按钮文字全部使用中文粗体
		pigAddCategoryTv.getPaint().setFakeBoldText(true);
		pigAddAgeTv.getPaint().setFakeBoldText(true);
		pigAddWeightTv.getPaint().setFakeBoldText(true);
		pigAddTimeTv.getPaint().setFakeBoldText(true);
		cancelBtn.getPaint().setFakeBoldText(true);
		ensureBtn.getPaint().setFakeBoldText(true);
	}

	/**
	 * 设置猪品种选择滚轮
	 */
	private void setPigAddCategoryWv() {
		ArrayList<PigCategory> pigCategories = DataManager.getInstance().getAllPigCategories();
		pigCategoryWvAdapter = new ArrayWheelAdapter<>(pigCategories, 2);
		pigAddCategoryWv.setAdapter(pigCategoryWvAdapter);
		// 设置选中第一项
		pigAddCategoryWv.setCurrentItem(0);
//		pigAddCategoryWv.setCyclic(false);
	}

	private void setPigAddDatePv() {
		pigAddDatePv.setPickerView(2016, 1, 1);
	}

	private PigInfo getSelectPigInfo() {
		PigInfo pigInfo = new PigInfo();
		// 猪品种
		pigInfo.pigCategory = (PigCategory) pigCategoryWvAdapter
				.getItem(pigAddCategoryWv.getCurrentItem());
		// 入栏猪龄
		pigInfo.attendedAge = pigAddAgePv.getSelectPigAge();
		// 入栏体重
		pigInfo.attendedWeight = pigAddWeightPv.getSelectPigWeight();
		// 入栏时间
		pigInfo.attendedDate = pigAddDatePv.getSelectTime();
		LogUtils.i(TAG, "入栏品种:" + pigInfo.pigCategory.toString());
		LogUtils.i(TAG, "入栏猪龄:" + pigInfo.attendedAge);
		LogUtils.i(TAG, "入栏体重:" + pigInfo.attendedWeight);
		LogUtils.i(TAG, "入栏时间:" + pigInfo.attendedDate);
		return pigInfo;
	}

	/**
	 * 绑定按钮事件
	 */
	private void setDialogBtnClickListener(final BaseDialog dialog) {
		ensureBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getSelectPigInfo();

				if (onClickDialogBtnListener != null)
					onClickDialogBtnListener.onClickEnsure(dialog, getPigInfo());
			}
		});
		cancelBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (onClickDialogBtnListener != null)
					onClickDialogBtnListener.onClickCancel(dialog);

			}
		});
	}


	//TODO:返回猪基础信息测试数据,返回后将基础数据提交服务端
	private PigInfo getPigInfo() {
		PigInfo pigInfo = new PigInfo();
		pigInfo.attendedAge = 3;
		pigInfo.attendedDate = "2016-3-18";
		pigInfo.attendedWeight = 30;
		pigInfo.pigCategory = new PigCategory();
		pigInfo.pigCategory.categoryID = "2";
		pigInfo.pigCategory.categoryName = "内江猪";
		return pigInfo;
	}


	private ArrayList<String> getCategoryNames() {
		ArrayList<String> categoryNames = new ArrayList<>();
		categoryNames.add("长白猪");
		categoryNames.add("荣昌猪");
		categoryNames.add("内江猪");
		categoryNames.add("香猪");
		categoryNames.add("藏猪");
		return categoryNames;
	}


}