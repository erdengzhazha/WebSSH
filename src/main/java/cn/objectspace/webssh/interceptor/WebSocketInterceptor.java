package cn.objectspace.webssh.interceptor;

import cn.objectspace.webssh.constant.ConstantPool;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

public class WebSocketInterceptor implements HandshakeInterceptor {
    Logger log = LoggerFactory.getLogger(WebSocketInterceptor.class);

    /**
     * @Description: Handler处理前调用
     * @Param: [serverHttpRequest, serverHttpResponse, webSocketHandler, map]
     * @return: boolean
     * @Author: NoCortY
     * @Date: 2020/3/1
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
            log.info("用户向服务器发起了握手申请！");
            log.info("【ServletServerHttpRequest . getHeaders】{}",request.getHeaders());
            log.info("【ServletServerHttpRequest . getMethod】{}",request.getMethod());
            log.info("【ServletServerHttpRequest . getURI】{}",request.getURI());
            log.info("【ServletServerHttpRequest . getBody】{}",JSON.toJSON(request.getBody()));
            log.info("【ServletServerHttpRequest . getServletRequest】{}",request.getServletRequest());
            HttpServletRequest servletRequest = request.getServletRequest();
            log.info("【HttpServletRequest】getRequestedSessionId{}",servletRequest.getRequestedSessionId());
            log.info("【HttpServletRequest】getCookies{}",servletRequest.getCookies());
            log.info("【HttpServletRequest】getRequestURL{}",servletRequest.getRequestURL());
            log.info("【HttpServletRequest】getParameterMap {}", JSON.toJSON(servletRequest.getParameterMap()));
            log.info("【HttpServletRequest】getContextPath {}", JSON.toJSON(servletRequest.getContextPath()));
            log.info("【HttpServletRequest】getServerInfo {}", JSON.toJSON(servletRequest.getServletContext().getServerInfo()));
            //生成一个UUID
            String uuid = UUID.randomUUID().toString().replace("-","");
            //将uuid放到websocketsession中的 attributes
            attributes.put(ConstantPool.USER_UUID_KEY, uuid);
            return true; // 握手🤝成功
        } else {
            return false; // 拒绝握手🤝
        }
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {


    }
}
