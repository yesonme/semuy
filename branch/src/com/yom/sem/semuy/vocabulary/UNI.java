package com.yom.sem.semuy.vocabulary;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.yom.sem.semuy.base.Constant;

public class UNI {
    private static final String NS = Constant.VOB_NS;
    private static Model m_model = ModelFactory.createDefaultModel();

    public static String getURI() {
        return NS;
    }

    // 学校代码 (DataProperty)
    public static final Property uniCode = m_model.createProperty(NS, "uniCode");

    // 学校名称 (DataProperty)
    public static final Property name = m_model.createProperty(NS, "name");

    // 办学类型 (ObjectProperty)
    public static final Property type = m_model.createProperty(NS, "type");

    // 性质类别 (ObjectProperty)
    public static final Property attribute = m_model.createProperty(NS, "attribute");

    // 学校举办者类别 (ObjectProperty)
    public static final Property holder = m_model.createProperty(NS, "holder");

    // 学校主页 (ObjectProperty)
    public static final Property homepage = m_model.createProperty(NS, "homepage");

    // 创立时间 (DataProperty)
    public static final Property establishedDate = m_model.createProperty(NS, "establishedDate");

    // 等级：211, 985, 研究生院 (DataProperty)
    public static final Property grade = m_model.createProperty(NS, "grade");

    // 二级学院 (ObjectProperty)
    public static final Property hasCollege = m_model.createProperty(NS, "hasCollege");

    // 专业 (ObjectProperty)
    public static final Property hasMajor = m_model.createProperty(NS, "hasMajor");

    // 地址 (ObjectProperty)
    public static final Property address = m_model.createProperty(NS, "address");

    // 省市 (ObjectProperty)
    public static final Property locatedIn = m_model.createProperty(NS, "locatedIn");

    // 最少分 (DataProperty)
    public static final Property minScore = m_model.createProperty(NS, "minScore");

    // 最高分 (DataProperty)
    public static final Property maxScore = m_model.createProperty(NS, "maxScore");

    // 平均分 (DataProperty)
    public static final Property avgScore = m_model.createProperty(NS, "avgScore");

    // 专业
    public static final Property hasSpecialty = m_model.createProperty(NS, "hasSpecialty");

    // 学科
    public static final Property hasSubject = m_model.createProperty(NS, "hasSubject");

    // 门类
    public static final Property hasCategory = m_model.createProperty(NS, "hasCategory");

    // 代码
    public static final Property code = m_model.createProperty(NS, "code");
}
