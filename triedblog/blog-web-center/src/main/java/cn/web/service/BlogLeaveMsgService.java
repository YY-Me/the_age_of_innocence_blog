package cn.web.service;

import cn.commons.common.PublicResultJosn;
import cn.web.dto.LayuiFlowArticleComment;
import cn.web.entity.BlogLeaveMsg;

public interface BlogLeaveMsgService {

    /**
     * 添加留言
    * @Title: add
    * @param @param blogLeaveMsg
    * @param @return
    * @return PublicResultJosn
    * @throws
     */
    PublicResultJosn add(BlogLeaveMsg blogLeaveMsg);

    /**
     * 分页查询留言信息
    * @Title: selectLeaveMsgByPage
    * @param @param page
    * @param @param pageSize
    * @param @return
    * @return LayuiFlowArticleComment
    * @throws
     */
    LayuiFlowArticleComment selectLeaveMsgByPage(Integer page, Integer pageSize);

}
