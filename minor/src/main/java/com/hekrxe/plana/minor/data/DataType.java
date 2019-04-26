package com.hekrxe.plana.minor.data;

import java.util.List;

/**
 * @author tanhuayou on 2019/04/12
 */
public class DataType {
    public static final long STRING_DATA_TYPE_ID = -1;
    public static final long NUMBER_DATA_TYPE_ID = -2;
    public static final long BOOLEAN_DATA_TYPE_ID = -3;

    public static final DataType STRING = new DataType(STRING_DATA_TYPE_ID, "String");
    public static final DataType NUMBER = new DataType(NUMBER_DATA_TYPE_ID, "Number");
    public static final DataType BOOLEAN = new DataType(BOOLEAN_DATA_TYPE_ID, "Boolean");

    private Long id;
    private String name;
    private List<DataType> filed;

    public DataType() {
    }

    public DataType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DataType> getFiled() {
        return filed;
    }

    public void setFiled(List<DataType> filed) {
        this.filed = filed;
    }
}
