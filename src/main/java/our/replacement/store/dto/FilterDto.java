package our.replacement.store.dto;

import java.sql.JDBCType;
import java.sql.SQLType;

public class FilterDto {

    public enum Operation {
        ILIKE, EQUAL
    }

    private String name;
    private String value;
    private Operation operation;

    private int sqlType;

    public FilterDto() {
    }

    public FilterDto(String name, Operation operation, int sqlType) {
        this.name = name;
        this.operation = operation;
        this.sqlType = sqlType;
    }

    public FilterDto(String name, String value, Operation operation, int sqlType) {
        this.name = name;
        this.value = value;
        this.operation = operation;
        this.sqlType = sqlType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public int getSqlType() {
        return sqlType;
    }

    public void setSqlType(int sqlType) {
        this.sqlType = sqlType;
    }
}
