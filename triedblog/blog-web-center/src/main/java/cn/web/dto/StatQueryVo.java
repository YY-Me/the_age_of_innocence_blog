package cn.web.dto;
/**
 * 
 * @ClassName:  StatQueryVo   
 * @Description: 分时间查询
 * @author: yuyong 
 * @date:   2018年10月8日 下午8:09:17   
 *     
 * @Copyright: 2018 www.xxx.com Inc. All rights reserved. 
 * @note: 注意：本内容仅限于xxx公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class StatQueryVo {

	private String start;
	
	private String end;

	public StatQueryVo() {
		super();
	}

	public StatQueryVo(String start, String end) {
		super();
		this.start = start;
		this.end = end;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
	
}
