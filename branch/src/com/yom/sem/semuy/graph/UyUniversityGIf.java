package com.yom.sem.semuy.graph;

import java.io.File;

public interface UyUniversityGIf {
    // 增加大学及其代码
    public boolean addUniversity(String code, String name);

    // 导入大学及其代码
    public boolean importUniversities(String file);

    // 导入大学所在地
    public boolean importUniPlace(String file);

    // 导入大学级别（211,985）
    public boolean importUniGrade(String file, String tag);

    // 导入大学性质
    public boolean importUniAttr(String file);

    // 导入办学类型
    public boolean importUniType(String file);

    // 删除大学
    public boolean deleteUniversity(String code);
}
