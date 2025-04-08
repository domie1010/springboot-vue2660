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
 * 专家
 *
 * @author 
 * @email
 */
@TableName("zhuanjia")
public class ZhuanjiaEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ZhuanjiaEntity() {

	}

	public ZhuanjiaEntity(T t) {
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
     * 账户
     */
    @TableField(value = "username")

    private String username;


    /**
     * 密码
     */
    @TableField(value = "password")

    private String password;


    /**
     * 专家姓名
     */
    @TableField(value = "zhuanjia_name")

    private String zhuanjiaName;


    /**
     * 专家手机号
     */
    @TableField(value = "zhuanjia_phone")

    private String zhuanjiaPhone;


    /**
     * 专家身份证号
     */
    @TableField(value = "zhuanjia_id_number")

    private String zhuanjiaIdNumber;


    /**
     * 专家照片
     */
    @TableField(value = "zhuanjia_photo")

    private String zhuanjiaPhoto;


    /**
     * 性别
     */
    @TableField(value = "sex_types")

    private Integer sexTypes;


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
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：专家姓名
	 */
    public String getZhuanjiaName() {
        return zhuanjiaName;
    }


    /**
	 * 获取：专家姓名
	 */

    public void setZhuanjiaName(String zhuanjiaName) {
        this.zhuanjiaName = zhuanjiaName;
    }
    /**
	 * 设置：专家手机号
	 */
    public String getZhuanjiaPhone() {
        return zhuanjiaPhone;
    }


    /**
	 * 获取：专家手机号
	 */

    public void setZhuanjiaPhone(String zhuanjiaPhone) {
        this.zhuanjiaPhone = zhuanjiaPhone;
    }
    /**
	 * 设置：专家身份证号
	 */
    public String getZhuanjiaIdNumber() {
        return zhuanjiaIdNumber;
    }


    /**
	 * 获取：专家身份证号
	 */

    public void setZhuanjiaIdNumber(String zhuanjiaIdNumber) {
        this.zhuanjiaIdNumber = zhuanjiaIdNumber;
    }
    /**
	 * 设置：专家照片
	 */
    public String getZhuanjiaPhoto() {
        return zhuanjiaPhoto;
    }


    /**
	 * 获取：专家照片
	 */

    public void setZhuanjiaPhoto(String zhuanjiaPhoto) {
        this.zhuanjiaPhoto = zhuanjiaPhoto;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
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
        return "Zhuanjia{" +
            "id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", zhuanjiaName=" + zhuanjiaName +
            ", zhuanjiaPhone=" + zhuanjiaPhone +
            ", zhuanjiaIdNumber=" + zhuanjiaIdNumber +
            ", zhuanjiaPhoto=" + zhuanjiaPhoto +
            ", sexTypes=" + sexTypes +
            ", createTime=" + createTime +
        "}";
    }
}
