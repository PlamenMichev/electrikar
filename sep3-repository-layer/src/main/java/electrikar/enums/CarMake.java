package electrikar.enums;

import java.util.Arrays;

public enum CarMake
{
  Tesla,
  Renault,
  Toyota,
  Volkswagen,
  Ford;

  /**
   * Get the value of the enum
   * @return
   */
  public static CarMake valueOf(int value)
  {
    return Arrays.stream(values()).filter(type -> type
            .ordinal() == value)
            .findFirst()
            .orElse(null);
  }
}
