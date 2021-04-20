package com.exemplo.cadastroveiculo.controller;

import com.exemplo.cadastroveiculo.model.Veiculo;
import com.exemplo.cadastroveiculo.service.VeiculoService;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private static final Logger logger = Logger.getLogger(VeiculoController.class);

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<List<Veiculo>> find() {
        if (veiculoService.find().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        logger.info(veiculoService.find());
        return ResponseEntity.ok(veiculoService.find());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Veiculo> create(@RequestBody JSONObject veiculo) {
        try {
            if (veiculoService.isJSONValid(veiculo.toString())) {
                Veiculo tripCreated = veiculoService.create(veiculo);
                var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path(tripCreated.toString()).build().toUri();

                veiculoService.add(tripCreated);
                return ResponseEntity.created(uri).body(null);

            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            logger.error("JSON fields are not parsable. " + e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }
}
