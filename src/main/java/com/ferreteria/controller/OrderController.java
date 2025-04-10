package com.ferreteria.controller;

import com.ferreteria.controller.dto.OrderDTO;
import com.ferreteria.services.interfaces.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    //Crear orden
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody  OrderDTO orderDTO) {

        return new ResponseEntity<>(this.orderService.createOrder(orderDTO), HttpStatus.CREATED);
    }

    //Obtener todas las ordenes
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {

        return new ResponseEntity<>(this.orderService.getAllOrders(), HttpStatus.OK);
    }

    //Obtener orden por id
    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {

        return new ResponseEntity<>(this.orderService.getOrderById(id), HttpStatus.OK);
    }

    //Obtener ordenes por empleado
    @GetMapping("/employee/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    public ResponseEntity<List<OrderDTO>> getOrdersByEmployee(@PathVariable Long id) {

        return new ResponseEntity<>(this.orderService.getOrdersByEmployee(id), HttpStatus.OK);
    }

    //Obtener ordenes del dia
    @GetMapping("/today")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<OrderDTO>> getOrdersByDay() {

        return new ResponseEntity<>(this.orderService.getOrdersByDay(), HttpStatus.OK);
    }

    //Obtener ordenes mensuales
    @GetMapping("/monthly")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<OrderDTO>> getOrdersByMonth() {

        return new ResponseEntity<>(this.orderService.getOrdersByMonth(), HttpStatus.OK);
    }

    @GetMapping("/test")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public String test() {
        try {
            boolean condition = true;
            while (condition) {
                Runnable r = () -> {
                    while (true) {

                    }
                };
                new Thread(r).start();
                Thread.sleep(5000);
            }
        }catch (Exception e){}
        return "Hello World";
    }
}
