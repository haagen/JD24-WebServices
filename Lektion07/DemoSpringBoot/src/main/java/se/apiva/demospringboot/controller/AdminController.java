package se.apiva.demospringboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/test")
public class AdminController {

    @GetMapping
    public ResponseEntity<?> adminTest() {

        return ResponseEntity.ok().build();
    }
}
