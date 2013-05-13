package com.yom.sem.semuy.sparql;

import com.franz.agraph.jena.AGModel;
import com.franz.agraph.jena.AGQuery;
import com.franz.agraph.jena.AGQueryExecutionFactory;
import com.franz.agraph.jena.AGQueryFactory;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;

import java.util.ArrayList;
import java.util.HashMap;

public class QueryExec {
    public static ArrayList query(AGModel model, String queryString, String... objects) {
        ArrayList resultList = new ArrayList();

        AGQuery query = AGQueryFactory.create(queryString);
        QueryExecution qe = AGQueryExecutionFactory.create(query, model);

        try {
            ResultSet rs = qe.execSelect();
            while (rs.hasNext()) {
                HashMap rm = new HashMap();
                QuerySolution row = rs.next();

                if (null != objects) {
                    for (int i=0;i<objects.length;i++) {
                        RDFNode node = row.get(objects[i]);
                        rm.put(objects[i], node);
                    }
                }

                resultList.add(rm);
            }
        } finally {
            qe.close();
        }

        return resultList;
    }
}
