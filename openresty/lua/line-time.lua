-- 模板
local template = require "resty.template"
-- json转换
local cjson = require "cjson"
-- 发送请求
local http = require("resty.http")
--要请求的ip  
url="http://127.0.0.1:9879"
local path="/api/blog-web"

local path_6=path.."/recentUser"
local path_7=path.."/system/baseInfo"

local _M = {}
function _M.index()
	
	local httpClient=http.new()
	httpClient:set_timeout(500)
	
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
	
	local recentUser=cjson.decode(resp_6.body)
	local webBaseInfo=cjson.decode(resp_7.body)
	local is_this_index="jinti"
	
	httpClient:close()
	local result={is_this_index=is_this_index,recentUsers=recentUser,webBaseInfo=webBaseInfo}
   -- 1、外部模板文件
    template.render('line-time.html', result)

end

return _M