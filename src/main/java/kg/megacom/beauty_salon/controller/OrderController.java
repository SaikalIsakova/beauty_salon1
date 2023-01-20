package kg.megacom.beauty_salon.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.megacom.beauty_salon.models.dto.OrderDto;
import kg.megacom.beauty_salon.models.request.OrderRequest;
import kg.megacom.beauty_salon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v2/order")
@Api(tags = "Запись")
public class OrderController {

    @Autowired
    OrderService service;

   @PostMapping("/save")
   @ApiOperation("Сохранение заявки")
    ResponseEntity<?>createOrder(@RequestBody OrderRequest order) throws ParseException {

           return new ResponseEntity<>(service.create(order), HttpStatus.CREATED);
   }

   @PostMapping("/confirm")
   @ApiOperation("Сохранение заявки")
    ResponseEntity<?>confirm(@RequestParam int code) throws ParseException {

           return new ResponseEntity<>(service.confirm(code), HttpStatus.CREATED);
   }


    @GetMapping("/find/by/id")
    @ApiOperation("Найти заявку по id")
    ResponseEntity<?> findById(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(service.findById(id),HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/order/by/masterId")
    @ApiOperation("Найти заявку по id")
    ResponseEntity<?> findOrderByMasterId(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(service.findOrderByMaster_Id(id),HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/all")
    @ApiOperation("Вывод всех заявок")
    ResponseEntity<List<OrderDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление заявки")
    ResponseEntity<?>delete(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(service.delete(id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
