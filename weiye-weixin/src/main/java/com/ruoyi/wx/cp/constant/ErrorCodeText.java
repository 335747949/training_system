package com.ruoyi.wx.cp.constant;

/**
 * 企业号根据错误码返回错误信息类
 *
 * @author sunlight
 */
public class ErrorCodeText {
    public static String errorMsg(int errorcode) {
        String errmsg = "";
        switch (errorcode) {
            case -1:
                errmsg = "系统繁忙 ";
                break;
            case 0:
                errmsg = "请求成功 ";
                break;
            case 40001:
                errmsg = "获取access_token时Secret错误，或者access_token无效 ";
                break;
            case 40002:
                errmsg = "不合法的凭证类型";
                break;
            case 40003:
                errmsg = "不合法的UserID";
                break;
            case 40004:
                errmsg = "不合法的媒体文件类型 ";
                break;
            case 40005:
                errmsg = "不合法的文件类型 ";
                break;
            case 40006:
                errmsg = "不合法的文件大小";
                break;
            case 40007:
                errmsg = "不合法的媒体文件id";
                break;
            case 40008:
                errmsg = "不合法的消息类型 ";
                break;
            case 40013:
                errmsg = "不合法的corpid ";
                break;
            case 40014:
                errmsg = "不合法的access_token";
                break;
            case 40015:
                errmsg = "不合法的菜单类型";
                break;
            case 40016:
                errmsg = "不合法的按钮个数";
                break;
            case 40017:
                errmsg = "不合法的按钮类型";
                break;
            case 40018:
                errmsg = "不合法的按钮名字长度";
                break;
            case 40019:
                errmsg = "不合法的按钮KEY长度";
                break;
            case 40020:
                errmsg = "不合法的按钮URL长度 ";
                break;
            case 40021:
                errmsg = "不合法的菜单版本号 ";
                break;
            case 40022:
                errmsg = "不合法的子菜单级数";
                break;
            case 40023:
                errmsg = "不合法的子菜单按钮个数";
                break;
            case 40024:
                errmsg = "不合法的子菜单按钮类型";
                break;
            case 40025:
                errmsg = "不合法的子菜单按钮名字长度";
                break;
            case 40026:
                errmsg = "不合法的子菜单按钮KEY长度";
                break;
            case 40027:
                errmsg = "不合法的子菜单按钮URL长度";
                break;
            case 40028:
                errmsg = "不合法的自定义菜单使用员工";
                break;
            case 40029:
                errmsg = "不合法的oauth_code";
                break;
            case 40031:
                errmsg = "不合法的UserID列表";
                break;
            case 40032:
                errmsg = "不合法的UserID列表长度";
                break;
            case 40033:
                errmsg = "不合法的请求字符，不能包含\\uxxxx格式的字符 ";
                break;
            case 40035:
                errmsg = "不合法的参数 ";
                break;
            case 40038:
                errmsg = "不合法的请求格式 ";
                break;
            case 40039:
                errmsg = "不合法的URL长度";
                break;
            case 40040:
                errmsg = "不合法的插件token";
                break;
            case 40041:
                errmsg = "不合法的插件id";
                break;
            case 40042:
                errmsg = "不合法的插件会话";
                break;
            case 40048:
                errmsg = "url中包含不合法domain";
                break;
            case 40054:
                errmsg = "不合法的子菜单url域名";
                break;
            case 40055:
                errmsg = "不合法的按钮url域名 ";
                break;
            case 40056:
                errmsg = "不合法的agentid";
                break;
            case 40057:
                errmsg = "不合法的callbackurl";
                break;
            case 40058:
                errmsg = "不合法的红包参数 ";
                break;
            case 40059:
                errmsg = "不合法的上报地理位置标志位 ";
                break;
            case 40060:
                errmsg = "设置上报地理位置标志位时没有设置callbackurl";
                break;
            case 40061:
                errmsg = "设置应用头像失败";
                break;
            case 40062:
                errmsg = "不合法的应用模式";
                break;
            case 40063:
                errmsg = "红包参数为空";
                break;
            case 40064:
                errmsg = "管理组名字已存在";
                break;
            case 40065:
                errmsg = "不合法的管理组名字长度";
                break;
            case 40066:
                errmsg = "不合法的部门列表";
                break;
            case 40067:
                errmsg = "标题长度不合法 ";
                break;
            case 40068:
                errmsg = "不合法的标签ID";
                break;
            case 40069:
                errmsg = "不合法的标签ID列表";
                break;
            case 40070:
                errmsg = "列表中所有标签（用户）ID都不合法  ";
                break;
            case 40071:
                errmsg = "不合法的标签名字，标签名字已经存在 ";
                break;
            case 40072:
                errmsg = "不合法的标签名字长度";
                break;
            case 40073:
                errmsg = "不合法的openid";
                break;
            case 40074:
                errmsg = "news消息不支持指定为高保密消息";
                break;
            case 41001:
                errmsg = "缺少access_token参数 ";
                break;
            case 41002:
                errmsg = "缺少corpid参数";
                break;
            case 41003:
                errmsg = "缺少refresh_token参数";
                break;
            case 41004:
                errmsg = "缺少secret参数";
                break;
            case 41005:
                errmsg = "缺少多媒体文件数据";
                break;
            case 41006:
                errmsg = "缺少media_id参数";
                break;
            case 41007:
                errmsg = "缺少子菜单数据";
                break;
            case 41008:
                errmsg = "缺少oauth code";
                break;
            case 41009:
                errmsg = "缺少UserID";
                break;
            case 41010:
                errmsg = "缺少url";
                break;
            case 41011:
                errmsg = "缺少agentid";
                break;
            case 41012:
                errmsg = "缺少应用头像mediaid";
                break;
            case 41013:
                errmsg = "缺少应用名字";
                break;
            case 41014:
                errmsg = "缺少应用描述";
                break;
            case 41015:
                errmsg = "缺少Content";
                break;
            case 41016:
                errmsg = "缺少标题";
                break;
            case 41017:
                errmsg = "缺少标签ID";
                break;
            case 41018:
                errmsg = "缺少标签名字 ";
                break;
            case 42001:
                errmsg = "access_token超时 ";
                break;
            case 42002:
                errmsg = "refresh_token超时";
                break;
            case 42003:
                errmsg = "oauth_code超时 ";
                break;
            case 42004:
                errmsg = "插件token超时";
                break;
            case 43001:
                errmsg = "需要GET请求";
                break;
            case 43002:
                errmsg = "需要POST请求";
                break;
            case 43003:
                errmsg = "需要HTTPS";
                break;
            case 43004:
                errmsg = "需要接收者关注";
                break;
            case 43005:
                errmsg = "需要好友关系";
                break;
            case 43006:
                errmsg = "需要订阅";
                break;
            case 43007:
                errmsg = "需要授权";
                break;
            case 43008:
                errmsg = "需要支付授权";
                break;
            case 43009:
                errmsg = "需要员工已关注";
                break;
            case 43010:
                errmsg = "需要处于回调模式";
                break;
            case 43011:
                errmsg = "需要企业授权";
                break;
            case 44001:
                errmsg = "多媒体文件为空";
                break;
            case 44002:
                errmsg = "POST的数据包为空";
                break;
            case 44003:
                errmsg = "图文消息内容为空";
                break;
            case 44004:
                errmsg = "文本消息内容为空";
                break;
            case 45001:
                errmsg = "多媒体文件大小超过限制";
                break;
            case 45002:
                errmsg = "消息内容超过限制";
                break;
            case 45003:
                errmsg = "标题字段超过限制";
                break;
            case 45004:
                errmsg = "描述字段超过限制";
                break;
            case 45005:
                errmsg = "链接字段超过限制";
                break;
            case 45006:
                errmsg = "图片链接字段超过限制";
                break;
            case 45007:
                errmsg = "语音播放时间超过限制";
                break;
            case 45008:
                errmsg = "图文消息超过限制";
                break;
            case 45009:
                errmsg = "接口调用超过限制";
                break;
            case 45010:
                errmsg = "创建菜单个数超过限制";
                break;
            case 45015:
                errmsg = "回复时间超过限制";
                break;
            case 45016:
                errmsg = "系统分组，不允许修改";
                break;
            case 45017:
                errmsg = "分组名字过长";
                break;
            case 45018:
                errmsg = "分组数量超过上限";
                break;
            case 45024:
                errmsg = "账号数量超过上限";
                break;
            case 46001:
                errmsg = "不存在媒体数据";
                break;
            case 46002:
                errmsg = "不存在的菜单版本";
                break;
            case 46003:
                errmsg = "不存在的菜单数据";
                break;
            case 46004:
                errmsg = "不存在的员工";
                break;
            case 47001:
                errmsg = "解析JSON/XML内容错误";
                break;
            case 48002:
                errmsg = "Api禁用";
                break;
            case 50001:
                errmsg = "redirect_uri未授权";
                break;
            case 50002:
                errmsg = "员工不在权限范围";
                break;
            case 50003:
                errmsg = "应用已停用";
                break;
            case 50004:
                errmsg = "员工状态不正确（未关注状态） ";
                break;
            case 50005:
                errmsg = "企业已禁用";
                break;
            case 60001:
                errmsg = "部门长度不符合限制";
                break;
            case 60002:
                errmsg = "部门层级深度超过限制";
                break;
            case 60003:
                errmsg = "部门不存在";
                break;
            case 60004:
                errmsg = "父亲部门不存在";
                break;
            case 60005:
                errmsg = "不允许删除有成员的部门";
                break;
            case 60006:
                errmsg = "不允许删除有子部门的部门";
                break;
            case 60007:
                errmsg = "不允许删除根部门";
                break;
            case 60008:
                errmsg = "部门名称或者ID已存在";
                break;
            case 60009:
                errmsg = "部门名称含有非法字符";
                break;
            case 60010:
                errmsg = "部门存在循环关系";
                break;
            case 60011:
                errmsg = "管理员权限不足，（user/department/agent）无权限";
                break;
            case 60012:
                errmsg = "不允许删除默认应用";
                break;
            case 60013:
                errmsg = "不允许关闭应用";
                break;
            case 60014:
                errmsg = "不允许开启应用";
                break;
            case 60015:
                errmsg = "不允许修改默认应用可见范围";
                break;
            case 60016:
                errmsg = "不允许删除存在成员的标签";
                break;
            case 60017:
                errmsg = "不允许设置企业";
                break;
            case 60102:
                errmsg = "UserID已存在";
                break;
            case 60103:
                errmsg = "手机号码不合法";
                break;
            case 60104:
                errmsg = "手机号码已存在";
                break;
            case 60105:
                errmsg = "邮箱不合法";
                break;
            case 60106:
                errmsg = "邮箱已存在";
                break;
            case 60107:
                errmsg = "微信号不合法";
                break;
            case 60108:
                errmsg = "微信号已存在";
                break;
            case 60109:
                errmsg = "QQ号已存在";
                break;
            case 60110:
                errmsg = "部门个数超出限制";
                break;
            case 60111:
                errmsg = "UserID不存在";
                break;
            case 60112:
                errmsg = "成员姓名不合法";
                break;
            case 60113:
                errmsg = "身份认证信息（微信号/手机/邮箱）不能同时为空 ";
                break;
            case 60114:
                errmsg = "性别不合法";
                break;
            case 60115:
                errmsg = "已关注成员微信不能修改";
                break;
            case 60116:
                errmsg = "扩展属性已存在 ";
                break;
            case 60118:
                errmsg = "成员无有效邀请字段，详情参考(邀请成员关注) 的接口说明 ";
                break;
            case 60119:
                errmsg = "成员已关注 ";
                break;
            case 60120:
                errmsg = "成员已禁用 ";
                break;
            case 60121:
                errmsg = "找不到该成员 ";
                break;
            case 60122:
                errmsg = "邮箱已被外部管理员使用 ";
                break;
            case 60123:
                errmsg = "无效的部门id ";
                break;
            case 60124:
                errmsg = "无效的父部门id ";
                break;
            case 60125:
                errmsg = "非法部门名字，长度超过限制、重名等，重名包括与csv文件中同级部门重名或者与旧组织架构包含成员的同级部门重名 ";
                break;
            case 60126:
                errmsg = "创建部门失败 ";
                break;
            case 60127:
                errmsg = "缺少部门id ";
                break;
            case 60128:
                errmsg = "字段不合法，可能存在主键冲突或者格式错误 ";
                break;
            case 60129:
                errmsg = "用户设置了拒绝邀请 ";
                break;
            case 60131:
                errmsg = "不合法的职位长度 ";
                break;
            case 72015:
                errmsg = "没有操作发票的权限 ";
                break;
            case 302001:
                errmsg = "批量同步成员存在userid为空的用户 ";
                break;
            case 302002:
                errmsg = "管理员userid不存在 ";
                break;
            case 302003:
                errmsg = "存在重复的userid ";
                break;
            case 302004:
                errmsg = "组织架构不合法 ";
                break;
            default:
                errmsg = "请查询微信的错误码含义！ ";
                break;
        }

        return errmsg;
    }
}