package ru.process.thymeleaf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "process")
@Data
public class Process {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "title")
  private String title;

  @Column(name = "date_add")
  private String dateAdd;

  @ManyToOne
  @JoinColumn(name = "agent_id")
  private Agent agent;

  @ManyToOne
  @JoinColumn(name = "place_id")
  private Place place;
}

