package com.sporty.api.core.model;

import java.math.BigDecimal;

public enum BookType {
  NEW_RELEASES {
    @Override
    public BigDecimal applyDiscount(BigDecimal basePrice, boolean isBundle) {
      return basePrice; // 100% price
    }
  },

  REGULAR {
    @Override
    public BigDecimal applyDiscount(BigDecimal basePrice, boolean isBundle) {
      return isBundle ? basePrice.multiply(BigDecimal.valueOf(0.90)) : basePrice;
    }
  },

  OLD_EDITIONS {
    @Override
    public BigDecimal applyDiscount(BigDecimal basePrice, boolean isBundle) {
      BigDecimal priceWithFirstDiscount = basePrice.multiply(BigDecimal.valueOf(0.80));
      return isBundle
          ? priceWithFirstDiscount.multiply(BigDecimal.valueOf(0.95))
          : priceWithFirstDiscount;
    }
  };

  public static final String UNSUPPORTED_BOOK_TYPE = "Unsupported book type: ";

  public abstract BigDecimal applyDiscount(BigDecimal basePrice, boolean isBundle);

  public static BookType fromString(String value) {
    // TODO: Replace this switch with pattern matching when upgrading to Java 21+
    // Example with pattern matching for switch (not yet stable in Java 17)
    //        return switch (value) {
    //            case String s when s.equalsIgnoreCase("new releases") -> NEW_RELEASES;
    //            case String s when s.equalsIgnoreCase("regular") -> REGULAR;
    //            case String s when s.equalsIgnoreCase("old editions") -> OLD_EDITIONS;
    //            default -> throw new IllegalArgumentException("Unsupported book type: " + value);
    //        };
    return switch (value.toLowerCase()) {
      case "new releases" -> NEW_RELEASES;
      case "regular" -> REGULAR;
      case "old editions" -> OLD_EDITIONS;
      default -> throw new IllegalArgumentException(UNSUPPORTED_BOOK_TYPE + value);
    };
  }
}
