package cn.admin.service;

import cn.admin.entity.BlogLabelClassify;
import cn.commons.common.PublicResultJosn;

public interface LabelClassifyService {

    /**
     * 获取所有标签、分类
     * @Title: listLabelClassify
     * @param @return
     * @return PublicResultJosn
     * @throws
     */
    PublicResultJosn listLabelClassify();

    /**
     * 
     * @Title: addUpd   
     * @Description: 添加、修改
     * @param: @param labelClassify
     * @param: @return      
     * @return: PublicResultJosn      
     * @throws
     */
    PublicResultJosn addUpd(BlogLabelClassify labelClassify);

    /**
     * 
     * @Title: del   
     * @Description: 删除
     * @param: @param id
     * @param: @return      
     * @return: PublicResultJosn      
     * @throws
     */
    PublicResultJosn del(String type, Long id);

}
