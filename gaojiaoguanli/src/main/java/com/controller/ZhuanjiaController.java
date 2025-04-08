package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.StringUtil;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.ZhuanjiaEntity;

import com.service.ZhuanjiaService;
import com.entity.view.ZhuanjiaView;
import com.service.ZuozheService;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 专家
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/zhuanjia")
public class ZhuanjiaController {
    private static final Logger logger = LoggerFactory.getLogger(ZhuanjiaController.class);

    @Autowired
    private ZhuanjiaService zhuanjiaService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service
    @Autowired
    private ZuozheService zuozheService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("作者".equals(role))
            params.put("zuozheId",request.getSession().getAttribute("userId"));
        else if("专家".equals(role))
            params.put("zhuanjiaId",request.getSession().getAttribute("userId"));
        params.put("orderBy","id");
        PageUtils page = zhuanjiaService.queryPage(params);

        //字典表数据转换
        List<ZhuanjiaView> list =(List<ZhuanjiaView>)page.getList();
        for(ZhuanjiaView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZhuanjiaEntity zhuanjia = zhuanjiaService.selectById(id);
        if(zhuanjia !=null){
            //entity转view
            ZhuanjiaView view = new ZhuanjiaView();
            BeanUtils.copyProperties( zhuanjia , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ZhuanjiaEntity zhuanjia, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zhuanjia:{}",this.getClass().getName(),zhuanjia.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        Wrapper<ZhuanjiaEntity> queryWrapper = new EntityWrapper<ZhuanjiaEntity>()
            .eq("username", zhuanjia.getUsername())
            .or()
            .eq("zhuanjia_phone", zhuanjia.getZhuanjiaPhone())
            .or()
            .eq("zhuanjia_id_number", zhuanjia.getZhuanjiaIdNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhuanjiaEntity zhuanjiaEntity = zhuanjiaService.selectOne(queryWrapper);
        if(zhuanjiaEntity==null){
            zhuanjia.setCreateTime(new Date());
            zhuanjia.setPassword("123456");
            zhuanjiaService.insert(zhuanjia);
            return R.ok();
        }else {
            return R.error(511,"账户或者身份证号或者手机号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZhuanjiaEntity zhuanjia, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,zhuanjia:{}",this.getClass().getName(),zhuanjia.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        //根据字段查询是否有相同数据
        Wrapper<ZhuanjiaEntity> queryWrapper = new EntityWrapper<ZhuanjiaEntity>()
            .notIn("id",zhuanjia.getId())
            .andNew()
            .eq("username", zhuanjia.getUsername())
            .or()
            .eq("zhuanjia_phone", zhuanjia.getZhuanjiaPhone())
            .or()
            .eq("zhuanjia_id_number", zhuanjia.getZhuanjiaIdNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhuanjiaEntity zhuanjiaEntity = zhuanjiaService.selectOne(queryWrapper);
        if("".equals(zhuanjia.getZhuanjiaPhoto()) || "null".equals(zhuanjia.getZhuanjiaPhoto())){
                zhuanjia.setZhuanjiaPhoto(null);
        }
        if(zhuanjiaEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      zhuanjia.set
            //  }
            zhuanjiaService.updateById(zhuanjia);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者身份证号或者手机号已经被使用");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        zhuanjiaService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        ZhuanjiaEntity zhuanjia = zhuanjiaService.selectOne(new EntityWrapper<ZhuanjiaEntity>().eq("username", username));
        if(zhuanjia==null || !zhuanjia.getPassword().equals(password))
            return R.error("账号或密码不正确");
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(yonghu.getRoleTypes());
        String token = tokenService.generateToken(zhuanjia.getId(),username, "zhuanjia", "专家");
        R r = R.ok();
        r.put("token", token);
        r.put("role","专家");
        r.put("username",zhuanjia.getZhuanjiaName());
        r.put("tableName","zhuanjia");
        r.put("userId",zhuanjia.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody ZhuanjiaEntity zhuanjia){
    //    	ValidatorUtils.validateEntity(user);
        if(zhuanjiaService.selectOne(new EntityWrapper<ZhuanjiaEntity>().eq("username", zhuanjia.getUsername()).orNew().eq("zhuanjia_phone",zhuanjia.getZhuanjiaPhone()).orNew().eq("zhuanjia_id_number",zhuanjia.getZhuanjiaIdNumber())) !=null)
            return R.error("账户已存在或手机号或身份证号已经被使用");
        zhuanjia.setCreateTime(new Date());
        zhuanjiaService.insert(zhuanjia);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        ZhuanjiaEntity zhuanjia = new ZhuanjiaEntity();
        zhuanjia.setPassword("123456");
        zhuanjia.setId(id);
        zhuanjiaService.updateById(zhuanjia);
        return R.ok();
    }

    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrZhuanjia(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        ZhuanjiaEntity zhuanjia = zhuanjiaService.selectById(id);
        if(zhuanjia !=null){
            //entity转view
            ZhuanjiaView view = new ZhuanjiaView();
            BeanUtils.copyProperties( zhuanjia , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }






}

