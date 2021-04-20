package com.exemplo.cadastroveiculo.service;

import com.exemplo.cadastroveiculo.model.Veiculo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VeiculoService {

    Gson gson = new Gson();
    private List<Veiculo> veiculos;

    public boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }

    public void createVeiculoList() {
        if (veiculos == null) {
            veiculos = new ArrayList<>();
        }
    }

    public void add(Veiculo veiculo) {
        createVeiculoList();
        veiculos.add(veiculo);
    }

    public Veiculo create(JSONObject jsonTravel) {
        return gson.fromJson(jsonTravel.toString(), Veiculo.class);
    }

    public List<Veiculo> find() {
        createVeiculoList();
        return veiculos;
    }

}
