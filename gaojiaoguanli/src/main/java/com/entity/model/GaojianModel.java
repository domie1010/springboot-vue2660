package com.entity.model;

import com.entity.GaojianEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 稿件
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class GaojianModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 作者
     */
    private Integer zuozheId;


    /**
     * 专家
     */
    private Integer zhuanjiaId;


    /**
     * 稿件名字
     */
    private String gaojianName;


    /**
     * 稿件类型
     */
    private Integer gaojianTypes;


    /**
     * 稿件介绍
     */
    private String gaojianContent;


    /**
     * 稿件
     */
    private String gaojianFile;


    /**
     * 交稿时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 审稿结果
     */
    private Integer gaojianYesnoTypes;


    /**
     * 审核回复
     */
    private String gaojianShenheContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：作者
	 */
    public Integer getZuozheId() {
        return zuozheId;
    }


    /**
	 * 设置：作者
	 */
    public void setZuozheId(Integer zuozheId) {
        this.zuozheId = zuozheId;
    }
    /**
	 * 获取：专家
	 */
    public Integer getZhuanjiaId() {
        return zhuanjiaId;
    }


    /**
	 * 设置：专家
	 */
    public void setZhuanjiaId(Integer zhuanjiaId) {
        this.zhuanjiaId = zhuanjiaId;
    }
    /**
	 * 获取：稿件名字
	 */
    public String getGaojianName() {
        return gaojianName;
    }


    /**
	 * 设置：稿件名字
	 */
    public void setGaojianName(String gaojianName) {
        this.gaojianName = gaojianName;
    }
    /**
	 * 获取：稿件类型
	 */
    public Integer getGaojianTypes() {
        return gaojianTypes;
    }


    /**
	 * 设置：稿件类型
	 */
    public void setGaojianTypes(Integer gaojianTypes) {
        this.gaojianTypes = gaojianTypes;
    }
    /**
	 * 获取：稿件介绍
	 */
    public String getGaojianContent() {
        return gaojianContent;
    }


    /**
	 * 设置：稿件介绍
	 */
    public void setGaojianContent(String gaojianContent) {
        this.gaojianContent = gaojianContent;
    }
    /**
	 * 获取：稿件
	 */
    public String getGaojianFile() {
        return gaojianFile;
    }


    /**
	 * 设置：稿件
	 */
    public void setGaojianFile(String gaojianFile) {
        this.gaojianFile = gaojianFile;
    }
    /**
	 * 获取：交稿时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：交稿时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：审稿结果
	 */
    public Integer getGaojianYesnoTypes() {
        return gaojianYesnoTypes;
    }


    /**
	 * 设置：审稿结果
	 */
    public void setGaojianYesnoTypes(Integer gaojianYesnoTypes) {
        this.gaojianYesnoTypes = gaojianYesnoTypes;
    }
    /**
	 * 获取：审核回复
	 */
    public String getGaojianShenheContent() {
        return gaojianShenheContent;
    }


    /**
	 * 设置：审核回复
	 */
    public void setGaojianShenheContent(String gaojianShenheContent) {
        this.gaojianShenheContent = gaojianShenheContent;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
