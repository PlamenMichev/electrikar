using shared.Models;

namespace blazor_client.Contacts;

public interface IApiService
{
    Task<string?> AddCarAsync(CarPostModel car);
    Task<string?> AddRentalAsync(RentalDto rental);
    Task<string?> EditRentalAsync(int id, RentalDto rental);
    Task<CarDto[]?> GetAllCarsAsync();
    Task<RentalDto[]?> GetAllRentalsAsync();
    Task<RentalDto?> GetRentalByIdAsync(int rentalId);
    Task<CarDto?> GetCarByRegNumberAsync(int regNumber);
    Task<string?> UpdateCarAsync(int regNumber, CarPostModel car);
    Task<string?> UpdateRentalAsync(int id, RentalDto rental);
    Task<string?> DeleteRentalAsync(int rentalId);
    Task<string?> DeleteCarAsync(int carId);
    Task<IEnumerable<UserDto>> GetUsersAsync();
    Task<TokenResponse?> LoginAsync(LoginModel login);
}
