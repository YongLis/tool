package com.sh.lang.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OptimumResultModel implements Serializable, Comparable<OptimumResultModel>{
	private List<OptimumModel> modelList = new ArrayList<OptimumModel>();
	private BigDecimal modelValueSum = BigDecimal.ZERO;
	

	@Override
	public int compareTo(OptimumResultModel o) {
		int tmp =  this.getModelValueSum().compareTo(o.modelValueSum);
		if(tmp !=0 ){
			return tmp;
		}
		else{
			if(o.getModelList().size() >= this.getModelList().size()){   // 最少节点组合排序
				return -1;
			} 
			else {
				return 1;
			}
		}
		
	}

	public List<OptimumModel> getModelList() {
		return modelList;
	}

	public void addModelList(OptimumModel model) {
		this.modelList.add(model);
	}

	public BigDecimal getModelValueSum() {
		return modelValueSum;
	}

	public void addModelValueSum(BigDecimal modelValueSum) {
		this.modelValueSum = this.modelValueSum.add(modelValueSum);
	}

}
