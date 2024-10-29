using shared.Enums;

public class Car
{
    public string RegistrationNumber { get; set; }
    public Make Make { get; set; }
    public CarModel Model { get; set; }
    public CarType Type { get; set; }
    public Color Color { get; set; }
    public byte[] ImageByteArray { get; set; }
}
