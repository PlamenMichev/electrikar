namespace shared.Models;

public class CreateUserModel
{
    public required string LegalName { get; set; }
    public required string Email { get; set; }
    public required string Password { get; set; }
    public required string Cpr { get; set; }
    public required string Phone { get; set; }
}
