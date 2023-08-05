package com.wink.seek.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wink.seek.mapper.UserTeamMapper;
import com.wink.seek.model.domain.UserTeam;
import com.wink.seek.service.UserTeamService;
import org.springframework.stereotype.Service;

/**
* @author wink
* @description 针对表【user_team(用户队伍关系)】的数据库操作Service实现
* @createDate 2023-07-21 14:14:08
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService {

}




