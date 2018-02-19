package com.oui.wdi.hp;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CartTest {

  private Cart cart;

  @Test
  public void should_calculate_one_book_price(){
    //Given
    givenCart(buildBook("A"));

    //When
    final BigDecimal price = cart.calculPrice();

    //Then
    assertThat(price).isEqualTo(getExpected(8));
  }

  @Test
  public void should_calculate_price_for_three_diffrent_books(){
    //Given
    givenCart(buildBook("A"),
      buildBook("B"),
      buildBook("C"));

    //When
    final BigDecimal price = cart.calculPrice();

    //Then
    assertThat(price).isEqualTo(getExpected(21.6));
  }

  @Test
  public void should_calculate_price_for_five_different_books(){
    //Given
    givenCart(buildBook("A"),
      buildBook("B"),
      buildBook("C"),
      buildBook("D"),
      buildBook("E"));

    //When
    final BigDecimal price = cart.calculPrice();

    //Then
    assertThat(price).isEqualTo(getExpected(30));
  }

  @Test
  public void should_calculate_price_for_two_identic_books(){
    givenCart(buildBook("A"),
      buildBook("A"));

    //When
    final BigDecimal price = cart.calculPrice();

    //Then
    assertThat(price).isEqualTo(getExpected(16));
  }

  @Test
  public void should_calculate_price_for_books_A_A_B(){
    givenCart(buildBook("A"),
      buildBook("A"),
      buildBook("B"));

    //When
    final BigDecimal price = cart.calculPrice();

    //Then
    assertThat(price).isEqualTo(getExpected(23.2));
  }

  @Test
  public void should_calculate_price_for_books_A_B_C_D_E_A_B(){
    givenCart(buildBook("A"),
            buildBook("B"),
            buildBook("C"),
            buildBook("D"),
            buildBook("E"),
            buildBook("A"),
            buildBook("B"));

    //When
    final BigDecimal price = cart.calculPrice();

    //Then
    assertThat(price).isEqualTo(getExpected(45.2));
  }

  @Test
  public void should_calculate_price_for_books_A_B_C_D_E_A_B_C(){
    givenCart(buildBook("A"),
      buildBook("B"),
      buildBook("C"),
      buildBook("D"),
      buildBook("E"),
      buildBook("A"),
      buildBook("B"),
      buildBook("C"));

    //When
    final BigDecimal price = cart.calculPrice();

    //Then
    assertThat(price).isEqualTo(getExpected(51.2));
  }

  private void givenCart(Book... books){
    cart = new Cart(Arrays.asList(books));
  }

  private Book buildBook(String name){
    return new Book(name);
  }

  private BigDecimal getExpected(double value) {
    return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_EVEN);
  }

}