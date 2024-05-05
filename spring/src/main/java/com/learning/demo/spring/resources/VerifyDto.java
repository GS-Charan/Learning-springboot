package com.learning.demo.spring.resources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public
class VerifyDto {
  private String username;
  private String password;
  
}