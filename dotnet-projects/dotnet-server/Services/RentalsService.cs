using dotnet_server.Contracts;
using RepositoryGrpcService;
using shared.Models;
using dotnet_server.grpc;
using shared.Enums;

namespace dotnet_server.Services
{
    public class RentalsService : IRentalService
    {
        
        
        public async Task<RentalDto> CreateRentalAsync(RentalDto rental)
        {
            var request = new CreateRentalRequest
            {
                CarRegNumber = rental.CarRegNumber,
                UserId = rental.UserId,
                StartDate = rental.StartDate.ToUnixTimeSeconds(),
                EndDate = rental.EndDate.ToUnixTimeSeconds(),
                DropDate = rental.DropDate.ToUnixTimeSeconds(),
                Status = (int)rental.Status,
                CustomerComment = null,
                OrganizerComment = null
            };

            var client = GrpcConnector.ConnectRentalServiceAsync();
            var response = await client.CreateAsync(request);
            return new RentalDto
            {
                Id = response.Id,
                CarRegNumber = response.CarRegNumber,
                UserId = response.UserId,
                StartDate = DateTimeOffset.FromUnixTimeSeconds(response.StartDate),
                EndDate = DateTimeOffset.FromUnixTimeSeconds(response.EndDate),
                DropDate = DateTimeOffset.FromUnixTimeSeconds(response.DropDate),
                Status = (RentalStatus)response.Status,
                CustomerComment = null,
                OrganizerComment = null
            };
        }

        public async Task<IEnumerable<RentalDto>> GetRentalsAsync()
        {
            var client = GrpcConnector.ConnectRentalServiceAsync();
            var response = await client.GetAllRentalsAsync(new Google.Protobuf.WellKnownTypes.Empty());
            return response.Rentals.Select(r => new RentalDto
            {
                Id = r.Id,
                CarRegNumber = r.CarRegNumber,
                UserId = r.UserId,
                StartDate = DateTimeOffset.FromUnixTimeSeconds(r.StartDate),
                EndDate = DateTimeOffset.FromUnixTimeSeconds(r.EndDate),
                DropDate = DateTimeOffset.FromUnixTimeSeconds(r.DropDate),
                Status = (RentalStatus)r.Status,
                CustomerComment = r.CustomerComment,
                OrganizerComment = r.OrganizerComment
            });
        }

        public async Task<RentalDto> GetRentalAsync(int id)
        {
            var request = new GetRentalRequest { Id = id };
            var client = GrpcConnector.ConnectRentalServiceAsync();
            var response = await client.GetRentalAsync(request);
            return new RentalDto
            {
                Id = response.Id,
                CarRegNumber = response.CarRegNumber,
                UserId = (int)response.UserId,
                StartDate = DateTimeOffset.FromUnixTimeSeconds(response.StartDate),
                EndDate = DateTimeOffset.FromUnixTimeSeconds(response.EndDate),
                DropDate = DateTimeOffset.FromUnixTimeSeconds(response.DropDate),
                Status = (RentalStatus)response.Status,
                CustomerComment = response.CustomerComment,
                OrganizerComment = response.OrganizerComment
            };
        }

        public async Task<RentalDto> UpdateRentalAsync(int id, RentalDto rental)
        {
            var request = new UpdateRentalRequest
            {
                Id = id,
                CarRegNumber = rental.CarRegNumber,
                UserId = rental.UserId,
                StartDate = rental.StartDate.ToUnixTimeSeconds(),
                EndDate = rental.EndDate.ToUnixTimeSeconds(),
                DropDate = rental.DropDate.ToUnixTimeSeconds(),
                Status = (int)rental.Status,
                CustomerComment = rental.CustomerComment,
                OrganizerComment = rental.OrganizerComment
            };
            
            var client = GrpcConnector.ConnectRentalServiceAsync();
            var response = await client.UpdateRentalAsync(request);
            return new RentalDto
            {
                Id = response.Id,
                CarRegNumber = response.CarRegNumber,
                UserId = (int)response.UserId,
                StartDate = DateTimeOffset.FromUnixTimeSeconds(response.StartDate),
                EndDate = DateTimeOffset.FromUnixTimeSeconds(response.EndDate),
                DropDate = DateTimeOffset.FromUnixTimeSeconds(response.DropDate),
                Status = (RentalStatus)response.Status,
                CustomerComment = response.CustomerComment,
                OrganizerComment = response.OrganizerComment
            };
        }

        public async Task DeleteRentalAsync(int id)
        {
            var client = GrpcConnector.ConnectRentalServiceAsync();
            var request = new DeleteRentalRequest { Id = id };
            await client.DeleteRentalAsync(request);
        }
    }
}