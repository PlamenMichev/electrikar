using shared.Enums;

public class CarPostModel
{
    public required string RegistrationNumber { get; set; }
    public Make Make { get; set; }
    public CarModel Model { get; set; }
    public CarType Type { get; set; }
    public Color Color { get; set; }
    public byte[] ImageByteArr { get; set; } = Array.Empty<byte>();
    public bool ShouldChangeFile { get; set; }
}
