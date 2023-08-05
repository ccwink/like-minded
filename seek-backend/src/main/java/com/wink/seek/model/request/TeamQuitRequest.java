package com.wink.seek.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户退出队伍请求体
 */
@Data
public class TeamQuitRequest implements Serializable {
    private static final long serialVersionUID = 2348639564430830854L;

    /**
     *  id
     */
    private Long teamId;
}
