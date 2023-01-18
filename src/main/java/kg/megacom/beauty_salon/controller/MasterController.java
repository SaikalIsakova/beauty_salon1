package kg.megacom.beauty_salon.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.megacom.beauty_salon.mappers.MasterMapper;
import kg.megacom.beauty_salon.models.dto.MasterDto;
import kg.megacom.beauty_salon.models.request.SaveMasterRequest;
import kg.megacom.beauty_salon.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2/master")
@Api(tags = "Мастер")
public class MasterController {

    @Autowired
    MasterService service;

//    @PostMapping("/save")
//    @ApiOperation("Сохранение мастера")
//    ResponseEntity<?>save(@RequestBody MasterDto masterDto) {
//        try {
//            return new ResponseEntity<>(service.save(masterDto), HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//        }
//    }

    @PostMapping("/save")
    @ApiOperation("Сохранение мастера")
    ResponseEntity<?>create(@ModelAttribute SaveMasterRequest masterDto) {
        try {
            return new ResponseEntity<>(service.create(masterDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/find/by/id")
    @ApiOperation("Найти по id")
    ResponseEntity<?>findById(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/all")
    @ApiOperation("Вывод списка мастеров")
    ResponseEntity <List<MasterDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление мастера")
    ResponseEntity<?>delete(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(service.delete(id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find")
    @ApiOperation("График мастера")
   ResponseEntity<List<MasterDto>>findScheduleByMasterId(@RequestParam Long id){
        return ResponseEntity.ok(service.findScheduleByMasterId(id));
    }

    @GetMapping("/find/master/by/salon")
    @ApiOperation("Вывод мастера по id салона")
   ResponseEntity<List<MasterDto>> findMasterBySalon(@RequestParam Long id){
        return new ResponseEntity<>(service.findBySalonId(id),HttpStatus.FOUND);
    }

}
