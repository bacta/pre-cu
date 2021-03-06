package com.ocdsoft.bacta.swg.server.controller.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocdsoft.bacta.soe.controller.MessageHandled;
import com.ocdsoft.bacta.soe.controller.GameNetworkMessageController;
import com.ocdsoft.bacta.soe.controller.ConnectionRolesAllowed;
import com.ocdsoft.bacta.soe.connection.ConnectionRole;
import com.ocdsoft.bacta.soe.connection.SoeUdpConnection;
import com.ocdsoft.bacta.swg.server.message.game.CommoditiesItemTypeListRequest;

@MessageHandled(handles = CommoditiesItemTypeListRequest.class)
@ConnectionRolesAllowed({ConnectionRole.AUTHENTICATED})
public class CommoditiesItemTypeListRequestController implements GameNetworkMessageController<CommoditiesItemTypeListRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommoditiesItemTypeListRequestController.class);

    @Override
    public void handleIncoming(SoeUdpConnection connection, CommoditiesItemTypeListRequest message) {
        LOGGER.warn("This controller is not implemented");
    }
}

