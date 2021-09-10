package com.tis.mx.application.controller;

import static org.junit.Assert.assertEquals;

import com.tis.mx.application.dto.InitialInvestmentDto;
import com.tis.mx.application.dto.InvestmentYieldDto;
import com.tis.mx.application.service.CompoundInterestCalculator;
import com.tis.mx.application.service.impl.CompoundInterestCalculatorImpl;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class ApplicationControllerTest {

  private ApplicationController controller;
  private InitialInvestmentDto initialInvestment;
  private CompoundInterestCalculator calculator;
  
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
  
  @Test
  public void shouldGenerateTableYield() {
    List<InvestmentYieldDto> tableYield = controller.createTableYield(initialInvestment);
    
    assertEquals(5, tableYield.size());
    
    InvestmentYieldDto firstYear = tableYield.get(0);
    assertEquals(Double.valueOf(5000), firstYear.getInitialInvestment());
    assertEquals(Double.valueOf(3000), firstYear.getYearlyInput());
    assertEquals(Double.valueOf(1679.9999475479126), firstYear.getInvestmentYield());
    assertEquals(Double.valueOf(9679.999947547913), firstYear.getFinalBalance());
  }
  
}
