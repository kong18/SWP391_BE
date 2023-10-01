package com.FPTU.controller;

import com.FPTU.dto.ItemDTO;
import com.FPTU.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
  @Autowired
  private ItemService itemService;

  @GetMapping
  public List<ItemDTO> getAllItems() {
    return itemService.getAllItems();

  }


  @PreAuthorize("hasRole('INSTRUCTOR')")// Restrict access to INSTRUCTOR role
  @PostMapping("/addItem")
  public ResponseEntity<ItemDTO> addItem(@RequestBody ItemDTO itemDTO) {
    itemDTO = itemService.save(itemDTO);
    return ResponseEntity.ok(itemDTO);
  }
  @PreAuthorize("hasRole('INSTRUCTOR')")
  @PutMapping("detail/{id}")
  public ResponseEntity<ItemDTO> updateItem(@RequestBody ItemDTO itemDTO, @PathVariable("id") Long id) {
    itemDTO.setId(id);
    return ResponseEntity.ok(itemDTO);
  }

  @PreAuthorize("hasRole('INSTRUCTOR')") // Restrict access to INSTRUCTOR role
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable("id") Long id) {
    if (itemService.deleteItemById(id)) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
  @GetMapping("/search")
  public List<ItemDTO> searchItems(
      @RequestParam(value = "name", required = false) String name)
       {
    return itemService.searchItems(name);
  }

}
