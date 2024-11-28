using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using dotnet_server.Contracts;
using dotnet_server.grpc;
using Google.Protobuf.WellKnownTypes;
using Grpc.Net.Client;
using RepositoryGrpcService;
using shared.Enums;
using shared.Models;

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
                StartDate = rental.StartDate.ToUnixTimeMilliseconds(),
                EndDate = rental.EndDate.ToUnixTimeMilliseconds(),
                DropDate = rental.DropDate.ToUnixTimeMilliseconds(),
                Status = (int)rental.Status,
                CustomerComment = rental.CustomerComment ?? string.Empty,
                OrganizerComment = rental.OrganizerComment ?? string.Empty,
            };

            var client = GrpcConnector.ConnectRentalServiceAsync();
            var response = await client.createAsync(request);
            return new RentalDto
            {
                Id = response.Id,
                CarRegNumber = response.CarRegNumber,
                UserId = (int)response.UserId,
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
            var response = await client.getAllRentalsAsync(new EmptyRental());
            return response.Rentals.Select(r => new RentalDto
            {
                Id = r.Id,
                CarRegNumber = r.CarRegNumber,
                UserId = (int)r.UserId,
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
            var response = await client.getRentalAsync(request);
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
            var response = await client.updateRentalAsync(request);
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
            await client.deleteRentalAsync(request);
        }
    }
}