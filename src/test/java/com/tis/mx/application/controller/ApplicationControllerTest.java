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
* Nombre de archivo: ApplicationControllerTest.java
* Autor: etristan
* Fecha de creación: 10 sep. 2021
*/

package com.tis.mx.application.controller;

import static org.junit.Assert.assertEquals;

import com.tis.mx.application.dto.InitialInvestmentDto;
import com.tis.mx.application.dto.InvestmentYieldDto;
import com.tis.mx.application.service.CompoundInterestCalculator;
import com.tis.mx.application.service.impl.CompoundInterestCalculatorImpl;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

/**
 * The Class ApplicationControllerTest.
 */
public class ApplicationControllerTest {

  /** The controller. */
  private ApplicationController controller;
  
  /** The initial investment. */
  private InitialInvestmentDto initialInvestment;
  
  /** The calculator. */
  private CompoundInterestCalculator calculator;

  /**
   * Creates the values before to test.
   */
  @Before
  public void createValuesBeforeToTest() {
    // Crea una calculadora
    this.calculator = new CompoundInterestCalculatorImpl();
    // Crea controlador con su dependencia de una calculadora
    this.controller = new ApplicationController(this.calculator);

    // Crea los valoder iniciales de la inversion
    this.initialInvestment = new InitialInvestmentDto();
    this.initialInvestment = new InitialInvestmentDto(5000d, 3000d, 1, 21f, 5);

  }

  /**
   * Should generate table yield.
   */
  @Test
  public void shouldGenerateTableYield() {
    List<InvestmentYieldDto> tableYield = controller.createTableYield("application/json",
        initialInvestment);

    assertEquals(5, tableYield.size());

    InvestmentYieldDto firstYear = tableYield.get(0);
    assertEquals(Double.valueOf(5000), firstYear.getInitialInvestment());
    assertEquals(Double.valueOf(3000), firstYear.getYearlyInput());
    assertEquals(Double.valueOf(1679.9999475479126), firstYear.getInvestmentYield());
    assertEquals(Double.valueOf(9679.999947547913), firstYear.getFinalBalance());
  }

}
