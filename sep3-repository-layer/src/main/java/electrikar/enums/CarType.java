package electrikar.enums;

import java.util.Arrays;
import java.util.Optional;


  public enum CarType
  {
    SEDAN,
    SUV,
    HATCHBACK,
    CROSSOVER,
    COUPE,
    CONVERTIBLE,
    WAGON,
    MINIVAN;

    /**
     * Get the value of the enum
     * @param value
     * @return
     */
    public static CarType valueOf(int value) {
    return Arrays.stream(values())
        .filter(type -> type.ordinal() == value)
        .findFirst()
        .orElse(null);
    }
  }
