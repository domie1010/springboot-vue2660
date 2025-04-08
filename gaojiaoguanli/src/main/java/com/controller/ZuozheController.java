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

import com.entity.ZuozheEntity;

import com.service.ZuozheService;
import com.entity.view.ZuozheView;
import com.service.ZhuanjiaService;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 作者
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/zuozhe")
public class ZuozheController {
    private static final Logger logger = LoggerFactory.getLogger(ZuozheController.class);

    @Autowired
    private ZuozheService zuozheService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service
    @Autowired
    private ZhuanjiaService zhuanjiaService;


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
        PageUtils page = zuozheService.queryPage(params);

        //字典表数据转换
        List<ZuozheView> list =(List<ZuozheView>)page.getList();
        for(ZuozheView c:list){
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
        ZuozheEntity zuozhe = zuozheService.selectById(id);
        if(zuozhe !=null){
            //entity转view
            ZuozheView view = new ZuozheView();
            BeanUtils.copyProperties( zuozhe , view );//把实体数据重构到view中

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
    public R save(@RequestBody ZuozheEntity zuozhe, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zuozhe:{}",this.getClass().getName(),zuozhe.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        Wrapper<ZuozheEntity> queryWrapper = new EntityWrapper<ZuozheEntity>()
            .eq("username", zuozhe.getUsername())
            .or()
            .eq("zuozhe_phone", zuozhe.getZuozhePhone())
            .or()
            .eq("zuozhe_id_number", zuozhe.getZuozheIdNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZuozheEntity zuozheEntity = zuozheService.selectOne(queryWrapper);
        if(zuozheEntity==null){
            zuozhe.setCreateTime(new Date());
            zuozhe.setPassword("123456");
            zuozheService.insert(zuozhe);
            return R.ok();
        }else {
            return R.error(511,"账户或者身份证号或者手机号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZuozheEntity zuozhe, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,zuozhe:{}",this.getClass().getName(),zuozhe.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        //根据字段查询是否有相同数据
        Wrapper<ZuozheEntity> queryWrapper = new EntityWrapper<ZuozheEntity>()
            .notIn("id",zuozhe.getId())
            .andNew()
            .eq("username", zuozhe.getUsername())
            .or()
            .eq("zuozhe_phone", zuozhe.getZuozhePhone())
            .or()
            .eq("zuozhe_id_number", zuozhe.getZuozheIdNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZuozheEntity zuozheEntity = zuozheService.selectOne(queryWrapper);
        if("".equals(zuozhe.getZuozhePhoto()) || "null".equals(zuozhe.getZuozhePhoto())){
                zuozhe.setZuozhePhoto(null);
        }
        if(zuozheEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      zuozhe.set
            //  }
            zuozheService.updateById(zuozhe);//根据id更新
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
        zuozheService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        ZuozheEntity zuozhe = zuozheService.selectOne(new EntityWrapper<ZuozheEntity>().eq("username", username));
        if(zuozhe==null || !zuozhe.getPassword().equals(password))
            return R.error("账号或密码不正确");
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(yonghu.getRoleTypes());
        String token = tokenService.generateToken(zuozhe.getId(),username, "zuozhe", "作者");
        R r = R.ok();
        r.put("token", token);
        r.put("role","作者");
        r.put("username",zuozhe.getZuozheName());
        r.put("tableName","zuozhe");
        r.put("userId",zuozhe.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody ZuozheEntity zuozhe){
    //    	ValidatorUtils.validateEntity(user);
        if(zuozheService.selectOne(new EntityWrapper<ZuozheEntity>().eq("username", zuozhe.getUsername()).orNew().eq("zuozhe_phone",zuozhe.getZuozhePhone()).orNew().eq("zuozhe_id_number",zuozhe.getZuozheIdNumber())) !=null)
            return R.error("账户已存在或手机号或身份证号已经被使用");
        zuozhe.setCreateTime(new Date());
        zuozheService.insert(zuozhe);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        ZuozheEntity zuozhe = new ZuozheEntity();
        zuozhe.setPassword("123456");
        zuozhe.setId(id);
        zuozheService.updateById(zuozhe);
        return R.ok();
    }

    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrZuozhe(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        ZuozheEntity zuozhe = zuozheService.selectById(id);
        if(zuozhe !=null){
            //entity转view
            ZuozheView view = new ZuozheView();
            BeanUtils.copyProperties( zuozhe , view );//把实体数据重构到view中

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

