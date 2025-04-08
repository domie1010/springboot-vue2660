package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 稿件
 *
 * @author 
 * @email
 */
@TableName("gaojian")
public class GaojianEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public GaojianEntity() {

	}

	public GaojianEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 作者
     */
    @TableField(value = "zuozhe_id")

    private Integer zuozheId;


    /**
     * 专家
     */
    @TableField(value = "zhuanjia_id")

    private Integer zhuanjiaId;


    /**
     * 稿件名字
     */
    @TableField(value = "gaojian_name")

    private String gaojianName;


    /**
     * 稿件类型
     */
    @TableField(value = "gaojian_types")

    private Integer gaojianTypes;


    /**
     * 稿件介绍
     */
    @TableField(value = "gaojian_content")

    private String gaojianContent;


    /**
     * 稿件
     */
    @TableField(value = "gaojian_file")

    private String gaojianFile;


    /**
     * 交稿时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 审稿结果
     */
    @TableField(value = "gaojian_yesno_types")

    private Integer gaojianYesnoTypes;


    /**
     * 审核回复
     */
    @TableField(value = "gaojian_shenhe_content")

    private String gaojianShenheContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：作者
	 */
    public Integer getZuozheId() {
        return zuozheId;
    }


    /**
	 * 获取：作者
	 */

    public void setZuozheId(Integer zuozheId) {
        this.zuozheId = zuozheId;
    }
    /**
	 * 设置：专家
	 */
    public Integer getZhuanjiaId() {
        return zhuanjiaId;
    }


    /**
	 * 获取：专家
	 */

    public void setZhuanjiaId(Integer zhuanjiaId) {
        this.zhuanjiaId = zhuanjiaId;
    }
    /**
	 * 设置：稿件名字
	 */
    public String getGaojianName() {
        return gaojianName;
    }


    /**
	 * 获取：稿件名字
	 */

    public void setGaojianName(String gaojianName) {
        this.gaojianName = gaojianName;
    }
    /**
	 * 设置：稿件类型
	 */
    public Integer getGaojianTypes() {
        return gaojianTypes;
    }


    /**
	 * 获取：稿件类型
	 */

    public void setGaojianTypes(Integer gaojianTypes) {
        this.gaojianTypes = gaojianTypes;
    }
    /**
	 * 设置：稿件介绍
	 */
    public String getGaojianContent() {
        return gaojianContent;
    }


    /**
	 * 获取：稿件介绍
	 */

    public void setGaojianContent(String gaojianContent) {
        this.gaojianContent = gaojianContent;
    }
    /**
	 * 设置：稿件
	 */
    public String getGaojianFile() {
        return gaojianFile;
    }


    /**
	 * 获取：稿件
	 */

    public void setGaojianFile(String gaojianFile) {
        this.gaojianFile = gaojianFile;
    }
    /**
	 * 设置：交稿时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：交稿时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：审稿结果
	 */
    public Integer getGaojianYesnoTypes() {
        return gaojianYesnoTypes;
    }


    /**
	 * 获取：审稿结果
	 */

    public void setGaojianYesnoTypes(Integer gaojianYesnoTypes) {
        this.gaojianYesnoTypes = gaojianYesnoTypes;
    }
    /**
	 * 设置：审核回复
	 */
    public String getGaojianShenheContent() {
        return gaojianShenheContent;
    }


    /**
	 * 获取：审核回复
	 */

    public void setGaojianShenheContent(String gaojianShenheContent) {
        this.gaojianShenheContent = gaojianShenheContent;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Gaojian{" +
            "id=" + id +
            ", zuozheId=" + zuozheId +
            ", zhuanjiaId=" + zhuanjiaId +
            ", gaojianName=" + gaojianName +
            ", gaojianTypes=" + gaojianTypes +
            ", gaojianContent=" + gaojianContent +
            ", gaojianFile=" + gaojianFile +
            ", insertTime=" + insertTime +
            ", gaojianYesnoTypes=" + gaojianYesnoTypes +
            ", gaojianShenheContent=" + gaojianShenheContent +
            ", createTime=" + createTime +
        "}";
    }
}
