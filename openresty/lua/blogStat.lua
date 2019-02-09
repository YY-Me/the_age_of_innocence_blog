
-- 模板
local template = require "resty.template"

local _M = {}
function _M.index()
   -- 1、外部模板文件
    template.render('user-area-view.html', {})

end

return _M