using dotnet_server.Contracts;
using dotnet_server.grpc;
using Grpc.Net.Client;
using RepositoryGrpcService;
using shared.Models;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Google.Protobuf.WellKnownTypes;
using shared.Enums;

namespace dotnet_server.Services
{
    public class RentalsService : IRentalService
    {
        private readonly ICloudinaryService _cloudinaryService;

        public RentalsService(ICloudinaryService cloudinaryService)
        {
            _cloudinaryService = cloudinaryService;
        }

        public async Task<RentalDto> CreateRentalAsync(RentalDto rental)
        {
            var client = GrpcConnector.ConnectRentalServiceAsync();

            var request = new CreateRentalRequest
            {
                CarRegNumber = rental.CarRegNumber,
                UserId = rental.UserId,
                StartDate = rental.StartDate.ToUnixTimeSeconds(),
                EndDate = rental.EndDate.ToUnixTimeSeconds(),
                DropDate = rental.DropDate.ToUnixTimeSeconds(),
                Status = (int)rental.Status,
                CustomerComment = rental.CustomerComment,
                OrganizerComment = rental.OrganizerComment
            };

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
                CustomerComment = response.CustomerComment,
                OrganizerComment = response.OrganizerComment
            };
        }

        public async Task<IEnumerable<RentalDto>> GetRentalsAsync()
        {
            var client = GrpcConnector.ConnectRentalServiceAsync();

            var response = await client.GetAllRentalsAsync(new Empty());
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
            var client = GrpcConnector.ConnectRentalServiceAsync();

            var request = new GetRentalRequest { Id = id };
            var response = await client.GetRentalAsync(request);
            return new RentalDto
            {
                Id = response.Id,
                CarRegNumber = response.CarRegNumber,
                UserId = response.UserId,
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
            var client = GrpcConnector.ConnectRentalServiceAsync();

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

            var response = await client.UpdateRentalAsync(request);
            return new RentalDto
            {
                Id = response.Id,
                CarRegNumber = response.CarRegNumber,
                UserId = response.UserId,
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