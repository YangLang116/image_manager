package com.xtu.plugin.res.manager.store;

import java.util.Objects;

public class StorageEntity {
    public String tinyApiKey = "33w0Q37jD8McmXQYYMH0sPqwdG2vxm70";

    public StorageEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorageEntity that = (StorageEntity) o;
        return Objects.equals(tinyApiKey, that.tinyApiKey);
    }

    @Override
    public String toString() {
        return "StorageEntity{" +
                "tinyApiKey='" + tinyApiKey + '\'' +
                '}';
    }
}
