using shared.Models;

namespace dotnet_server.Contracts;

public interface IRentalService
{
    Task<IEnumerable<CarDto>> CreateRentalAsync();
    Task<IEnumerable<CarDto>> GetRentalsAsync();
    Task<IEnumerable<CarDto>> GetRentalAsync(int id);
    Task<IEnumerable<CarDto>> UpdateRentalAsync(int id);
    Task<IEnumerable<CarDto>> DeleteRentalAsync(int id);
}