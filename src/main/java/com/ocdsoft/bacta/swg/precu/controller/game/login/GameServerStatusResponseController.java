package com.ocdsoft.bacta.swg.precu.controller.game.login;

import com.google.inject.Inject;

import com.ocdsoft.bacta.soe.connection.ConnectionRole;
import com.ocdsoft.bacta.soe.connection.SoeUdpConnection;
import com.ocdsoft.bacta.soe.controller.ConnectionRolesAllowed;
import com.ocdsoft.bacta.soe.controller.GameNetworkMessageController;
import com.ocdsoft.bacta.soe.controller.MessageHandled;
import com.ocdsoft.bacta.soe.io.udp.game.GameServerState;
import com.ocdsoft.bacta.soe.message.TerminateReason;
import com.ocdsoft.bacta.swg.precu.message.login.GameServerStatusResponse;
import com.ocdsoft.bacta.swg.precu.object.login.ClusterEntry;


/**
 * Created by kburkhardt on 1/31/15.
 */
@MessageHandled(handles = GameServerStatusResponse.class)
@ConnectionRolesAllowed({ConnectionRole.WHITELISTED})
public class GameServerStatusResponseController implements GameNetworkMessageController<GameServerStatusResponse> {
    
    private final GameServerState<ClusterEntry> serverState;
    
    @Inject
    public GameServerStatusResponseController(final GameServerState<ClusterEntry> serverState) {
        this.serverState = serverState;
    }
    
    
    @Override
    public void handleIncoming(SoeUdpConnection loginConnection, GameServerStatusResponse message) throws Exception {

        if(serverState.getId() < 2) {
            serverState.setId(message.getClusterId());
        }
        
        loginConnection.terminate(TerminateReason.NONE);
    }
}
