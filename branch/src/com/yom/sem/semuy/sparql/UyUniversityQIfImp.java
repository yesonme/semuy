package com.yom.sem.semuy.sparql;

import com.franz.agraph.jena.AGModel;
import com.yom.sem.semuy.base.CatalogBase;
import com.yom.sem.semuy.base.Namespace;

import java.util.ArrayList;
import java.util.Map;

public class UyUniversityQIfImp implements UyUniversityQIf {
    private static AGModel model = CatalogBase.getModel("university");
    private static Namespace ns = new Namespace();

    public ArrayList getAllUniversities() {
        String queryString = ns.getNsPrefixString() +
                "SELECT DISTINCT ?uni ?name ?code ?province ?attrName " +
                "WHERE { " +
                "   ?uni uni:name ?name ; " +
                "        uni:uniCode ?code ; " +
                "        uni:locatedIn ?locate ; " +
                "        uni:type ?type ; " +
                "        uni:attribute ?attr . " +
                "   ?locate uni:name ?province . " +
                "   ?attr uni:name ?attrName . " +
                "} " +
                "ORDER BY ASC(?code) ";

        return QueryExec.query(model, queryString, "uni", "name", "code", "province", "attrName");
    }

    public ArrayList getAllUniversities(String... grades) {
        String queryString = ns.getNsPrefixString() +
                "SELECT DISTINCT ?uni ?name " +
                "WHERE { " +
                "   ?uni uni:name ?name ; ";

        String gradeString = "";

        if (grades.length > 0) {
            if (grades.length > 1) {
                for (int i=1;i<grades.length;i++) {
                    gradeString = " uni:grade '" + grades[i] + "'; ";
                }
            }

            queryString = queryString + gradeString +
                    "      uni:uniCode ?code . " +
                    "} " +
                    "ORDER BY ASC(?code)";

            return QueryExec.query(model, queryString, "uni", "name");
        }

        return getAllUniversities();
    }

    public ArrayList getUniversitySubject(String name) {
        String queryString = ns.getNsPrefixString() +
                "SELECT ?uni " +
                "WHERE { " +
                "   ?uni uni:name ?name . " +
                "FILTER REGEX(?name, '^" + name + "$') " +
                "}";

        return QueryExec.query(model, queryString, "uni");
    }
}
