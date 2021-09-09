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
* Nombre de archivo: ApplicationController.java
* Autor: etristan
* Fecha de creación: 7 sep. 2021
*/


package com.tis.mx.application.controller;

import com.tis.mx.application.dto.InitialInvestmentDto;
import com.tis.mx.application.dto.InvestmentYieldDto;
import com.tis.mx.application.service.impl.CompoundInterestCalculatorImpl;

/**
 * The Class ApplicationController.
 */
public class ApplicationController {

  /** The calculator. */
  private static CompoundInterestCalculatorImpl calculator = new CompoundInterestCalculatorImpl();

  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    InitialInvestmentDto initialInvestmentDto = 
        new InitialInvestmentDto(5000d, 3000d, 1, 21f, 5);
    Double initialInvestment = initialInvestmentDto.getInitialInvestment();
    Double yearInputs = 0d;
    Double finalAmount;
    Double gain = 0d;
    Integer yearInvestment = 1;
    
    if (calculator.validateInput(initialInvestmentDto)) {
      for (InvestmentYieldDto investmentYieldDto : 
          calculator.createRevenueGrid(initialInvestmentDto)) {
        System.out.println("Año = " + yearInvestment
                            + " Saldo inicial = $" + investmentYieldDto.getInitialInvestment()
                            + " Aportación = $" + investmentYieldDto.getYearInput()
                            + " Rendimiento = $" + investmentYieldDto.getInvestmentYield() 
                            + " Saldo final = $" + investmentYieldDto.getFinalBalance());
        yearInputs += investmentYieldDto.getYearInput();
        gain = investmentYieldDto.getFinalBalance();
        yearInvestment++;
      }
      finalAmount = gain - initialInvestment - yearInputs;
      System.out.println("Ganancia por inversión: $" + finalAmount);
      System.out.println("Monto final: $" + gain);
    } else {
      System.out.println("No es posible procesar su solicitud con los datos proporcionados");
    }
    

  }

}
