package com.wink.seek.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wink.seek.model.domain.Team;
import com.wink.seek.model.domain.User;
import com.wink.seek.model.dto.TeamQuery;
import com.wink.seek.model.request.TeamJoinRequest;
import com.wink.seek.model.request.TeamQuitRequest;
import com.wink.seek.model.request.TeamUpdateRequest;
import com.wink.seek.model.vo.TeamUserVO;

import java.util.List;

/**
* @author wink
* @description 针对表【team(队伍表)】的数据库操作Service
* @createDate 2023-07-21 14:12:37
*/
public interface TeamService extends IService<Team> {

    /**
     * 创建队伍
     * @param team
     * @param currentUser
     * @return
     */
    long addTeam(Team team, User currentUser);

    /**
     * 查询队伍列表
     * @param teamQuery
     * @return
     */
    List<TeamUserVO> listTeams(TeamQuery teamQuery, boolean isAdmin);

    /**
     * 修改队伍
     * @param teamUpdateRequest
     * @param currentUser
     * @return
     */
    boolean updateTeam(TeamUpdateRequest teamUpdateRequest, User currentUser);

    /**
     * 加入队伍
     * @param teamJoinRequest
     * @param currentUser
     * @return
     */
    boolean joinTeam(TeamJoinRequest teamJoinRequest, User currentUser);

    /**
     * 退出队伍
     * @param teamQuitRequest
     * @param currentUser
     * @return
     */
    boolean quitTeam(TeamQuitRequest teamQuitRequest, User currentUser);

    /**
     * 删除队伍
     * @param id
     * @param currentUser
     * @return
     */
    boolean deleteTeam(long id, User currentUser);
}
