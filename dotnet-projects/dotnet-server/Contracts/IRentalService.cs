using shared.Models;

namespace dotnet_server.Contracts;

public interface IRentalService
{
    Task<RentalDto> CreateRentalAsync(RentalDto rental);
    Task<IEnumerable<RentalDto>> GetRentalsAsync();
    Task<RentalDto> GetRentalAsync(int id);
    Task<RentalDto> UpdateRentalAsync(int id, RentalDto rentalDto);
    Task DeleteRentalAsync(int id);
}