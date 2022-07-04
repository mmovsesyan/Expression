package com.movsisyan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MathResult {
    private String expression;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int result;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MathResult{" +
                "expression='" + expression + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
