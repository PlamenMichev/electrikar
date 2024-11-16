using dotnet_server.Contracts;
using Grpc.Net.Client;
using RepositoryGrpcService;
using shared.Models;

namespace dotnet_server.Services
{
    public class RentalService : IRentalService
    {
        

        Task<CarDto> IRentalService.CreateRentalAsync(Car car)
        {
            throw new NotImplementedException();
        }

        public Task<IEnumerable<CarDto>> GetRentalsAsync()
        {
            throw new NotImplementedException();
        }


        Task<CarDto> IRentalService.GetRentalAsync(int id)
        {
            throw new NotImplementedException();
        }

        public Task<CarDto> UpdateRentalAsync(int id, Car car)
        {
            throw new NotImplementedException();
        }


        Task IRentalService.DeleteRentalAsync(int id)
        {
            throw new NotImplementedException();
        }
        
        

        
    }
}