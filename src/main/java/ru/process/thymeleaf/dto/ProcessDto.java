package ru.process.thymeleaf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessDto {
  private Integer id;

  private String title;

  private String dateAdd;
  private Integer agentId;
  private Integer placeId;
}

