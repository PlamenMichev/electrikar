using shared.Enums;

namespace shared.Models
{
    public class RentalDto
    {
        public int Id { get; set; }
        public required string CarRegNumber { get; set; }
        public int UserId { get; set; }
        public DateTimeOffset StartDate { get; set; }
        public DateTimeOffset EndDate { get; set; }
        public DateTimeOffset DropDate { get; set; }
        public RentalStatus Status { get; set; }
        public string? CustomerComment { get; set; }
        public string? OrganizerComment { get; set; }
        public UserDto? User { get; set; }
    }
}
