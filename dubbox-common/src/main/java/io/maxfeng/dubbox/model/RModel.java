package io.maxfeng.dubbox.model;

import java.io.Serializable;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class RModel implements Serializable {

    private String classPathName;

    private String group;

    private Class<?> clazz;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getClassPathName() {
        return classPathName;
    }

    public void setClassPathName(String classPathName) {

        this.classPathName = classPathName;
    }

}
