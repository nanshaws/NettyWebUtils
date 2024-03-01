package org.cyl.http;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new HttpServerCodec());// http 编解码
        pipeline.addLast("httpAggregator",new HttpObjectAggregator(512*1024)); // http 消息聚合器                                                                     512*1024为接收的最大contentlength
        pipeline.addLast(new HttpRequestHandler("/api/addfriend",Constant.Post));// 请求处理器
        pipeline.addLast(new HttpRequestHandler("/api/deletefriend",Constant.Post));// 请求处理器
        pipeline.addLast(new HttpRequestHandler("/api/findFriend",Constant.Get));
        pipeline.addLast(new HttpRequestHandler("/api/searchfriends",Constant.Post));
        pipeline.addLast(new HttpRequestHandler("/api/updatefriend",Constant.Post));

        pipeline.addLast(new HttpRequestHandler("/api/getuserInfo",Constant.Post));
        pipeline.addLast(new HttpRequestHandler("/api/login",Constant.Post));
        pipeline.addLast(new HttpRequestHandler("/api/logout",Constant.Post));
        pipeline.addLast(new HttpRequestHandler("/api/register",Constant.Post));
        pipeline.addLast(new HttpRequestHandler("/api/save",Constant.Post));
    }

}
