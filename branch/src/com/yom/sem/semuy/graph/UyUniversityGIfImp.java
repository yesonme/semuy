package com.yom.sem.semuy.graph;

import com.franz.agraph.jena.AGGraph;
import com.franz.agraph.jena.AGGraphMaker;
import com.franz.agraph.jena.AGModel;
import com.hp.hpl.jena.rdf.model.Resource;
import com.yom.sem.semuy.base.Constant;
import com.yom.sem.semuy.base.ServerBase;
import com.yom.sem.semuy.sparql.UyUniversityQIf;
import com.yom.sem.semuy.sparql.UyUniversityQIfImp;
import com.yom.sem.semuy.vocabulary.UNI;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class UyUniversityGIfImp implements UyUniversityGIf {
    private static AGGraphMaker maker = ServerBase.getMarker("university");

    public boolean addUniversity(String code, String name) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean importUniversities(String file) {
        try {
            FileReader read = new FileReader(file);
            BufferedReader br = new BufferedReader(read);
            String row;

            try {
                while((row = br.readLine())!=null){
                    String[] infos = row.split(" ");
                    String code = infos[0].trim();
                    String name = infos[1].trim();
                    String province = infos[2].trim();
                    String type = infos[3].trim();
                    String attribute = infos[4].trim();

                    AGGraph graph = maker.createGraph(Constant.u_prefix + code);
                    AGModel model = new AGModel(graph);

                    Resource uniRes = model.createResource(Constant.u_prefix + code);
                    Resource provRes = model.createResource(Constant.p_prefix + province);
                    Resource typeRes = model.createResource(Constant.t_prefix + type);
                    Resource attrRes = model.createResource(Constant.a_prefix + attribute);

                    uniRes.addProperty(UNI.uniCode, code)
                            .addProperty(UNI.name, name)
                            .addProperty(UNI.locatedIn, provRes)
                            .addProperty(UNI.type, typeRes)
                            .addProperty(UNI.attribute, attrRes);

                    model.close();
                    graph.close();
                }
            } finally {
                br.close();
                read.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean importUniPlace(String file) {
        try {
            FileReader read = new FileReader(file);
            BufferedReader br = new BufferedReader(read);
            String row;

            AGGraph graph = maker.createGraph(Constant.p_prefix);
            AGModel model = new AGModel(graph);

            try {
                while((row = br.readLine())!=null){
                    String[] infos = row.split(" ");
                    String code = infos[0].trim();
                    String name = infos[1].trim();

                    Resource placeRes = model.createResource(Constant.p_prefix + code);

                    placeRes.addProperty(UNI.name, name);
                }
            } finally {
                model.close();
                graph.close();
                br.close();
                read.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean importUniGrade(String file, String tag) {
        UyUniversityQIf uyQ = new UyUniversityQIfImp();

        try {
            FileReader read = new FileReader(file);
            BufferedReader br = new BufferedReader(read);
            String row;

            AGGraph graph = maker.getUnionOfAllGraphs();
            AGModel model = new AGModel(graph);

            try {
                while ((row = br.readLine())!=null) {
                    ArrayList uriList = uyQ.getUniversitySubject(row.trim());

                    if (uriList.size()>0) {
                        AGGraph g = maker.openGraph(((Map)uriList.get(0)).get("uni").toString());
                        AGModel m = new AGModel(g);

                        Resource res = m.createResource(((Map)uriList.get(0)).get("uni").toString());

                        if (!m.contains(res, UNI.grade, tag))
                            m.add(res, UNI.grade, tag);
                    } else {
                        System.out.println(row.trim() + "doesn't exist!");
                    }
                }
            } finally {
                model.close();
                graph.close();
                br.close();
                read.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean importUniAttr(String file) {
        try {
            FileReader read = new FileReader(file);
            BufferedReader br = new BufferedReader(read);
            String row;

            AGGraph graph = maker.createGraph(Constant.u_prefix);
            AGModel model = new AGModel(graph);

            try {
                while((row = br.readLine())!=null){
                    String[] infos = row.split(" ");
                    String code = infos[0].trim();
                    String name = infos[1].trim();

                    Resource attrRes = model.createResource(Constant.a_prefix + code);

                    attrRes.addProperty(UNI.name, name);
                }
            } finally {
                model.close();
                graph.close();
                br.close();
                read.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean importUniType(String file) {
        try {
            FileReader read = new FileReader(file);
            BufferedReader br = new BufferedReader(read);
            String row;

            AGGraph graph = maker.createGraph(Constant.t_prefix);
            AGModel model = new AGModel(graph);

            try {
                while((row = br.readLine())!=null){
                    String[] infos = row.split(" ");
                    String code = infos[0].trim();
                    String name = infos[1].trim();

                    Resource typeRes = model.createResource(Constant.t_prefix + code);

                    typeRes.addProperty(UNI.name, name);
                }
            } finally {
                model.close();
                graph.close();
                br.close();
                read.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean deleteUniversity(String code) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
