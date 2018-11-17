package io.maxfeng.dubbox.model;

import com.google.common.base.Strings;

import java.io.Serializable;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class RModel implements Serializable {

    private String classPathName;

    private String group;

    private Class<?> clazz;

    private int count = 0;

    public int getCount() {
        return count;
    }

    public void setCount() {
        this.count++;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String var0) {
        if (Strings.isNullOrEmpty(var0)) {

            String[] split = this.classPathName.split("\\.");
            var0 = split[split.length - 1];
            char[] tmp = new char[1];
            tmp[0] = var0.charAt(0);
            String var = new String(tmp);
            String r = var.toLowerCase();
            var0 = var0.replaceFirst(var, r);
            this.group = var0;

        } else {
            this.group = var0;
        }
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

    @Override
    public String toString() {
        return "RModel{" +
                "classPathName='" + classPathName + '\'' +
                ", group='" + group + '\'' +
                ", clazz=" + clazz +
                ", count=" + count +
                '}';
    }
}
