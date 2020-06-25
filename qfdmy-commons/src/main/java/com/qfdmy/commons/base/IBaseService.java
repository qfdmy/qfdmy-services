package com.qfdmy.commons.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 通用业务接口
 *
 * @author Lusifer
 * @since v1.0.0
 */
public interface IBaseService<T extends BaseDomain> extends IService<T> {
    /**
     * 新增
     *
     * @param domain 领域模型
     * @return {@code boolean}
     */
    boolean create(T domain);

    /**
     * 删除
     *
     * @param id {@code Long} ID
     * @return {@code boolean}
     */
    boolean remove(Long id);

    /**
     * 更新
     *
     * @param domain 领域模型
     * @return {@code boolean}
     */
    boolean update(T domain);

    /**
     * 获取
     *
     * @param id {@code Long} ID
     * @return 领域模型
     */
    T get(Long id);

    /**
     * 分页
     *
     * @param current {@code int} 页码
     * @param size    {@code int} 笔数
     * @param domain  领域模型
     * @return 管理员分页数据
     */
    IPage<?> page(int current, int size, T domain);
}
