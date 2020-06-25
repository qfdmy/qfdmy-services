package com.qfdmy.controller.dashboard.core;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qfdmy.business.core.service.ICorePostService;
import com.qfdmy.commons.base.BaseController;
import com.qfdmy.commons.response.ResponseCode;
import com.qfdmy.commons.response.ResponseResult;
import com.qfdmy.repository.core.domain.CorePost;
import com.qfdmy.repository.core.dto.CorePostDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author Lusifer
 * @since 2020-06-12
 */
@RestController
@RequestMapping("core/post")
public class CorePostController extends BaseController<CorePost, ICorePostService> {

}
