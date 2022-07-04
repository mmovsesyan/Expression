package com.movsisyan.program;

import com.movsisyan.exceptions.ExpressionFormatValidException;
import com.movsisyan.repository.MathRepository;

import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        try {
            MathRepository repository = new MathRepository("expression.json");
            System.out.println(repository);
            repository.toJSON("expressions1.json");
        } catch (RuntimeException | IOException | ExpressionFormatValidException e) {
            System.out.println(e.getMessage());
        }
    }
}
