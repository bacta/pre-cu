package com.ocdsoft.bacta.swg.precu.object.knowledgebase;

import com.ocdsoft.bacta.swg.network.soe.lang.UnicodeString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crush on 8/14/2014.
 */
public class CustomerServiceCategory {
    private UnicodeString categoryName;
    private int categoryId;
    private List<CustomerServiceCategory> subCategories = new ArrayList<>();
    private boolean isBugType;
    private boolean isServiceType;
}
