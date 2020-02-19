package me.limeglass.skungee.proxy.handlers.returnables;

import java.net.InetAddress;
import java.util.stream.Collectors;

import me.limeglass.skungee.common.handlercontroller.SkungeeBungeePlayerHandler;
import me.limeglass.skungee.common.packets.ServerPacket;
import me.limeglass.skungee.common.packets.ServerPacketType;

public class PlayerUUIDHandler extends SkungeeBungeePlayerHandler {

	public PlayerUUIDHandler() {
		super(ServerPacketType.PLAYERUUID);
	}

	@Override
	public Object handlePacket(ServerPacket packet, InetAddress address) {
		if (packet.getObject() == null)
			return null;
		return players.parallelStream().map(player -> player.getUniqueId().toString()).collect(Collectors.toSet());
	}

}