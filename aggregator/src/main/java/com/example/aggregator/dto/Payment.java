package com.example.aggregator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment implements Payload {
  private long id;
  private long senderId;
  private long amount;
  private String comment;

}
