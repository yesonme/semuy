package com.yom.sem.semuy.graph;

import com.franz.agraph.jena.AGGraph;
import com.franz.agraph.jena.AGGraphMaker;
import com.franz.agraph.jena.AGModel;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.yom.sem.semuy.base.Constant;
import com.yom.sem.semuy.base.ServerBase;
import com.yom.sem.semuy.sparql.UyUniversityQIf;
import com.yom.sem.semuy.sparql.UyUniversityQIfImp;
import com.yom.sem.semuy.vocabulary.UNI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class UySpecailtyGIfImp implements UySpecialtyGIf {
    private static AGGraphMaker maker = ServerBase.getMarker("specialty");

    public boolean importCategory4M(String file) {
        try {
            FileReader read = new FileReader(file);
            BufferedReader br = new BufferedReader(read);
            String row;

            AGGraph graph;

            if (maker.hasGraph(Constant.cate_graph)) {
                graph = maker.openGraph(Constant.cate_graph);
            } else {
                graph = maker.createGraph(Constant.cate_graph);
            }

            AGModel model = new AGModel(graph);

            try {
                while ((row = br.readLine())!=null) {
                    String[] items = row.split(" ");
                    Resource cate_res = model.createResource(Constant.cate_graph + "/" + items[0]);

                    if (!cate_res.hasProperty(UNI.code, items[0])) {
                        cate_res.addProperty(UNI.code, items[0])
                                .addProperty(RDFS.label, items[1]);
                    }
                }
            } finally {
                model.close();
                graph.close();
                br.close();
                read.close();
            }

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean importSubject4M(String file) {
        try {
            FileReader read = new FileReader(file);
            BufferedReader br = new BufferedReader(read);
            String row;

            AGGraph graph;

            if (maker.hasGraph(Constant.subj_graph)) {
                graph = maker.openGraph(Constant.subj_graph);
            } else {
                graph = maker.createGraph(Constant.subj_graph);
            }

            AGModel model = new AGModel(graph);

            try {
                Resource res = model.createResource(Constant.subj_graph);

                while ((row = br.readLine())!=null) {
                    String[] items = row.split(" ");
                    String cate = items[0].substring(0,2);

                    Resource subj_res = model.createResource(Constant.subj_graph + "/" + items[0]);
                    Resource cate_res = model.createResource(Constant.cate_graph + "/" + cate);

                    Resource ref_res = null;

                    if (items.length > 2) {
                        ref_res = model.createResource(Constant.subj_graph + "/" + items[2]);
                    }

                    if (!subj_res.hasProperty(UNI.code, items[0])) {
                        subj_res.addProperty(UNI.code, items[0])
                                .addProperty(RDFS.label, items[1])
                                .addProperty(DCTerms.isPartOf, cate_res);

                        if (null != ref_res) {
                            subj_res.addProperty(OWL.sameAs, ref_res);
                        }
                    }
                }
            } finally {
                model.close();
                graph.close();
                br.close();
                read.close();
            }

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean importSpecialty4M(String file) {
        try {
            FileReader read = new FileReader(file);
            BufferedReader br = new BufferedReader(read);
            String row;

            AGGraph graph;

            if (maker.hasGraph(Constant.spec_graph)) {
                graph = maker.openGraph(Constant.spec_graph);
            } else {
                graph = maker.createGraph(Constant.spec_graph);
            }

            AGModel model = new AGModel(graph);

            try {
                Resource res = model.createResource(Constant.spec_graph);

                while ((row = br.readLine())!=null) {
                    String[] items = row.split(" ");
                    String subj = items[0].substring(0, 4);

                    Resource spec_res = model.createResource(Constant.spec_graph + "/" + items[0]);
                    Resource subj_res = model.createResource(Constant.subj_graph + "/" + subj);

                    Resource ref_res = null;

                    if (items.length > 2) {
                        ref_res = model.createResource(Constant.spec_graph + "/" + items[2]);
                    }

                    if (!spec_res.hasProperty(UNI.code, items[0])) {
                        spec_res.addProperty(UNI.code, items[0])
                                .addProperty(RDFS.label, items[1])
                                .addProperty(DCTerms.isPartOf, subj_res);

                        if (null != ref_res) {
                            spec_res.addProperty(OWL.sameAs, ref_res);
                        }
                    }
                }
            } finally {
                model.close();
                graph.close();
                br.close();
                read.close();
            }

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
