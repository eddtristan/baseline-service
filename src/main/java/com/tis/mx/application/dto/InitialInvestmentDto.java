/* 
* This program is free software: you can redistribute it and/or modify  
* it under the terms of the GNU General Public License as published by  
* the Free Software Foundation, version 3.
*
* This program is distributed in the hope that it will be useful, but 
* WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
* General Public License for more details.
*
* Nombre de archivo: InitialInvestmentDto.java
* Autor: etristan
* Fecha de creación: 7 sep. 2021
*/

package com.tis.mx.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Gets the investment years.
 *
 * @return the investment years
 */
@Getter

/**
 * Sets the investment years.
 *
 * @param investmentYears the new investment years
 */
@Setter

/**
 * Instantiates a new initial investment dto.
 *
 * @param initialInvestment the initial investment
 * @param yearInput the year input
 * @param yearInputIncrement the year input increment
 * @param investmentYield the investment yield
 * @param investmentYears the investment years
 */
@AllArgsConstructor

/**
 * Instantiates a new initial investment dto.
 */
@NoArgsConstructor
public class InitialInvestmentDto {

  /** The initial investment. */
  private Double initialInvestment;
  
  /** The year input. */
  private Double yearlyInput;
  
  /** The year input increment. */
  private Integer yearlyInputIncrement;
  
  /** The investment yield. */
  private Float investmentYield;
  
  /** The investment years. */
  private Integer investmentYears;

}
