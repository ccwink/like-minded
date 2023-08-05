package com.wink.seek.common;

import lombok.Data;


/**
 * 通用分页请求参数包装类
 */
@Data
public class PageRequest{

    /**
     * 页面大小
     */
    protected int pageSize;

    /**
     * 当前第几页
     */
    protected int pageNum;
}
