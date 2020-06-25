package com.qfdmy.controller.dashboard.core;

import com.qfdmy.business.core.service.ICoreUserService;
import com.qfdmy.commons.base.BaseController;
import com.qfdmy.repository.core.domain.CoreUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Lusifer
 * @since 2020-05-28
 */
@RestController
@RequestMapping("core/user")
public class CoreUserController extends BaseController<CoreUser, ICoreUserService> {

}
