package com.qfdmy.repository.core.dto;

import com.qfdmy.repository.core.domain.CorePost;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 文章数据传输对象
 * @author Lusifer
 * @since v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CorePostDTO extends CorePost implements Serializable {
    private String username;
}
