package org.netty.tcp.channel;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.RequiredArgsConstructor;
import org.netty.tcp.handler.MessageHandler;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * Netty 通道初始化
 */
@Component
@RequiredArgsConstructor
public class ChannelInit extends ChannelInitializer<SocketChannel> {

	private final MessageHandler messageHandler;

	@Override
	protected void initChannel(SocketChannel channel) {
		channel.pipeline()
				// 心跳时间
				.addLast("idle", new IdleStateHandler(0, 0, 60, TimeUnit.SECONDS))
				// 添加解码器
				.addLast(new StringDecoder())
				// 添加编码器
				.addLast(new StringEncoder())
				// 添加消息处理器
				.addLast(messageHandler);
	}

}

