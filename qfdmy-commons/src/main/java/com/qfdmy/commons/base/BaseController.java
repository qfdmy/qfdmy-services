package com.qfdmy.commons.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qfdmy.commons.response.ResponseCode;
import com.qfdmy.commons.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 通用请求处理
 * @author Lusifer
 * @since v1.0.0
 */
@SuppressWarnings("all")
public abstract class BaseController<T extends BaseDomain, S extends IBaseService<T>> {

    @Resource
    protected HttpServletRequest request;

    @Autowired
    protected S service;

    /**
     * 新增
     *
     * @param domain 领域模型
     * @return {@link ResponseResult}
     */
    @PostMapping("create")
    public ResponseResult create(@Valid @RequestBody T domain) {
        // 业务逻辑
        boolean created = service.create(domain);
        if (created) {
            return ResponseResult.success("创建成功");
        }

        return ResponseResult.failure(ResponseCode.INTERFACE_ADDRESS_INVALID);
    }

    /**
     * 删除
     *
     * @param id {@code Long}
     * @return {@link ResponseResult}
     */
    @DeleteMapping("remove/{id}")
    public ResponseResult remove(@PathVariable Long id) {
        // 业务逻辑
        boolean deleted = service.remove(id);
        if (deleted) {
            return ResponseResult.success("删除成功");
        }

        return ResponseResult.failure(ResponseCode.INTERFACE_ADDRESS_INVALID);
    }

    /**
     * 修改
     *
     * @param domain 领域模型
     * @return {@link ResponseResult}
     */
    @PutMapping("update")
    public ResponseResult update(@Valid @RequestBody T domain) {
        // 业务逻辑
        boolean updated = service.update(domain);
        if (updated) {
            return ResponseResult.success("编辑成功");
        }

        return ResponseResult.failure(ResponseCode.INTERFACE_ADDRESS_INVALID);
    }

    /**
     * 获取
     *
     * @param id {@code Long}
     * @return {@link ResponseResult}
     */
    @GetMapping("get/{id}")
    public ResponseResult get(@PathVariable Long id) {
        T domain = service.get(id);
        return ResponseResult.success(domain);
    }

    /**
     * 分页
     *
     * @param current {@code int} 页码
     * @param size    {@code int} 笔数
     * @return {@link ResponseResult}
     */
    @GetMapping("page")
    public ResponseResult page(
            @RequestParam int current, @RequestParam int size, @ModelAttribute T domain) {
        IPage<?> page = service.page(current, size, domain);
        return ResponseResult.success(page);
    }
}
