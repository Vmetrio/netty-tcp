package org.netty.tcp;

import lombok.RequiredArgsConstructor;
import org.netty.tcp.server.TcpServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class TcpApplication implements ApplicationRunner {

	private final TcpServer tcpServer;

	public static void main(String[] args) {
		SpringApplication.run(TcpApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		tcpServer.start();
	}
}
