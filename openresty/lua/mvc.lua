local uri = ngx.var.uri
-- 默认首页
local moduleName
if uri == "" or uri == "/" then
    local res = ngx.location.capture("/index", {})
    ngx.say(res.body)
    return
end

-- 模板
local template = require "resty.template"

local m, err = ngx.re.match(uri, "([^/w+$]+)/*([^/w+$]+)*")

	  moduleName = m[1]     -- 模块名
local method = "index"         -- 默认访问index方法
--ngx.say(uri)

-- 控制器默认在web包下面
local prefix = "lua."       
local path = prefix .. moduleName

-- 尝试引入模块，不存在则报错
local ret, ctrl, err = pcall(require, path)
local is_debug = true -- 调试阶段，会输出错误信息到页面上
if ret == false then
    if is_debug then
        ngx.status = 404
        ngx.say("<p style='font-size: 16px'>Error: <span style='color:red'>" .. ctrl .. "</span> module not found !</p>")
    end
    ngx.exit(404)
end

-- 尝试获取模块方法，不存在则报错
local req_method = ctrl[method]
if req_method == nil then
    if is_debug then
        ngx.status = 404
        ngx.say("<p style='font-size: 16px'>Error: <span style='color:red'>" .. method .. "()</span> method not found in <span style='color:red'>" .. moduleName .. "</span> lua module !</p>")
    end
    ngx.exit(404)
end


-- 执行模块方法，报错则显示错误信息，所见即所得，可以追踪lua报错行数
ret, err = pcall(req_method)
if ret == false then
    if is_debug then
        ngx.status = 404
        local errors={err=err}
        template.render('maintain.html', errors)
		--ngx.say(errorhtml.body)
        --ngx.say("<p style='font-size: 16px'>Error: <span style='color:red'>" .. err .. "</span></p>")
    else
        ngx.exit(500)
    end
end







