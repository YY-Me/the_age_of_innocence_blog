package cn.admin.service;

import java.util.List;

import cn.admin.dto.BlogArticleAdd;
import cn.commons.common.LayuiTableResult;
import cn.commons.common.PublicResultJosn;

public interface ArticleService {

	PublicResultJosn add(BlogArticleAdd blog);

	PublicResultJosn delete(List<String> bids);

	PublicResultJosn updateByExample(BlogArticleAdd blog, Boolean flag);

	LayuiTableResult selectByExample(BlogArticleAdd blog, Integer page, Integer pageSize);

	PublicResultJosn selectByBid(String bid);
}
