package com.yom.sem.semuy.sparql;

import java.util.ArrayList;

public interface UyUniversityQIf {
    // 查找所有大学 （uni,name,code）
    public ArrayList getAllUniversities();

    // 查找所有符合条件的大学 （211、985、有研究生院等）
    public ArrayList getAllUniversities(String... grades);

    // 根据名称查找大学
    public ArrayList getUniversitySubject(String name);
}
