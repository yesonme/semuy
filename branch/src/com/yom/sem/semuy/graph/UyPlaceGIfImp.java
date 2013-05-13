package com.yom.sem.semuy.graph;

import com.franz.agraph.jena.AGGraph;
import com.franz.agraph.jena.AGGraphMaker;
import com.franz.agraph.jena.AGModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DC;
import com.yom.sem.semuy.base.Constant;
import com.yom.sem.semuy.base.ServerBase;
import com.yom.sem.semuy.vocabulary.GEO;
import com.yom.sem.semuy.vocabulary.UNI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UyPlaceGIfImp implements UyPlaceGIf {
    public boolean importPlaces(String file) {
        ServerBase server = new ServerBase();
        AGGraphMaker maker = server.getMarker("place");

        try {
            FileReader read = new FileReader(file);
            BufferedReader br = new BufferedReader(read);
            String row;

            while((row = br.readLine())!=null){
                String[] infos = row.split(" ");
                String code = infos[0].trim();
                String name = infos[1].trim();

                AGGraph graph = maker.createGraph(Constant.p_prefix + code);
                AGModel model = new AGModel(graph);

                Resource provRes = model.createResource(Constant.p_prefix + code);

                provRes.addProperty(GEO.placeCode, code)
                        .addProperty(UNI.name, name);

                model.close();
                graph.close();
            }

            br.close();
            read.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
