-- page参数
local args = ngx.req.get_uri_args()
-- uri
local uri = ngx.var.uri
local m, err = ngx.re.match(uri, "([^/w+$]+)/*([^/w+$]+)*")
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
local path_1=path.."/article/classify/"..m[2]
local path_2=path.."/article/blogArticleClassifyLable"
local path_3=path.."/article/blogArticleLable"
local path_4=path.."/article/hotBlogArticle"
local path_5=path.."/article/classify"
local path_6=path.."/recentUser"
local path_7=path.."/system/baseInfo"

local _M = {}
function _M.index()
	local httpClient=http.new()
	httpClient:set_timeout(500)
	--文章列表(根据分类id确定)
	local resp_1, err_1 = httpClient:request_uri(url, {
		method = "GET",
		path = path_1
	})
	-- 文章数、分类数、标签数
	local resp_2, err_2 = httpClient:request_uri(url, {
		method = "GET",
		path = path_2
	})
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
	-- 网站基本信息
	local resp_7, err_7 = httpClient:request_uri(url, {
		method = "GET",
		path = path_7
	})
	-- 获取请求结果(json->table)
	local articleList=cjson.decode(resp_1.body)
	local blogArticleClassifyLable=cjson.decode(resp_2.body)
	local blogArticleLable=cjson.decode(resp_3.body)
	local hotBlogArticle=cjson.decode(resp_4.body)
	local classify=cjson.decode(resp_5.body)
	local recentUser=cjson.decode(resp_6.body)
	local webBaseInfo=cjson.decode(resp_7.body)
	httpClient:close()
	local result ={articles=articleList,classifyName=m[2],blogArticleClassifyLables=blogArticleClassifyLable,blogArticleLables=blogArticleLable,hotBlogArticles=hotBlogArticle,classifys=classify,recentUsers=recentUser,webBaseInfo=webBaseInfo}
	--ngx.say(cjson.encode(result))
	--ngx.say(path_1)
	--math.randomseed(os.time())
    --ngx.say(math.random(1,50))
   -- 1、外部模板文件
    template.render('index.html', result)

end

return _M