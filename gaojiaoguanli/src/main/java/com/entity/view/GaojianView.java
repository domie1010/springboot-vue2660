package com.entity.view;

import com.entity.GaojianEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 稿件
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("gaojian")
public class GaojianView extends GaojianEntity implements Serializable {
    private static final long serialVersionUID = 1L;
		/**
		* 稿件类型的值
		*/
		private String gaojianValue;
		/**
		* 审稿结果的值
		*/
		private String gaojianYesnoValue;



		//级联表 zhuanjia
			/**
			* 专家姓名
			*/
			private String zhuanjiaName;
			/**
			* 专家身份证号
			*/
			private String zhuanjiaIdNumber;
			/**
			* 专家照片
			*/
			private String zhuanjiaPhoto;

		//级联表 zuozhe
			/**
			* 作者姓名
			*/
			private String zuozheName;
			/**
			* 作者身份证号
			*/
			private String zuozheIdNumber;
			/**
			* 现住地址
			*/
			private String zuozheAddress;
			@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
			@DateTimeFormat
			/**
			* 出生年月
			*/
			private Date zuozheTime;
			/**
			* 作者照片
			*/
			private String zuozhePhoto;

	public GaojianView() {

	}

	public GaojianView(GaojianEntity gaojianEntity) {
		try {
			BeanUtils.copyProperties(this, gaojianEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 稿件类型的值
			*/
			public String getGaojianValue() {
				return gaojianValue;
			}
			/**
			* 设置： 稿件类型的值
			*/
			public void setGaojianValue(String gaojianValue) {
				this.gaojianValue = gaojianValue;
			}
			/**
			* 获取： 审稿结果的值
			*/
			public String getGaojianYesnoValue() {
				return gaojianYesnoValue;
			}
			/**
			* 设置： 审稿结果的值
			*/
			public void setGaojianYesnoValue(String gaojianYesnoValue) {
				this.gaojianYesnoValue = gaojianYesnoValue;
			}

















				//级联表的get和set zhuanjia
					/**
					* 获取： 专家姓名
					*/
					public String getZhuanjiaName() {
						return zhuanjiaName;
					}
					/**
					* 设置： 专家姓名
					*/
					public void setZhuanjiaName(String zhuanjiaName) {
						this.zhuanjiaName = zhuanjiaName;
					}
					/**
					* 获取： 专家身份证号
					*/
					public String getZhuanjiaIdNumber() {
						return zhuanjiaIdNumber;
					}
					/**
					* 设置： 专家身份证号
					*/
					public void setZhuanjiaIdNumber(String zhuanjiaIdNumber) {
						this.zhuanjiaIdNumber = zhuanjiaIdNumber;
					}
					/**
					* 获取： 专家照片
					*/
					public String getZhuanjiaPhoto() {
						return zhuanjiaPhoto;
					}
					/**
					* 设置： 专家照片
					*/
					public void setZhuanjiaPhoto(String zhuanjiaPhoto) {
						this.zhuanjiaPhoto = zhuanjiaPhoto;
					}


				//级联表的get和set zuozhe
					/**
					* 获取： 作者姓名
					*/
					public String getZuozheName() {
						return zuozheName;
					}
					/**
					* 设置： 作者姓名
					*/
					public void setZuozheName(String zuozheName) {
						this.zuozheName = zuozheName;
					}
					/**
					* 获取： 作者身份证号
					*/
					public String getZuozheIdNumber() {
						return zuozheIdNumber;
					}
					/**
					* 设置： 作者身份证号
					*/
					public void setZuozheIdNumber(String zuozheIdNumber) {
						this.zuozheIdNumber = zuozheIdNumber;
					}
					/**
					* 获取： 现住地址
					*/
					public String getZuozheAddress() {
						return zuozheAddress;
					}
					/**
					* 设置： 现住地址
					*/
					public void setZuozheAddress(String zuozheAddress) {
						this.zuozheAddress = zuozheAddress;
					}
					/**
					* 获取： 出生年月
					*/
					public Date getZuozheTime() {
						return zuozheTime;
					}
					/**
					* 设置： 出生年月
					*/
					public void setZuozheTime(Date zuozheTime) {
						this.zuozheTime = zuozheTime;
					}
					/**
					* 获取： 作者照片
					*/
					public String getZuozhePhoto() {
						return zuozhePhoto;
					}
					/**
					* 设置： 作者照片
					*/
					public void setZuozhePhoto(String zuozhePhoto) {
						this.zuozhePhoto = zuozhePhoto;
					}




}
