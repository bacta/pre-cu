package com.ocdsoft.bacta.swg.precu.controller;

import com.ocdsoft.bacta.swg.network.soe.buffer.SoeByteBuf;
import com.ocdsoft.bacta.swg.network.swg.ServerType;
import com.ocdsoft.bacta.swg.network.swg.SwgController;
import com.ocdsoft.bacta.swg.network.swg.controller.SwgMessageController;
import com.ocdsoft.bacta.swg.server.game.GameClient;
import com.ocdsoft.bacta.swg.server.game.message.chat.ChatRequestRoomList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SwgController(server = ServerType.GAME, handles = ChatRequestRoomList.class)
public class ChatRequestRoomListController implements SwgMessageController<GameClient> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void handleIncoming(GameClient client, SoeByteBuf data)
            throws Exception {

        logger.warn("Not implemented.");

        //client.sendMessage(new ChatRoomList(rooms));
    }

}
