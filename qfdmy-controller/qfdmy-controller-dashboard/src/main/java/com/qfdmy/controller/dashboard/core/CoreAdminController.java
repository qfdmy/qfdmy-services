package com.qfdmy.controller.dashboard.core;

import com.qfdmy.business.core.service.ICoreAdminService;
import com.qfdmy.commons.base.BaseController;
import com.qfdmy.repository.core.domain.CoreAdmin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员
 *
 * @author Lusifer
 * @since v1.0.0
 */
@RestController
@RequestMapping("core/admin")
public class CoreAdminController extends BaseController<CoreAdmin, ICoreAdminService> {

}
