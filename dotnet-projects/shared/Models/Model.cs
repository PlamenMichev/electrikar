using shared.Enums;

namespace shared.Models;

public class Model
{
    private List<CarModel> _models;

    public Model(Make make)
    {
        _models = make switch
        {
            Make.Tesla
                => new List<CarModel>
                {
                    CarModel.Tesla_ModelS,
                    CarModel.Tesla_Model3,
                    CarModel.Tesla_ModelX,
                    CarModel.Tesla_ModelY,
                    CarModel.Tesla_Roadster,
                },
            Make.Renault
                => new List<CarModel>
                {
                    CarModel.Renault_Clio,
                    CarModel.Renault_Captur,
                    CarModel.Renault_Megane,
                    CarModel.Renault_Scenic,
                    CarModel.Renault_Talisman,
                },
            Make.Toyota
                => new List<CarModel>
                {
                    CarModel.Toyota_Avalon,
                    CarModel.Toyota_Camry,
                    CarModel.Toyota_Corolla,
                    CarModel.Toyota_Prius,
                    CarModel.Toyota_Yaris,
                },
            Make.Volkswagen
                => new List<CarModel>
                {
                    CarModel.Volkswagen_Arteon,
                    CarModel.Volkswagen_Golf,
                    CarModel.Volkswagen_Jetta,
                    CarModel.Volkswagen_Passat,
                    CarModel.Volkswagen_Polo,
                    CarModel.Volkswagen_Tiguan,
                    CarModel.Volkswagen_Touareg,
                    CarModel.Volkswagen_TouaregR,
                },
            Make.Ford
                => new List<CarModel>
                {
                    CarModel.Ford_Edge,
                    CarModel.Ford_Escape,
                    CarModel.Ford_Explorer,
                    CarModel.Ford_Fiesta,
                    CarModel.Ford_Kuga,
                    CarModel.Ford_Mustang,
                    CarModel.Ford_Ranger,
                },
            _ => throw new ArgumentException("Invalid make"),
        };
    }

    public List<CarModel> Models
    {
        get => _models;
    }
}
