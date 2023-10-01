package com.FPTU.converter;

import com.FPTU.dto.ItemDTO;
import com.FPTU.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemConverter {
  public Item toEntity(ItemDTO itemDTO){
    Item item = new Item();
    item.setName(itemDTO.getName());
    item.setDescription(itemDTO.getDescription());
    item.setPrice(itemDTO.getPrice());
    return item;
  }
  public ItemDTO toDTO(Item item){
    ItemDTO itemDTO = new ItemDTO();
    itemDTO.setId(item.getItemId());
    itemDTO.setName(item.getName());
    itemDTO.setDescription(item.getDescription());
    itemDTO.setPrice(item.getPrice());
    itemDTO.setCategoryId(item.getItemCategory().getCategoryId());
    return itemDTO;
  }

  public Item toEntity(ItemDTO itemDTO,Item item){
    item.setName(item.getName());
    item.setDescription(itemDTO.getDescription());
    item.setPrice(itemDTO.getPrice());
    return item;
  }

}
