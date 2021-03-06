package com.ocdsoft.bacta.swg.shared.portal;

import com.ocdsoft.bacta.swg.shared.container.Container;
import com.ocdsoft.bacta.swg.shared.foundation.Crc;
import com.ocdsoft.bacta.swg.shared.object.GameObject;

/**
 * Created by crush on 4/27/2016.
 */
public class CellProperty extends Container {
    public static int getClassPropertyId() {
        return 0x9C3DFF88;
    }

    private static final GameObject worldCellObject;
    private static final CellProperty worldCellProperty;

    public static CellProperty getWorldCellProperty() {
        return worldCellProperty;
    }

    static {
        worldCellObject = new GameObject();
        worldCellProperty = new CellProperty(worldCellObject);
        worldCellProperty.cellIndex = 0;
        worldCellProperty.cellName = "world";
        worldCellProperty.cellNameCrc = Crc.normalizeAndCalculate(worldCellProperty.cellName);
        worldCellObject.addProperty(worldCellProperty);
    }

    private PortalProperty portalProperty;
    private int cellIndex;
    private GameObject appearanceObject;
    private String cellName;
    private int cellNameCrc;

    /*
    const PortalProperty             *m_portalProperty;
    int                               m_cellIndex;
    mutable Object                   *m_appearanceObject;
    PortalObjectList                 *m_portalObjectList;
    mutable bool                      m_visible;
    Floor                            *m_floor;
    const char                       *m_cellName;
    uint32                            m_cellNameCrc;
    DPVS::Cell                       *m_dpvsCell;
    const Texture                    *m_environmentTexture;
    bool                              m_fogEnabled;
    PackedArgb                        m_fogColor;
    float                             m_fogDensity;
    bool mutable m_appliedInteriorLayout;
    */

    public CellProperty(final GameObject cellObject) {
        super(getClassPropertyId(), cellObject);

        cellIndex = -1;
    }

    public void addObjectToWorld(final GameObject object) {
        object.setParentCell(this);

        if (!isWorldCell()) {
            //addToContents(object);
        } else {
            LOGGER.warn("Attempting to add object {} to world cell", object.getNetworkId());
        }
    }

    public boolean isWorldCell() {
        return this == worldCellProperty;
    }
}
