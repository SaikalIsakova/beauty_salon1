package kg.megacom.beauty_salon.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.megacom.beauty_salon.models.dto.SalonDto;
import kg.megacom.beauty_salon.models.request.SalonRequest;
import kg.megacom.beauty_salon.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Салон")
@RestController
@RequestMapping("/api/v2/salon")
public class SalonController {

    @Autowired
    SalonService service;

//    @ApiOperation("Создание салона")
//    @PostMapping("/save")
//    ResponseEntity<?>save(@RequestBody SalonDto salonDto) {
//        try {
//            return new ResponseEntity<>(service.save(salonDto), HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//        }
//    }

    @PostMapping("/save")
    @ApiOperation("Сохранение салона")
    ResponseEntity<?>create(@ModelAttribute SalonRequest salonRequest) {
        try {
            return new ResponseEntity<>(service.create(salonRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/find/by/id")
    @ApiOperation("Найти салон по id")
    ResponseEntity<?>findById(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/all")
    @ApiOperation("Вывод списка салонов")
    ResponseEntity<List<SalonDto>>findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление салона")
    ResponseEntity<?>delete(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(service.delete(id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
