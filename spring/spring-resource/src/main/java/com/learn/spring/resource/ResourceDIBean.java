package com.learn.spring.resource;

import org.springframework.core.io.Resource;

public class ResourceDIBean {

    private Resource resource;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
