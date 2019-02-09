package cn.web.service;

import cn.commons.common.PublicResultJosn;
import cn.web.entity.BlogQQUser;

public interface BlogQQUserService {

    /**
     * 根据openid查找用户
    * @Title: checkOppenId
    * @param @param oppenId
    * @param @return
    * @return BlogQQUser
    * @throws
     */
    BlogQQUser checkOppenId(String oppenId);

    /**
     * 插入一个QQ用户
    * @Title: insert
    * @param @param blogQQUser
    * @param @return
    * @return int
    * @throws
     */
    int insert(BlogQQUser blogQQUser);

    /**
     * 根据openid更新基本信息
    * @Title: updateByPrimaryKeySelective
    * @param @param blogQQUser
    * @param @return
    * @return BlogQQUser
    * @throws
     */
    int updateByPrimaryKeySelective(BlogQQUser blogQQUser);

    /**
     * 获取最近登录用户
    * @Title: listRecentUser
    * @param @return
    * @return PublicResultJosn
    * @throws
     */
    PublicResultJosn listRecentUser();

}
