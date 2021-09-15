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
* Nombre de archivo: CompoundInterestCalculatorImpl.java
* Autor: etristan
* Fecha de creación: 15 sep. 2021
*/


package com.tis.mx.application.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tis.mx.application.dto.InitialInvestmentDto;
import com.tis.mx.application.dto.InvestmentYieldDto;
import com.tis.mx.application.service.CompoundInterestCalculator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * The Class CompoundInterestCalculatorImpl.
 */
@Service
public class CompoundInterestCalculatorImpl implements CompoundInterestCalculator {

  /**
   * Creates the revenue grid.
   *
   * @param initialInvestment the initial investment
   * @return the list
   */
  @Override
  @HystrixCommand(commandKey = "createRevenueGrid", fallbackMethod = "falbackRevenueGrid")
  public List<InvestmentYieldDto> createRevenueGrid(InitialInvestmentDto initialInvestment) {
    Double initialInvest = initialInvestment.getInitialInvestment();
    Double yearInput = initialInvestment.getYearlyInput();
    Integer yearInputIncrement = initialInvestment.getYearlyInputIncrement();
    Integer investmentYears = initialInvestment.getInvestmentYears();
    Float investmentYield = initialInvestment.getInvestmentYield();
    Double finalBalance;
    Double yieldInvest;
    List<InvestmentYieldDto> investmentList = new ArrayList<>();
    for (int i = 0; i < investmentYears; i++) {
      yieldInvest = ((initialInvest + yearInput) * (investmentYield / 100));
      finalBalance = initialInvest + yearInput + yieldInvest;
      investmentList
          .add(new InvestmentYieldDto(i + 1, initialInvest, yearInput, yieldInvest, finalBalance));

      yearInput = (yearInput) * (1 + (yearInputIncrement / 100d));
      initialInvest = finalBalance;
    }
    return investmentList;
  }

  /**
   * Falback revenue grid.
   *
   * @param initialInvestmentDto the initial investment dto
   * @return the list
   */
  public List<InvestmentYieldDto> falbackRevenueGrid(InitialInvestmentDto initialInvestmentDto) {
    return null;
  }

  /**
   * Validate input.
   *
   * @param input the input
   * @return true, if successful
   */
  @Override
  public boolean validateInput(InitialInvestmentDto input) {

    this.setDefaults(input);

    return (input.getInitialInvestment() >= 1000 && input.getYearlyInput() >= 0
        && input.getYearlyInputIncrement() >= 0 && input.getInvestmentYears() > 0
        && input.getInvestmentYield() > 0);
  }

  /**
   * Sets the defaults.
   *
   * @param input the new defaults
   */
  private void setDefaults(InitialInvestmentDto input) {
    Double yearInput = input.getYearlyInput();
    Integer yearInputIncrement = input.getYearlyInputIncrement();

    yearInput = yearInput != null ? yearInput : 0;
    yearInputIncrement = yearInputIncrement != null ? yearInputIncrement : 0;

    input.setYearlyInput(yearInput);
    input.setYearlyInputIncrement(yearInputIncrement);
  }

}
