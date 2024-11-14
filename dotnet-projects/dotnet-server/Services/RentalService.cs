using dotnet_server.Contracts;
using Google.Protobuf;
using Grpc.Net.Client;
using RepositoryGrpcService;
using shared.Enums;
using shared.Models;

namespace dotnet_server.Services;
public class RentalService : IRentalService
{

    public async Task CreateRentalAsync(int customerId, int carId)
    {
        throw new NotImplementedException();
    }

    public Task<IEnumerable<CarDto>> CreateRentalAsync()
    {
        throw new NotImplementedException();
    }

    public Task<IEnumerable<CarDto>> GetRentalsAsync()
    {
        throw new NotImplementedException();
    }

    public Task<IEnumerable<CarDto>> GetRentalAsync(int id)
    {
        throw new NotImplementedException();
    }

    public Task<IEnumerable<CarDto>> UpdateRentalAsync(int id)
    {
        throw new NotImplementedException();
    }

    public Task<IEnumerable<CarDto>> DeleteRentalAsync(int id)
    {
        throw new NotImplementedException();
    }
}