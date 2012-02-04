package org.menacheri.server.netty;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.timeout.ReadTimeoutHandler;
import org.jboss.netty.util.Timer;
import org.menacheri.handlers.netty.FlashPolicyServerDecoder;
import org.menacheri.handlers.netty.FlashPolicyServerHandler;


/**
 * @author <a href="http://www.waywardmonkeys.com/">Bruce Mitchener</a>
 */
public class FlashPolicyServerPipelineFactory implements ChannelPipelineFactory
{
	private Timer timer;

    public ChannelPipeline getPipeline() throws Exception {
        // Create a default pipeline implementation.
        ChannelPipeline pipeline = pipeline();
        pipeline.addLast("timeout", new ReadTimeoutHandler(timer, 30));
        pipeline.addLast("decoder", new FlashPolicyServerDecoder());
        pipeline.addLast("handler", new FlashPolicyServerHandler());
        return pipeline;
    }

	public Timer getTimer()
	{
		return timer;
	}

	public void setTimer(Timer timer)
	{
		this.timer = timer;
	}
}