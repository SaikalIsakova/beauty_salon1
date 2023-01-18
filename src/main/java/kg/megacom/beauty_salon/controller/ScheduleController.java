package kg.megacom.beauty_salon.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.megacom.beauty_salon.models.dto.ScheduleDto;
import kg.megacom.beauty_salon.models.enums.WorkDayEnum;
import kg.megacom.beauty_salon.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/v2/schedule")
@Api(tags = "График")
public class ScheduleController {

    @Autowired
    ScheduleService service;

    @PostMapping("/save")
    @ApiOperation("Сохранение графика")
    ResponseEntity<?> save(@RequestParam WorkDayEnum workDayEnum,
                           @RequestParam @DateTimeFormat(pattern = "HH:mm:ss") LocalTime startTime,
                           @RequestParam @DateTimeFormat(pattern = "HH:mm:ss")LocalTime endTime)  {
        try {
            return new ResponseEntity<>(service.create(startTime,endTime,workDayEnum), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/find/by/id")
    @ApiOperation("Найти график по id")
    ResponseEntity<?> findById(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/all")
    @ApiOperation("Вывод графика")
    ResponseEntity<List<ScheduleDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }



    @GetMapping("/find/master/schedule")
    ResponseEntity<List<ScheduleDto>> findMasterSchedule(@RequestParam Long masterId) {
        return new ResponseEntity<>(service.findScheduleByMasterId(masterId), HttpStatus.FOUND);
    }

    @GetMapping("/get/schedule")
    ResponseEntity<?> getSchedule(@RequestParam Long masterId) {
        return new ResponseEntity<>(service.scheduleMap(masterId), HttpStatus.FOUND);
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление графика")
    ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(service.delete(id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
