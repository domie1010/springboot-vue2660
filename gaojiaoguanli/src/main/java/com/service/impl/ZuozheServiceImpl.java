package com.service.impl;

import com.utils.StringUtil;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;

import com.dao.ZuozheDao;
import com.entity.ZuozheEntity;
import com.service.ZuozheService;
import com.entity.view.ZuozheView;

/**
 * 作者 服务实现类
 */
@Service("zuozheService")
@Transactional
public class ZuozheServiceImpl extends ServiceImpl<ZuozheDao, ZuozheEntity> implements ZuozheService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<ZuozheView> page =new Query<ZuozheView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
