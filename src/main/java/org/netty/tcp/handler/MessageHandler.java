package org.netty.tcp.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.netty.tcp.service.ApiService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * MQTT消息处理,单例启动
 */
@Slf4j
@Component
@ChannelHandler.Sharable
@RequiredArgsConstructor
public class MessageHandler extends SimpleChannelInboundHandler<String> {

	@Resource
	private ApiService apiService;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String message) throws Exception {
		log.info("\n");
		log.info("channelId:" + ctx.channel().id());
		log.info("收到消息:{}", message);
		String result = apiService.callApi(message);
		// 回复客户端
		ctx.writeAndFlush(result);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) {
		log.info("\n");
		log.info("成功关闭连接,channelId：{}", ctx.channel().id());
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("\n");
		log.info("成功建立连接,channelId：{}", ctx.channel().id());
		super.channelActive(ctx);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		log.info("心跳事件时触发");
		if (evt instanceof IdleStateEvent) {
			log.info("发送心跳");
			IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
		} else {
			super.userEventTriggered(ctx, evt);
		}
	}
}

