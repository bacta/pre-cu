package com.ocdsoft.bacta.swg.server.game.controller.client;

import com.ocdsoft.bacta.soe.connection.ConnectionRole;
import com.ocdsoft.bacta.soe.connection.SoeUdpConnection;
import com.ocdsoft.bacta.soe.controller.ConnectionRolesAllowed;
import com.ocdsoft.bacta.soe.controller.GameNetworkMessageController;
import com.ocdsoft.bacta.soe.controller.MessageHandled;
import com.ocdsoft.bacta.swg.server.game.message.client.CmdSceneReady;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MessageHandled(handles = CmdSceneReady.class)
@ConnectionRolesAllowed({ConnectionRole.AUTHENTICATED})
public class CmdSceneReadyController implements GameNetworkMessageController<CmdSceneReady> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmdSceneReadyController.class);

    @Override
    public void handleIncoming(SoeUdpConnection connection, CmdSceneReady message) {
        final CmdSceneReady startScene = new CmdSceneReady();
        connection.sendMessage(startScene);
    }
}

