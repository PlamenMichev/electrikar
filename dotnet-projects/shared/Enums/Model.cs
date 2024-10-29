namespace shared.Enums;

public static class ModelsDisplayNames
{
    public static string GetDisplayName(CarModel model) =>
        model switch
        {
            CarModel.Tesla_ModelS => "Model S",
            CarModel.Tesla_Model3 => "Model 3",
            CarModel.Tesla_ModelX => "Model X",
            CarModel.Tesla_ModelY => "Model Y",
            CarModel.Tesla_Roadster => "Roadster",
            CarModel.Renault_Clio => "Clio",
            CarModel.Renault_Captur => "Captur",
            CarModel.Renault_Megane => "Megane",
            CarModel.Renault_Scenic => "Scenic",
            CarModel.Renault_Talisman => "Talisman",
            CarModel.Toyota_Avalon => "Avalon",
            CarModel.Toyota_Camry => "Camry",
            CarModel.Toyota_Corolla => "Corolla",
            CarModel.Toyota_Prius => "Prius",
            CarModel.Toyota_Yaris => "Yaris",
            CarModel.Volkswagen_Arteon => "Arteon",
            CarModel.Volkswagen_Golf => "Golf",
            CarModel.Volkswagen_Jetta => "Jetta",
            CarModel.Volkswagen_Passat => "Passat",
            CarModel.Volkswagen_Polo => "Polo",
            CarModel.Volkswagen_Tiguan => "Tiguan",
            CarModel.Volkswagen_Touareg => "Touareg",
            CarModel.Volkswagen_TouaregR => "Touareg R",
            CarModel.Ford_Edge => "Edge",
            CarModel.Ford_Escape => "Escape",
            CarModel.Ford_Explorer => "Explorer",
            CarModel.Ford_Fiesta => "Fiesta",
            CarModel.Ford_Kuga => "Kuga",
            CarModel.Ford_Mustang => "Mustang",
            CarModel.Ford_Ranger => "Ranger",
            _ => throw new ArgumentException("Invalid model"),
        };
}

public enum CarModel
{
    // Tesla models
    Tesla_ModelS = 0,
    Tesla_Model3,
    Tesla_ModelX,
    Tesla_ModelY,
    Tesla_Roadster,

    // Renault models
    Renault_Clio,
    Renault_Captur,
    Renault_Megane,
    Renault_Scenic,
    Renault_Talisman,

    // Toyota models
    Toyota_Avalon,
    Toyota_Camry,
    Toyota_Corolla,
    Toyota_Prius,
    Toyota_Yaris,

    // Volkswagen models
    Volkswagen_Arteon,
    Volkswagen_Golf,
    Volkswagen_Jetta,
    Volkswagen_Passat,
    Volkswagen_Polo,
    Volkswagen_Tiguan,
    Volkswagen_Touareg,
    Volkswagen_TouaregR,

    // Ford models
    Ford_Edge,
    Ford_Escape,
    Ford_Explorer,
    Ford_Fiesta,
    Ford_Kuga,
    Ford_Mustang,
    Ford_Ranger,
}
