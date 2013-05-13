package com.yom.sem.semuy.vocabulary;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.yom.sem.semuy.base.Constant;

public class GEO {
    private static final String NS = Constant.VOB_NS;
    private static Model m_model = ModelFactory.createDefaultModel();

    public static String getURI() {
        return NS;
    }

    public static final Property placeCode = m_model.createProperty(NS, "palceCode");
}
