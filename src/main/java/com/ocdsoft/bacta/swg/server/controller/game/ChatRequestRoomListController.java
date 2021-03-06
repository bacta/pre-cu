package com.ocdsoft.bacta.swg.server.controller.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocdsoft.bacta.soe.controller.MessageHandled;
import com.ocdsoft.bacta.soe.controller.GameNetworkMessageController;
import com.ocdsoft.bacta.soe.controller.ConnectionRolesAllowed;
import com.ocdsoft.bacta.soe.connection.ConnectionRole;
import com.ocdsoft.bacta.soe.connection.SoeUdpConnection;
import com.ocdsoft.bacta.swg.server.message.game.ChatRequestRoomList;

@MessageHandled(handles = ChatRequestRoomList.class)
@ConnectionRolesAllowed({ConnectionRole.AUTHENTICATED})
public class ChatRequestRoomListController implements GameNetworkMessageController<ChatRequestRoomList> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatRequestRoomListController.class);

    @Override
    public void handleIncoming(SoeUdpConnection connection, ChatRequestRoomList message) {
        LOGGER.warn("This controller is not implemented");
    }
}

