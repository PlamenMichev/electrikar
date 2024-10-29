package electrikar.enums;

import java.util.Arrays;

public enum RentalStatus
{
  PENDING,
  ACTIVE,
  COMPLETED,
  CANCELLED;

  public static RentalStatus valueOf(int value)
  {
    return Arrays.stream(values()).filter(type -> type
            .ordinal() == value)
            .findFirst()
            .orElse(null);
  }

  }
