package com.wink.seek.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wink.seek.mapper.TagMapper;
import com.wink.seek.model.domain.Tag;
import com.wink.seek.service.TagService;
import org.springframework.stereotype.Service;

/**
* @author wink
* @description 针对表【tag(标签)】的数据库操作Service实现
* @createDate 2023-07-08 17:11:23
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService {

}




