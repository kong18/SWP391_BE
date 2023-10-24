package com.FPTU.controller;

import com.FPTU.dto.CourseDTO;
import com.FPTU.dto.ItemDTO;
import com.FPTU.exceptions.CourseNotFoundException;
import com.FPTU.exceptions.ItemNotFoundException;
import com.FPTU.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@CrossOrigin("http://127.0.0.1:5173/")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();

    }

    @GetMapping("/{id}")
    public ItemDTO getItemById(@PathVariable("id") Long id) {
        return itemService.getItemById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")// Restrict access to ADMIN role
    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody ItemDTO itemDTO) {
        String message = itemService.save(itemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<String> updateItem(@RequestBody ItemDTO itemDTO) {
        if(!itemService.existsById(itemDTO.getId())) {
            throw new ItemNotFoundException(itemDTO.getId());
        }
        String message = itemService.save(itemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PreAuthorize("hasRole('ADMIN')") // Restrict access to ADMIN role
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id") Long id) {
        if(!itemService.existsById(id)) {
            throw new ItemNotFoundException(id);
        }
        itemService.deleteItemById(id);
        return ResponseEntity.ok("Delete the item with id" + id);

    }

    @GetMapping("/search")
    public List<ItemDTO> searchItems(
            @RequestParam(value = "name", required = false) String name) {
        return itemService.searchItems(name);
    }

}
