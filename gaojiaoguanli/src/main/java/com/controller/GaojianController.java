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

import com.entity.GaojianEntity;

import com.service.GaojianService;
import com.entity.view.GaojianView;
import com.service.ZhuanjiaService;
import com.entity.ZhuanjiaEntity;
import com.service.ZuozheService;
import com.entity.ZuozheEntity;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 稿件
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/gaojian")
public class GaojianController {
    private static final Logger logger = LoggerFactory.getLogger(GaojianController.class);

    @Autowired
    private GaojianService gaojianService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service
    @Autowired
    private ZhuanjiaService zhuanjiaService;
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
//        else if("专家".equals(role))
//            params.put("zhuanjiaId",request.getSession().getAttribute("userId"));
        params.put("orderBy","id");
        PageUtils page = gaojianService.queryPage(params);

        //字典表数据转换
        List<GaojianView> list =(List<GaojianView>)page.getList();
        for(GaojianView c:list){
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
        GaojianEntity gaojian = gaojianService.selectById(id);
        if(gaojian !=null){
            //entity转view
            GaojianView view = new GaojianView();
            BeanUtils.copyProperties( gaojian , view );//把实体数据重构到view中

            //级联表
            ZhuanjiaEntity zhuanjia = zhuanjiaService.selectById(gaojian.getZhuanjiaId());
            if(zhuanjia != null){
                BeanUtils.copyProperties( zhuanjia , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setZhuanjiaId(zhuanjia.getId());
            }
            //级联表
            ZuozheEntity zuozhe = zuozheService.selectById(gaojian.getZuozheId());
            if(zuozhe != null){
                BeanUtils.copyProperties( zuozhe , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setZuozheId(zuozhe.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 新增稿件
    */
    @RequestMapping("/save")
    public R save(@RequestBody GaojianEntity gaojian, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,gaojian:{}",this.getClass().getName(),gaojian.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("作者".equals(role)){
            gaojian.setZuozheId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
            gaojian.setGaojianYesnoTypes(1);
        }else if("专家".equals(role))
            gaojian.setZhuanjiaId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        Wrapper<GaojianEntity> queryWrapper = new EntityWrapper<GaojianEntity>()
//            .eq("zuozhe_id", gaojian.getZuozheId())
//            .eq("zhuanjia_id", gaojian.getZhuanjiaId())
//            .eq("gaojian_name", gaojian.getGaojianName())
//            .eq("gaojian_types", gaojian.getGaojianTypes())
//            .eq("gaojian_yesno_types", gaojian.getGaojianYesnoTypes())
//            ;
//        logger.info("sql语句:"+queryWrapper.getSqlSegment());
//        GaojianEntity gaojianEntity = gaojianService.selectOne(queryWrapper);
//        if(gaojianEntity==null){
            gaojian.setInsertTime(new Date());
            gaojian.setCreateTime(new Date());
            gaojianService.insert(gaojian);
            return R.ok();
//        }else {
//            return R.error(511,"表中有相同数据");
//        }
    }

    /**
    * 审核稿件
    */
    @RequestMapping("/update")
    public R update(@RequestBody GaojianEntity gaojian, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,gaojian:{}",this.getClass().getName(),gaojian.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("作者".equals(role))
            gaojian.setZuozheId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("专家".equals(role))
            gaojian.setZhuanjiaId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
//        Wrapper<GaojianEntity> queryWrapper = new EntityWrapper<GaojianEntity>()
//            .notIn("id",gaojian.getId())
//            .andNew()
//            .eq("zuozhe_id", gaojian.getZuozheId())
//            .eq("zhuanjia_id", gaojian.getZhuanjiaId())
//            .eq("gaojian_name", gaojian.getGaojianName())
//            .eq("gaojian_types", gaojian.getGaojianTypes())
//            .eq("gaojian_yesno_types", gaojian.getGaojianYesnoTypes())
//            ;
//        logger.info("sql语句:"+queryWrapper.getSqlSegment());
//        GaojianEntity gaojianEntity = gaojianService.selectOne(queryWrapper);
        if("".equals(gaojian.getGaojianFile()) || "null".equals(gaojian.getGaojianFile())){
                gaojian.setGaojianFile(null);
        }
//        if(gaojianEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      gaojian.set
            //  }
            gaojianService.updateById(gaojian);//根据id更新
            return R.ok();
//        }else {
//            return R.error(511,"表中有相同数据");
//        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        gaojianService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }






}

