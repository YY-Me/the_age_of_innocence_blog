
-- json转换
local cjson = require "cjson"
local _M = {}
function _M.index()

	math.randomseed(tostring(ngx.now()):reverse():sub(1, 6))
	 
	ngx.say(string.sub(tostring(math.random(1,10)),1,1))

end

return _M