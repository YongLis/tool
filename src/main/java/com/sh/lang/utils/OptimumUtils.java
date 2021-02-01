package com.sh.lang.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;

/**
 * 求最优路径 
 */
public class OptimumUtils {
	
	public static OptimumResultModel getOptimum(OptimumResultModel resultModel, List<OptimumModel> list, int index, BigDecimal target){
		
		OptimumModel optimumModel = list.get(index);
		resultModel.addModelList(optimumModel);
		resultModel.addModelValueSum(optimumModel.getNodeValue());
		if(index == list.size()-1){
			return resultModel;
		}
		
		if(target.compareTo(resultModel.getModelValueSum()) <= 0){
			return resultModel;
		}
		
		return getOptimum(resultModel, list, ++index, target);
		
		
	}
	

	public static void main(String[] args) {
		List<OptimumModel> list = new ArrayList<OptimumModel>();
		for(int i=0; i< 1000; i++){
			OptimumModel model = new OptimumModel();
			model.setNodeId(i+"");
			model.setNodeValue(new BigDecimal(i*5.55).setScale(2, BigDecimal.ROUND_HALF_DOWN));
			list.add(model);
		}
		
		
//		for(OptimumModel model : list){
//			System.out.println(model.getNodeId()+"|"+model.getNodeValue());
//		}
		
		
		System.out.println("----------------排序");
		List<OptimumResultModel> resultModels = new ArrayList<OptimumResultModel>();
		for(int i=0; i<list.size(); i++){
		    OptimumResultModel resultModel = new OptimumResultModel();
			resultModels.add(getOptimum(resultModel, list, i, new BigDecimal(1399)));
		}
		Collections.sort(resultModels);
		for(OptimumResultModel model : resultModels){
			System.out.println(new Gson().toJson(model.getModelList())+"|"+model.getModelValueSum());
		}

	}

}
