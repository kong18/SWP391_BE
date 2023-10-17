package com.FPTU.controller;

import com.FPTU.converter.ItemConverter;
import com.FPTU.dto.CourseDTO;
import com.FPTU.dto.ItemDTO;
import com.FPTU.exceptions.CourseNotFoundException;
import com.FPTU.model.Item;
import com.FPTU.repository.ItemRepository;
import com.FPTU.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemConverter itemConverter;
    @Autowired
    private ItemRepository itemRepository;
    @GetMapping
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();

    }
    @GetMapping("/{id}")
    public ItemDTO getItemById(@PathVariable("id") Long id) {

        return itemService.getItemById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ItemDTO> addItem(@RequestBody ItemDTO itemDTO) {
        itemDTO = itemService.save(itemDTO);
        return ResponseEntity.ok(itemDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable("id") Long id, @RequestBody ItemDTO itemDTO) {
        // Check if the item with the given ID exists
        Optional<Item> existingItemOptional = itemRepository.findById(id);
        if (!existingItemOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Map the updated data from the DTO to the existing item
        Item existingItem = existingItemOptional.get();
        existingItem = itemConverter.toEntity(itemDTO, existingItem);

        // Save the updated item
        existingItem = itemRepository.save(existingItem);

        // Convert and return the updated item as a DTO
        ItemDTO updatedItemDTO = itemConverter.toDTO(existingItem);
        return ResponseEntity.ok(updatedItemDTO);
    }


    @PreAuthorize("hasRole('ADMIN')") // Restrict access to ADMIN role
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") Long id) {
        if (itemService.deleteItemById(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemDTO>> searchItems(@RequestParam(value = "name", required = false) String name) {
        List<ItemDTO> matchingItems = itemService.searchItems(name);
        return ResponseEntity.ok(matchingItems);
    }

}
