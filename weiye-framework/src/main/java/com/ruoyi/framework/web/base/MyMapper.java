package com.ruoyi.framework.web.base;

/**
 * Created by Administrator on 2017/8/4.
 */

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 被继承的Mapper，一般业务Mapper继承它
 *
 * @author admin
 * @since 2017年8月22日11:50:27
 */
public interface MyMapper<T> extends Mapper<T>,
        MySqlMapper<T>,
        ConditionMapper<T>,
        IdsMapper<T> {

    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}