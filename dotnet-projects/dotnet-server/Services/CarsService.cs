using dotnet_server.Contracts;
using Google.Protobuf;
using Grpc.Net.Client;
using RepositoryGrpcService;
using shared.Enums;
using shared.Models;

namespace dotnet_server.Services;

public class CarsService : ICarsService
{
    private ICloudinaryService _cloudinaryService;

    public CarsService(ICloudinaryService cloudinaryService)
    {
        _cloudinaryService = cloudinaryService;
    }

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
            ImageUrl = car.Image,
        });
    }

    public async Task<CarDto> GetCarAsync(int id)
    {
        throw new NotImplementedException();
    }

    public async Task<CarDto> CreateCarAsync(CarPostModel car)
    {
        using var channel = GrpcChannel.ForAddress("http://localhost:8080");
        // Casting byte array to byte string

        var client = new RepositoryGrpcService.CarsService.CarsServiceClient(channel);
        // Get image url
        var imageUrl = await _cloudinaryService.UploadImageAsync(car.ImageByteArr);

        var response = await client.createAsync(
            new CreateCarRequest
            {
                Color = (int)car.Color,
                Make = (int)car.Make,
                Model = (int)car.Model,
                Type = (int)car.Type,
                RegNumber = car.RegistrationNumber,
                Image = imageUrl,
            }
        );

        return new CarDto()
        {
            RegistrationNumber = response.RegNumber,
            Make = (Make)response.Make,
            Model = (CarModel)response.Model,
            Type = (CarType)response.Type,
            Color = (Color)response.Color,
            // Add image
        };
    }

    public async Task<CarDto> UpdateCarAsync(int id, Car car)
    {
        throw new NotImplementedException();
    }

    public async Task DeleteCarAsync(int id)
    {
        throw new NotImplementedException();
    }
}
