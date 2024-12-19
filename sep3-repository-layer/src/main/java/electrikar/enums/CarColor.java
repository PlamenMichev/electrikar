package electrikar.enums;

import java.util.Arrays;

public enum CarColor
{
  Red,
  Green,
  Blue,
  Yellow,
  Black,
  White,
  Orange,
  Silver,
  Gold,
  Purple,
  Pink,
  Brown,
  Gray,
  Cyan,
  Magenta,
  Lime;

  /**
   * Get the value of the enum
   * @return
   */
  public static CarColor valueOf(int value)
  {
    return Arrays.stream(values()).filter(type -> type
            .ordinal() == value)
            .findFirst()
            .orElse(null);
  }
}

