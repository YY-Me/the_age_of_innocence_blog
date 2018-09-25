-- 模板
local template = require "resty.template"
-- json转换
local cjson = require "cjson"
-- 发送请求
local http = require("resty.http")
--要请求的ip  
url="http://127.0.0.1:9879"
local path="/api/blog-web"
-- 请求的路径
local path_2=path.."/article/blogArticleClassifyLable"
local path_3=path.."/article/blogArticleLable"
local path_4=path.."/article/hotBlogArticle"
local path_5=path.."/article/classify"
local path_6=path.."/recentUser"
local _M = {}
function _M.index()
	local httpClient=http.new()
	httpClient:set_timeout(500)
	-- 文章数、分类数、标签数
	--local resp_2, err_2 = httpClient:request_uri(url, {
	--	method = "GET",
	--	path = path_2
	--})
	-- 查询标签
	local resp_3, err_3 = httpClient:request_uri(url, {
		method = "GET",
		path = path_3
	})
	-- 热门文章
	local resp_4, err_4 = httpClient:request_uri(url, {
		method = "GET",
		path = path_4
	})
	-- 文章分类
	local resp_5, err_5 = httpClient:request_uri(url, {
		method = "GET",
		path = path_5
	})
	-- 最近访客
	local resp_6, err_6 = httpClient:request_uri(url, {
		method = "GET",
		path = path_6
	})
	-- 获取请求结果(json->table)
	--local blogArticleClassifyLable=cjson.decode(resp_2.body)
	local blogArticleLable=cjson.decode(resp_3.body)
	local hotBlogArticle=cjson.decode(resp_4.body)
	local classify=cjson.decode(resp_5.body)
	local recentUser=cjson.decode(resp_6.body)
	local is_this_index="leave_msg"
	httpClient:close()
	
	local result ={article_details=article_detail,blogArticleLables=blogArticleLable,hotBlogArticles=hotBlogArticle,classifys=classify,is_this_index=is_this_index,recentUsers=recentUser}
	
   -- 1、外部模板文件
    template.render('leave_msg.html', result)

end

return _M