package cn.admin.service;

import java.util.List;

import cn.admin.entity.SystemDict;
import cn.commons.common.LayuiTableResult;
import cn.commons.common.PublicResultJosn;

public interface DictionaryService {

	public PublicResultJosn add(SystemDict dict);

	public PublicResultJosn delete(List<Long> ids);

	public PublicResultJosn update(SystemDict dict);

	public LayuiTableResult select(SystemDict dict, Integer page, Integer pageSize);

}
