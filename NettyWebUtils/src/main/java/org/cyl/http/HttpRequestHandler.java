package org.cyl.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import java.util.HashMap;
import java.util.Map;

import static io.netty.handler.codec.http.HttpUtil.is100ContinueExpected;

public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private   String path=null;

    private   String method=null;

    public HttpRequestHandler(String path,String method){
        this.path=path;
        this.method=method;
    }

    public HttpRequestHandler(){

    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setpath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        if (is100ContinueExpected(req)) {

            ctx.write(new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.CONTINUE));
        }
        // 获取请求的uri
        String uri = req.uri();
        Map<String,String> resMap = new HashMap<>();
        resMap.put("method",req.method().name());
        resMap.put("uri",uri);
        if (!uri.equals(path)){
            ctx.fireChannelRead(req);
        }

        if (uri.equals(path)&&resMap.get("method").equals(Constant.Get)){
            handleResource(ctx, resMap);
        } else if (uri.equals(path)&&resMap.get("method").equals(Constant.Post)){
            handlePostResource(ctx, resMap,req);
        }else {
            handleNotFound(ctx, resMap);
        }
    }

    private void handleNotFound(ChannelHandlerContext ctx, Map<String, String> resMap) {

    }

    private void handlePostResource(ChannelHandlerContext ctx, Map<String, String> resMap, FullHttpRequest req) {

    }

    private void handleResource(ChannelHandlerContext ctx, Map<String, String> resMap) {

    }
}
