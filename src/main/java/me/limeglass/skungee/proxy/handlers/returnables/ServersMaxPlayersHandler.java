package me.limeglass.skungee.proxy.handlers.returnables;

import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;

import me.limeglass.skungee.common.handlercontroller.SkungeeProxyHandler;
import me.limeglass.skungee.common.objects.SkungeeServer;
import me.limeglass.skungee.common.packets.ServerPacket;
import me.limeglass.skungee.common.packets.ServerPacketType;
import me.limeglass.skungee.common.wrappers.SkungeePlatform.Platform;
import me.limeglass.skungee.proxy.sockets.ServerTracker;

public class ServersMaxPlayersHandler extends SkungeeProxyHandler {

	public ServersMaxPlayersHandler() {
		super(Platform.ANY_PROXY, ServerPacketType.MAXPLAYERS);
	}

	@Override
	public Object handlePacket(ServerPacket packet, InetAddress address) {
		Set<Number> limits = new HashSet<>();
		if (packet.getObject() == null)
			return limits;
		for (String server : (String[]) packet.getObject()) {
			for (SkungeeServer tracked : ServerTracker.get(server)) {
				if (tracked == null || !ServerTracker.isResponding(tracked))
					continue;
				limits.add(tracked.getMaxPlayers());
			}
		}
		return limits;
	}

}