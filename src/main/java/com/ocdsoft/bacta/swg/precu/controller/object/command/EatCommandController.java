package com.ocdsoft.bacta.swg.precu.controller.object.command;

import com.ocdsoft.bacta.soe.controller.Command;
import com.ocdsoft.bacta.soe.connection.SoeUdpConnection;
import com.ocdsoft.bacta.swg.precu.object.tangible.TangibleObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Command(id=0x4353ddc9)
public class EatCommandController implements CommandController {

	private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

	@Override
	public void handleCommand(SoeUdpConnection connection, TangibleObject invoker, TangibleObject target, String params) {
		
		logger.warn("This command not implemented");
		
	}
	
}
