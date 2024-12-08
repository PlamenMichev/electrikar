using dotnet_server.Contracts;
using dotnet_server.grpc;
using RepositoryGrpcService;
using shared.Models;

namespace dotnet_server.Services;

public class UsersService : IUsersService
{
    public Task DeleteUserAsync(int id)
    {
        throw new System.NotImplementedException();
    }

    public async Task<UserDto> CreateUserAsync(CreateUserModel user)
    {
        var client = GrpcConnector.ConnectUsersServiceAsync();
        var response = await client.createUserAsync(
            new CreateUserRequest
            {
                LegalName = user.LegalName,
                Email = user.Email,
                Phone = user.Phone,
                Cpr = user.Cpr,
                IsAdmin = false,
                IsBanned = false,
            }
        );

        return new UserDto
        {
            Id = (int)response.Id,
            LegalName = response.LegalName,
            Email = response.Email,
            Phone = response.Phone,
            Cpr = response.Cpr,
            IsAdmin = response.IsAdmin,
            IsBanned = response.IsBanned,
        };
    }

    public async Task<UserDto> GetUserAsync(int id)
    {
        var client = GrpcConnector.ConnectUsersServiceAsync();
        var response = await client.getUserAsync(new GetUserRequest { Id = id, });
        return new UserDto
        {
            Id = (int)response.Id,
            LegalName = response.LegalName,
            Email = response.Email,
            Phone = response.Phone,
            Cpr = response.Cpr,
        };
    }

    public async Task<IEnumerable<UserDto>> GetUsersAsync()
    {
        var client = GrpcConnector.ConnectUsersServiceAsync();
        var response = await client.getUsersListAsync(new EmptyUser());

        return response.Users.Select(user => new UserDto
        {
            Id = (int)user.Id,
            LegalName = user.LegalName,
            Email = user.Email,
            Phone = user.Phone,
            Cpr = user.Cpr,
            IsAdmin = user.IsAdmin,
            IsBanned = user.IsBanned,
        });
    }

    public async Task<UserDto> UpdateUserAsync(UserDto user)
    {
        var client = GrpcConnector.ConnectUsersServiceAsync();
        var response = await client.updateUserAsync(
            new UpdateUserRequest
            {
                Id = user.Id,
                LegalName = user.LegalName,
                Email = user.Email,
                Phone = user.Phone,
                Cpr = user.Cpr,
                IsAdmin = user.IsAdmin,
                IsBanned = user.IsBanned,
            }
        );

        return new UserDto
        {
            Id = (int)response.Id,
            LegalName = response.LegalName,
            Email = response.Email,
            Phone = response.Phone,
            Cpr = response.Cpr,
            IsAdmin = response.IsAdmin,
            IsBanned = response.IsBanned,
        };
    }
}
