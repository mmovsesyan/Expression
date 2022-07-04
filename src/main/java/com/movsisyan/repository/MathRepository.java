package com.movsisyan.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movsisyan.exceptions.ExpressionFormatValidException;
import com.movsisyan.model.MathResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MathRepository {
    private ArrayList<MathResult> results = new ArrayList<>();

    public MathRepository(String fileName) throws IOException, ExpressionFormatValidException {
        ObjectMapper mapper = new ObjectMapper();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            this.results = mapper.readValue(reader, new TypeReference<>() {
            });

        }
        for (MathResult result : results) {
            result.setResult(this.getResult(result.getExpression()));
        }
    }

    private int getResult(String expression) throws ExpressionFormatValidException {
        if (!validString(expression)) {
            throw new ExpressionFormatValidException("expression is not valid");
        }
        String[] split = expression.split("[^0-9]");
        String[] split1 = expression.split("[^+-]");
        ArrayList<String> numbers = new ArrayList<>();
        for (String s : split) {
            if (!s.isEmpty()) {
                numbers.add(s);
            }
        }
        ArrayList<Character> operators = new ArrayList<>();
        for (String s : split1) {
            if (!s.isEmpty()) {
                operators.add(s.charAt(0));
            }
        }
        int result1 = 0;
        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i).equals('+')) {
                result1 += Integer.parseInt(numbers.get(i));
            } else if (operators.get(i).equals('-')) {
                result1 -= Integer.parseInt(numbers.get(i));
            }
        }
        return result1;
    }


    private boolean validString(String expression) {
        String[] split = expression.split("[^0-9]");
        String[] split1 = expression.split("[^+-]");
        ArrayList<String> numbers = new ArrayList<>();
        for (String s : split) {
            if (!s.isEmpty()) {
                numbers.add(s);
            }
        }
        ArrayList<Character> operators = new ArrayList<>();
        for (String s : split1) {
            if (!s.isEmpty()) {
                operators.add(s.charAt(0));
            }
        }
        int length = numbers.size() - operators.size();
        return length == 0 || length == 1;
    }

    public void toJSON(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(fileName), results);
    }

    @Override
    public String toString() {
        return "MathRepository{" +
                "results=" + results +
                '}';
    }
}
