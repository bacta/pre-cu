package com.ocdsoft.bacta.swg.server.controller.game;

import com.ocdsoft.bacta.soe.connection.ConnectionRole;
import com.ocdsoft.bacta.soe.connection.SoeUdpConnection;
import com.ocdsoft.bacta.soe.controller.ConnectionRolesAllowed;
import com.ocdsoft.bacta.soe.controller.GameNetworkMessageController;
import com.ocdsoft.bacta.soe.controller.MessageHandled;
import com.ocdsoft.bacta.swg.server.message.game.ConnectionServerLagResponse;
import com.ocdsoft.bacta.swg.server.message.game.GameServerLagResponse;
import com.ocdsoft.bacta.swg.server.message.game.LagRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MessageHandled(handles = LagRequest.class)
@ConnectionRolesAllowed({ConnectionRole.AUTHENTICATED})
public class LagRequestController implements GameNetworkMessageController<LagRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LagRequestController.class);

    @Override
    public void handleIncoming(SoeUdpConnection connection, LagRequest message) {
        connection.sendMessage(new ConnectionServerLagResponse());
        connection.sendMessage(new GameServerLagResponse());
    }
}

