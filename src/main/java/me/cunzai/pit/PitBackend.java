package me.cunzai.pit;

import me.cunzai.pit.handler.MessageHandler;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: EmptyIrony
 * @Date: 2021/1/7 23:11
 */
public class PitBackend {

    public static void main(String[] args) {

        //服务类
        ServerBootstrap bootstrap = new ServerBootstrap();

        //boss线程监听端口，worker线程负责数据读写  他们里面都会负责一个selector。
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        //设置niosocket工厂
        bootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));

        //设置管道的工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

            @Override
            public ChannelPipeline getPipeline() throws Exception {

                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast("messageHandler", new MessageHandler());
                return pipeline;
            }
        });

        bootstrap.bind(new InetSocketAddress(6666));

        System.out.println("start!!!");

    }


}
