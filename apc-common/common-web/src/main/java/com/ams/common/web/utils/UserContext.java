package com.ams.common.web.utils;

import cn.conchit.common.constant.SecurityConstants;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Objects;
/**
 * @author： hetao
 */
public class UserContext {


    public static Long getCurrentUserId() {
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader(SecurityConstants.JWT_PAYLOAD_KEY);
        if (StringUtil.isEmpty(token)) {
            return null;
        }
        try {
            JSONObject jsonObject = JSON.parseObject(URLDecoder.decode(token, "utf-8"));
            if (Objects.isNull(jsonObject)) {
                return null;
            }
            return jsonObject.getLong("userId");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
