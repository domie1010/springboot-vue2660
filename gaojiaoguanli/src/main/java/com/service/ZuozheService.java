package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ZuozheEntity;
import java.util.Map;

/**
 * 作者 服务类
 */
public interface ZuozheService extends IService<ZuozheEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}