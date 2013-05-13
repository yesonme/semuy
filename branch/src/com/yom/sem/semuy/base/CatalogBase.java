package com.yom.sem.semuy.base;

import com.franz.agraph.jena.AGGraphMaker;
import com.franz.agraph.jena.AGModel;

public class CatalogBase {
    public static AGModel getModel(String repository_id) {
        AGGraphMaker marker = ServerBase.getMarker(repository_id);

        return new AGModel(marker.getUnionOfAllGraphs());
    }
}
