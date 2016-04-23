package com.ocdsoft.bacta.swg.precu;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import com.ocdsoft.bacta.engine.service.object.ObjectService;
import com.ocdsoft.bacta.soe.ServerState;
import com.ocdsoft.bacta.soe.io.udp.NetworkConfiguration;
import com.ocdsoft.bacta.soe.io.udp.game.GameNetworkConfiguration;
import com.ocdsoft.bacta.soe.io.udp.game.GameServer;
import com.ocdsoft.bacta.soe.io.udp.game.GameServerState;
import com.ocdsoft.bacta.soe.service.OutgoingConnectionService;
import com.ocdsoft.bacta.swg.precu.object.ServerObject;
import com.ocdsoft.bacta.swg.precu.object.login.ClusterEntry;

public class GameModule extends AbstractModule implements Module {

    @Override
    protected void configure() {
        bind(ServerState.class).to(GameServerState.class);
        bind(NetworkConfiguration.class).to(GameNetworkConfiguration.class);
        bind(OutgoingConnectionService.class).to(GameServer.GameOutgoingConnectionService.class);
    }

}
