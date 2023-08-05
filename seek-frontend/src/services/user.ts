import myAxios from "../plugins/myAxios.js";
import {getCurrentUserState, setCurrentUserState} from "../states/user";

/**
 * 获取当前用户信息
 */
export const getCurrentUser = async () => {
    /*const currentUser = getCurrentUserState();
    // 存在直接返回
    if (currentUser) {
        return currentUser;
    }*/
    // 不存在则从远程获取
    const res = await myAxios.get('/user/current');
    if (res.code === 0){
        setCurrentUserState(res.data)
        return res.data;
    }
    return null;
}