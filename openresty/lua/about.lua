-- 模板
local template = require "resty.template"
-- json转换
local cjson = require "cjson"
-- 发送请求
local http = require("resty.http")
--要请求的ip  
url="http://127.0.0.1:9879"
local path="/api/blog-web"

local _M = {}
function _M.index()
	
	local is_this_index="about"
	
	local result={is_this_index=is_this_index}
   -- 1、外部模板文件
    template.render('about.html', result)

end

return _M