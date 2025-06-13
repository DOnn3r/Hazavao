package com.example.demo.endpoint.rest.controller;

import com.example.demo.endpoint.rest.service.HazavaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class HazavaoController {

  @Autowired private HazavaoService hazavaoService;

  @GetMapping("/hazavao")
  public ResponseEntity<String> hazavao(@RequestParam String teny) {
    if (teny == null || teny.isBlank()) {
      return ResponseEntity.badRequest().body("Paramètre 'teny' manquant ou vide.");
    }

    try {
      String result = hazavaoService.getDefinition(teny);
      return ResponseEntity.ok(result);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Erreur lors de la génération : " + e.getMessage());
    }
  }
}
