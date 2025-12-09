package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ErrorResponse;
import com.example.demo.dto.RequestCadena;
import com.example.demo.service.SortService;

@RestController
@RequestMapping("/api")
public class SortController {
    @Autowired
    SortService service;

    @PostMapping("/burbuja")
    public ResponseEntity<?> bubbleSort(@RequestBody RequestCadena request) {

        try {
            if (request == null || request.getCadena().isBlank()) {
                ErrorResponse error = new ErrorResponse();
                error.setError("La peticion es incorrecta");
                error.setDetail("Necesito que llenes los datos");
                return ResponseEntity.badRequest().build();
            }

            String[] partes = request.getCadena().split(",");
            int[] numeros = new int[partes.length];
            for (int i = 0; i < partes.length; i++) {
                numeros[i] = Integer.parseInt(partes[i]);
            }

            return ResponseEntity.ok(service.sort(numeros));
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse();
            error.setError("La peticion es incorrecta");
            error.setDetail(e.getMessage());
            return ResponseEntity.internalServerError().body(error);

        }
    }

    @PostMapping("/insertion")
    public int[] insertionSort(@RequestBody RequestCadena request) {

        String[] partes = request.getCadena().split(",");
        int[] numeros = new int[partes.length];
        for (int i = 0; i < partes.length; i++) {
            numeros[i] = Integer.parseInt(partes[i]);
        }

        return service.insertionSort(numeros);
    }

    @PostMapping("/selection")
    public int[] selectionSort(@RequestBody RequestCadena request) {

        String[] partes = request.getCadena().split(",");
        int[] numeros = new int[partes.length];
        for (int i = 0; i < partes.length; i++) {
            numeros[i] = Integer.parseInt(partes[i]);
        }

        return service.selectionSort(numeros);
    }
}