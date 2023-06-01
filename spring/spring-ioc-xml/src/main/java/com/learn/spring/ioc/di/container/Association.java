package com.learn.spring.ioc.di.container;

import java.util.List;
import java.util.Map;

public class Association {
    private Map<User, User> association;
    private List<User> container;

    @Override
    public String toString() {
        return "Association{" +
                "association=" + association +
                ", container=" + container +
                '}';
    }

    public void setContainer(List<User> container) {
        this.container = container;
    }

    public Map<User, User> getAssociation() {
        return association;
    }

    public void setAssociation(Map<User, User> association) {
        this.association = association;
    }
}
