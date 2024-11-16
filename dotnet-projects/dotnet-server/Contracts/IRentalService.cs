using shared.Models;

namespace dotnet_server.Contracts;

public interface IRentalService
{
    Task<CarDto> CreateRentalAsync(Car car);
    Task<IEnumerable<CarDto>> GetRentalsAsync();
    Task<CarDto> GetRentalAsync(int id);
    Task<CarDto> UpdateRentalAsync(int id, Car car);
    Task DeleteRentalAsync(int id);
}