using shared.Models;

namespace dotnet_server.Contracts;

public interface IUsersService
{
    Task<IEnumerable<UserDto>> GetUsersAsync();
    Task<UserDto> GetUserAsync(int id);
    Task<UserDto> CreateUserAsync(CreateUserModel user);
    Task<UserDto> UpdateUserAsync(UserDto user);
    Task DeleteUserAsync(int id);
}
