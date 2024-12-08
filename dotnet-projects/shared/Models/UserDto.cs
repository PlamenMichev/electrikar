namespace shared.Models;

public class UserDto
{
    public int Id { get; set; }
    public required string LegalName { get; set; }
    public required string Email { get; set; }
    public required string Cpr { get; set; }
    public required string Phone { get; set; }
    public bool IsAdmin { get; set; }
    public bool IsBanned { get; set; }
    public string? Password { get; set; }
}
