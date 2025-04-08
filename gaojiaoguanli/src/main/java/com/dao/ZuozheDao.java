package com.dao;

import com.entity.ZuozheEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZuozheView;

/**
 * 作者 Dao 接口
 *
 * @author 
 */
public interface ZuozheDao extends BaseMapper<ZuozheEntity> {

   List<ZuozheView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
