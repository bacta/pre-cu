package com.ocdsoft.bacta.swg.server.component;

import com.ocdsoft.bacta.swg.server.message.game.messagequeue.MessageQueueObjectMenuRequest;
import com.ocdsoft.bacta.swg.server.object.ServerObject;

/**
 * Created by crush on 9/5/2014.
 */
public abstract class ObjectMenuComponent implements ExtensibleComponent {
    @Override
    public final int getComponentType() { return ComponentTypes.ObjectMenuComponent; }

    public abstract void onObjectMenuRequest(ServerObject invoker, MessageQueueObjectMenuRequest objectMenuRequest);

    public abstract void onObjectMenuSelection(ServerObject invoker, int selectItemId);
}