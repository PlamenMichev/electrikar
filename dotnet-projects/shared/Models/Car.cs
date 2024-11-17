using shared.Enums;

public class Car
{
    public required string RegistrationNumber { get; set; }
    public Make Make { get; set; }
    public CarModel Model { get; set; }
    public CarType Type { get; set; }
    public Color Color { get; set; }
    public string? ImageUrl { get; set; }
}
