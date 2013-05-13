package com.yom.sem.semuy.test;

import com.franz.agraph.jena.*;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.*;
import com.yom.sem.semuy.base.Namespace;
import com.yom.sem.semuy.base.ServerBase;
import com.yom.sem.semuy.graph.*;
import com.yom.sem.semuy.sparql.UyPlaceQIf;
import com.yom.sem.semuy.sparql.UyPlaceQIfImp;
import com.yom.sem.semuy.sparql.UyUniversityQIf;
import com.yom.sem.semuy.sparql.UyUniversityQIfImp;

public class Test {
    public static void main(String[] args) {
        ServerBase server = new ServerBase();
        server.init();

        // 导入大学
        UyUniversityGIf uyUniversityGIf = new UyUniversityGIfImp();
//        System.out.println(uyUniversityGIf.importUniversities("/home/yesonme/uni.txt"));

        // 导入学校性质
//        System.out.println(uyUniversityGIf.importUniAttr("/home/yesonme/attr.txt"));

        // 导入大学所在地
//        System.out.println(uyUniversityGIf.importUniPlace("/home/yesonme/place.txt"));

        // 导入985,211大学
//        System.out.println(uyUniversityGIf.importUniGrade("/home/yesonme/985.txt", "985"));
//        System.out.println(uyUniversityGIf.importUniGrade("/home/yesonme/211.txt", "211"));

        // 导入办学类型
//        System.out.println(uyUniversityGIf.importUniType("/home/yesonme/type.txt"));

        UySpecialtyGIf uySpecialtyGIf = new UySpecailtyGIfImp();
        System.out.println(uySpecialtyGIf.importCategory4M("/home/yesonme/master/category.txt"));
        System.out.println(uySpecialtyGIf.importSubject4M("/home/yesonme/master/subject.txt"));
        System.out.println(uySpecialtyGIf.importSpecialty4M("/home/yesonme/master/specialty.txt"));

        // 查询大学信息
        UyUniversityQIf qIf = new UyUniversityQIfImp();
//        System.out.println(qIf.getAllUniversities("211", "985"));
//        System.out.println(qIf.getAllUniversities());
//        System.out.println(qIf.getUniDetails("http://www.semuy.com/university/10001"));

        // 显示地址
        UyPlaceQIf place = new UyPlaceQIfImp();
//        System.out.println(place.getPlaceDetails("http://www.semuy.com/province/11"));
    }
}
