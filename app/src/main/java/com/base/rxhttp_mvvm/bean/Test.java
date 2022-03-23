package com.base.rxhttp_mvvm.bean;

/**
 * @author anhuang
 * @date 2022/3/23
 * Description :
 */
public class Test {
    public String category;
    public String icon;
    public int id;
    public String link;
    public String name;
    public int order;
    public int visible;

    @Override
    public String toString() {
        return "Test{" +
                "category='" + category + '\'' +
                ", icon='" + icon + '\'' +
                ", id=" + id +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                ", order=" + order +
                ", visible=" + visible +
                '}';
    }
}
