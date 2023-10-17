package com.FPTU.dto;

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
  private Long categoryId;
  private String img;
}