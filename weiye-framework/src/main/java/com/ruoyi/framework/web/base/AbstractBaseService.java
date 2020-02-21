package com.ruoyi.framework.web.base;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * @Author agile
 * Date: 17/1/13
 * Time: 15:13
 * Version 1.0.0
 */
public abstract interface AbstractBaseService<T> {
    /**
     * 查询单条数据信息
     * 保证条件使数据唯一
     * @param entity
     * @return
     */
    public T selectOne(T entity);

    /**
     * 查询单条数据信息
     * @param id 代码
     * @return
     */
    public T selectById(Object id);

    /**
     * 单表分页查询
     * @param entity
     * @return
     */
    public List<T> selectListByPage(T entity);

    /**
     * 查询数据集合
     * @param entity
     * @return
     */
    public List<T> selectList(T entity) ;

    /**
     * 查询所有数据集合
     * @return
     */
    public List<T> selectListAll();

    /**
     * 查询数据量
     * @param entity
     * @return
     */
    public Long selectCount(T entity);

    /**
     * 保存(并且自动给填充用户信息)
     * @param entity
     * @return
     */
    public int insert(T entity);

    /**
     * 保存不为null的数据(并且自动给填充用户信息)
     * @param entity
     * @return
     */
    public int insertSelective(T entity);

    /**
     * 删除符合条件的数据
     * @param entity
     * @return
     */
    public int delete(T entity) ;

    /**
     * 根据编码ID删除
     * @param id
     * @return
     */
    public int deleteById(Object id);

    /**
     * 根据id集合删除
     * ids 例如 '1','2'
     * @param ids
     * @return
     */
    public int deleteByIds(String ids);

    /**
     * 根据编码ID全部字段更新（自动填充用户信息）
     * 包含（NULL）
     * @param entity
     * @return
     */
    public int updateById(T entity) ;

    /**
     * 根据编码ID更新（自动填充用户信息）
     * 不包含（NULL）
     * @param entity
     * @return
     */
    public int updateSelectiveById(T entity);

    /**
     * 自定义模板查询
     * @param example
     * @return
     */
    public List<T> selectByExample(Object example);
    /**
     * 自定义模板查询数量
     * @param example
     * @return
     */
    public int selectCountByExample(Object example);

    /**
     * 设置请求分页数据
     */
    public Page startPage();
}
