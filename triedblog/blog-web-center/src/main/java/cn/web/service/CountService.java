package cn.web.service;

import cn.commons.common.PublicResultJosn;
import cn.web.dto.StatQueryVo;

public interface CountService {

	/**
	 * @Title: selectAreaByExample   
	 * @Description: 地区统计查询
	 * @param: @return      
	 * @return: PublicResultJosn      
	 * @throws
	 */
	PublicResultJosn selectAreaByExample(StatQueryVo queryVo);

	/**
	 * @Title: selectOsByExample   
	 * @Description: Os统计查询
	 * @param: @return      
	 * @return: PublicResultJosn      
	 * @throws
	 */
	PublicResultJosn selectOsByExample(StatQueryVo queryVo);

	/**
	 * @Title: selectBrowserByExample   
	 * @Description: Browser统计查询
	 * @param: @return      
	 * @return: PublicResultJosn      
	 * @throws
	 */
	PublicResultJosn selectBrowserByExample(StatQueryVo queryVo);

}
