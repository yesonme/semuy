package com.yom.sem.semuy.sparql;

import com.franz.agraph.jena.AGModel;
import com.yom.sem.semuy.base.CatalogBase;
import com.yom.sem.semuy.base.Namespace;

import java.util.ArrayList;

public class UyPlaceQIfImp implements UyPlaceQIf {
    private static AGModel model = CatalogBase.getModel("place");
    private static Namespace ns = new Namespace();

    public ArrayList getPlaceDetails(String uri) {
        String queryString = ns.getNsPrefixString() +
                "SELECT ?name " +
                "WHERE { " +
                "   <" + uri + "> uni:name ?name . " +
                "}";

        return QueryExec.query(model, queryString, "name");
    }
}
