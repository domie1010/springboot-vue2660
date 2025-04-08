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
 * 作者
 *
 * @author 
 * @email
 */
@TableName("zuozhe")
public class ZuozheEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ZuozheEntity() {

	}

	public ZuozheEntity(T t) {
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
     * 作者姓名
     */
    @TableField(value = "zuozhe_name")

    private String zuozheName;


    /**
     * 作者手机号
     */
    @TableField(value = "zuozhe_phone")

    private String zuozhePhone;


    /**
     * 作者身份证号
     */
    @TableField(value = "zuozhe_id_number")

    private String zuozheIdNumber;


    /**
     * 现住地址
     */
    @TableField(value = "zuozhe_address")

    private String zuozheAddress;


    /**
     * 出生年月
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
    @TableField(value = "zuozhe_time",fill = FieldFill.UPDATE)

    private Date zuozheTime;


    /**
     * 作者照片
     */
    @TableField(value = "zuozhe_photo")

    private String zuozhePhoto;


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
	 * 设置：作者姓名
	 */
    public String getZuozheName() {
        return zuozheName;
    }


    /**
	 * 获取：作者姓名
	 */

    public void setZuozheName(String zuozheName) {
        this.zuozheName = zuozheName;
    }
    /**
	 * 设置：作者手机号
	 */
    public String getZuozhePhone() {
        return zuozhePhone;
    }


    /**
	 * 获取：作者手机号
	 */

    public void setZuozhePhone(String zuozhePhone) {
        this.zuozhePhone = zuozhePhone;
    }
    /**
	 * 设置：作者身份证号
	 */
    public String getZuozheIdNumber() {
        return zuozheIdNumber;
    }


    /**
	 * 获取：作者身份证号
	 */

    public void setZuozheIdNumber(String zuozheIdNumber) {
        this.zuozheIdNumber = zuozheIdNumber;
    }
    /**
	 * 设置：现住地址
	 */
    public String getZuozheAddress() {
        return zuozheAddress;
    }


    /**
	 * 获取：现住地址
	 */

    public void setZuozheAddress(String zuozheAddress) {
        this.zuozheAddress = zuozheAddress;
    }
    /**
	 * 设置：出生年月
	 */
    public Date getZuozheTime() {
        return zuozheTime;
    }


    /**
	 * 获取：出生年月
	 */

    public void setZuozheTime(Date zuozheTime) {
        this.zuozheTime = zuozheTime;
    }
    /**
	 * 设置：作者照片
	 */
    public String getZuozhePhoto() {
        return zuozhePhoto;
    }


    /**
	 * 获取：作者照片
	 */

    public void setZuozhePhoto(String zuozhePhoto) {
        this.zuozhePhoto = zuozhePhoto;
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
        return "Zuozhe{" +
            "id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", zuozheName=" + zuozheName +
            ", zuozhePhone=" + zuozhePhone +
            ", zuozheIdNumber=" + zuozheIdNumber +
            ", zuozheAddress=" + zuozheAddress +
            ", zuozheTime=" + zuozheTime +
            ", zuozhePhoto=" + zuozhePhoto +
            ", sexTypes=" + sexTypes +
            ", createTime=" + createTime +
        "}";
    }
}
