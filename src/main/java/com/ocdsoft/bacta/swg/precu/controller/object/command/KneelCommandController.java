package com.ocdsoft.bacta.swg.precu.controller.object.command;

import com.google.inject.Inject;
import com.ocdsoft.bacta.soe.controller.Command;
import com.ocdsoft.bacta.soe.connection.SoeUdpConnection;
import com.ocdsoft.bacta.swg.precu.object.tangible.TangibleObject;
import com.ocdsoft.bacta.swg.precu.object.tangible.creature.CreatureObject;
import com.ocdsoft.bacta.swg.precu.object.tangible.creature.CreaturePosture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Command(id = 0x1b48b26)
public class KneelCommandController implements CommandController {

    private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

    @Inject
    public KneelCommandController() {

    }

    @Override
    public void handleCommand(SoeUdpConnection connection, TangibleObject invoker, TangibleObject target, String params) {

        CreatureObject creature = client.getCharacter();
        creature.setPosture(CreaturePosture.CROUCHED);

    }

}
