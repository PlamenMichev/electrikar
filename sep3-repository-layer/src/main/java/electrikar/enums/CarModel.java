package electrikar.enums;

import java.util.Arrays;

public enum CarModel
{
  // Tesla models
  Tesla_ModelS, Tesla_Model3, Tesla_ModelX, Tesla_ModelY, Tesla_Roadster,

  // Renault models
  Renault_Clio, Renault_Captur, Renault_Megane, Renault_Scenic, Renault_Talisman,

  // Toyota models
  Toyota_Avalon, Toyota_Camry, Toyota_Corolla, Toyota_Prius, Toyota_Yaris,

  // Volkswagen models
  Volkswagen_Arteon, Volkswagen_Golf, Volkswagen_Jetta, Volkswagen_Passat, Volkswagen_Polo, Volkswagen_Tiguan, Volkswagen_Touareg, Volkswagen_TouaregR,

  // Ford models
  Ford_Edge, Ford_Escape, Ford_Explorer, Ford_Fiesta, Ford_Kuga, Ford_Mustang, Ford_Ranger;

  public static CarModel valueOf(int value)
  {
    return Arrays.stream(values()).filter(type -> type
            .ordinal() == value)
            .findFirst()
            .orElse(null);

  }

}
