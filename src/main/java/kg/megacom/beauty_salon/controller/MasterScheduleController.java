package kg.megacom.beauty_salon.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.megacom.beauty_salon.models.dto.ClientDto;
import kg.megacom.beauty_salon.response.Response;
import kg.megacom.beauty_salon.service.MasterScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/master-schedule")
@Api(tags = "График мастеров")
public class MasterScheduleController {
    @Autowired
    MasterScheduleService service;

    @PostMapping("/save")
    @ApiOperation("Сохранение")

    ResponseEntity<?> create(@RequestParam Long masterId, @RequestParam List<Long>scheduleIds) {
        try {
            return ResponseEntity.ok(service.create(masterId,scheduleIds));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
