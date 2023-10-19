package com.FPTU.dto;

import com.FPTU.model.ItemCategory;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
  private Long id;
  private String name;
  private String description;
  private Long price;
  private ItemCategoryDTO category;
  private String img;
}