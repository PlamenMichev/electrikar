using dotnet_server.Contracts;
using Google.Protobuf;
using Grpc.Net.Client;
using RepositoryGrpcService;
using shared.Enums;
using shared.Models;

namespace dotnet_server.Services
{
    public class CarsService : ICarsService
    {
        public async Task<IEnumerable<CarDto>> GetCarsAsync()
        {
            using var channel = GrpcChannel.ForAddress(
                "http://localhost:8080",
                new GrpcChannelOptions()
                {
                    MaxReceiveMessageSize = 1024 * 1024 * 1024,
                    MaxSendMessageSize = 1024 * 1024 * 1024,
                }
            );

            var client = new RepositoryGrpcService.CarsService.CarsServiceClient(channel);
            var resonse = await client.getAllCarsAsync(new() { });

            return resonse.Cars.Select(car => new CarDto
            {
                Color = (Color)car.Color,
                Make = (Make)car.Make,
                Model = (CarModel)car.Model,
                Type = (CarType)car.Type,
                RegistrationNumber = car.RegNumber,
                ImageUrl = "data:image/png;base64, " + car.Image,
            });
        }

        public async Task<CarDto> GetCarAsync(int id)
        {
            throw new NotImplementedException();
        }

        public async Task<CarDto> CreateCarAsync(Car car)
        {
            using var channel = GrpcChannel.ForAddress("http://localhost:8080");
            // Casting byte array to byte string
            var imageByteString = ByteString.CopyFrom(car.ImageByteArray);

            var client = new RepositoryGrpcService.CarsService.CarsServiceClient(channel);
            var response = await client.createAsync(
                new CreateCarRequest
                {
                    Color = (int)car.Color,
                    Make = (int)car.Make,
                    Model = (int)car.Model,
                    Type = (int)car.Type,
                    RegNumber = car.RegistrationNumber,
                    Image = imageByteString,
                }
            );

            return new CarDto();
        }

        public async Task<CarDto> UpdateCarAsync(Car car)
        {
            using var channel = GrpcChannel.ForAddress("http://localhost:8080");
            var client = new RepositoryGrpcService.CarsService.CarsServiceClient(channel);
            var imageByteString = ByteString.CopyFrom(car.ImageByteArray);
            
            var response = await client.updateAsync(
                new UpdateCarRequest
                {
                    RegNumber = car.RegistrationNumber,
                    Color = (int)car.Color,
                    Make = (int)car.Make,
                    Model = (int)car.Model,
                    Type = (int)car.Type,
                    Image = ByteString.CopyFrom(car.ImageByteArray),
                }
            );
            
            return new CarDto();
        }

        public async Task DeleteCarAsync(int id)
        {
            using var channel = GrpcChannel.ForAddress("http://localhost:8080");
            var client = new RepositoryGrpcService.CarsService.CarsServiceClient(channel);

            var request = new DeleteCarRequest { Id = id };
            await client.deleteAsync(request);
        }   
    }
}
