package com.yom.sem.semuy.base;

import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.yom.sem.semuy.vocabulary.GEO;
import com.yom.sem.semuy.vocabulary.UNI;

import java.util.HashMap;
import java.util.Map;

public class Namespace {
    private static final Map<String, String> names = new HashMap<String, String>();

    public String getNsPrefixString() {
        String prefix = "";

        for (Map.Entry entry : names.entrySet()) {
            String pre = entry.getKey().toString();
            String ns = entry.getValue().toString();

            prefix += "PREFIX " + pre + ":<" + ns + "> \n";
        }

        return prefix;
    }

    public Namespace() {
        if (names.isEmpty()) {
//            names.put("dc", DC.getURI());
//            names.put("dct", DCTerms.getURI());
            names.put("uni", UNI.getURI());
            names.put("geo", GEO.getURI());
//            names.put("foaf", FOAF.getURI());
        }
    }
}
