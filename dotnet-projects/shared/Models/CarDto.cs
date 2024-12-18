using shared.Enums;

namespace shared.Models;

public class CarDto
{
    public required string RegistrationNumber { get; set; }
    public Make Make { get; set; }
    public CarModel Model { get; set; }
    public CarType Type { get; set; }
    public Color Color { get; set; }
    public string? ImageUrl { get; set; }
    public bool HasRentals { get; set; }
}
