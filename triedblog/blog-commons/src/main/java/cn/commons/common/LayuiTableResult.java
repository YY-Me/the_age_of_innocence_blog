package cn.commons.common;

/**
 * layui-table返回json格式
 * 
 * @author 偶尔有点困
 * @date 2018年5月4日
 */
public class LayuiTableResult extends PublicResultJosn {

	/**
	 * 总条数
	 */
	private Long count;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
}
