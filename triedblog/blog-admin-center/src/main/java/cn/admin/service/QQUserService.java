package cn.admin.service;

import java.util.List;

import cn.admin.entity.BlogQQUser;
import cn.commons.common.LayuiTableResult;
import cn.commons.common.PublicResultJosn;

public interface QQUserService {

    /**
     * 
     * @Title: delete   
     * @Description: 单、批删除
     * @param: @param uids
     * @param: @return      
     * @return: PublicResultJosn      
     * @throws
     */
    PublicResultJosn delete(List<String> uids);

    /**
     * 
     * @Title: selectByExample   
     * @Description: 模糊分页查询
     * @param: @param qqUser
     * @param: @param page
     * @param: @param pageSize
     * @param: @return      
     * @return: LayuiTableResult      
     * @throws
     */
    LayuiTableResult selectByExample(BlogQQUser qqUser, Integer page, Integer pageSize);

    /**
     * 
     * @Title: update   
     * @Description: 更新信息（状态）  
     * @param: @param qqUser
     * @param: @return      
     * @return: PublicResultJosn      
     * @throws
     */
    PublicResultJosn update(BlogQQUser qqUser);

}
