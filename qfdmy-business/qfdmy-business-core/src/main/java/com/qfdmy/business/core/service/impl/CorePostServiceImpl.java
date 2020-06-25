package com.qfdmy.business.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfdmy.business.core.service.ICorePostService;
import com.qfdmy.commons.base.BaseServiceImpl;
import com.qfdmy.repository.core.domain.CorePost;
import com.qfdmy.repository.core.dto.CorePostDTO;
import com.qfdmy.repository.core.mapper.CorePostMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author Lusifer
 * @since 2020-06-12
 */
@Service
public class CorePostServiceImpl extends BaseServiceImpl<CorePostMapper, CorePost> implements ICorePostService {

    @Override
    public IPage<CorePostDTO> page(int current, int size, CorePost corePost) {
        Page<CorePostDTO> page = new Page<>(current, size);

        LambdaQueryWrapper<CorePostDTO> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .like(StringUtils.isNotBlank(corePost.getPostTitle()),
                        CorePostDTO::getPostTitle, corePost.getPostTitle())
                .and(StringUtils.isNotBlank(corePost.getPostStatus()),
                        i -> i.eq(CorePostDTO::getPostStatus, corePost.getPostStatus()))
                .and(StringUtils.isNotBlank(corePost.getPostType()),
                        i -> i.eq(CorePostDTO::getPostType, corePost.getPostType()));
        wrapper
                .orderByDesc(CorePost::getCreateTime);

        return baseMapper.listByQuery(page, wrapper);
    }
}
