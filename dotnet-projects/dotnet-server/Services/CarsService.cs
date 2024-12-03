using dotnet_server.Contracts;
using dotnet_server.grpc;
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
        var client = GrpcConnector.ConnectCarServiceAsync();
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

    public async Task<CarDto> GetCarAsync(string regNumber)
    {
        var client = GrpcConnector.ConnectCarServiceAsync();
        var response = await client.getCarAsync(new GetCarRequest { RegNumber = regNumber });

        return new CarDto()
        {
            RegistrationNumber = response.RegNumber,
            Make = (Make)response.Make,
            Model = (CarModel)response.Model,
            Type = (CarType)response.Type,
            Color = (Color)response.Color,
            ImageUrl = response.Image,
        };
    }

    public async Task<CarDto> CreateCarAsync(CarPostModel car)
    {
        var client = GrpcConnector.ConnectCarServiceAsync();

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
            ImageUrl = response.Image,
        };
    }

    public async Task<CarDto> UpdateCarAsync(CarPostModel car)
    {
        var client = GrpcConnector.ConnectCarServiceAsync();

        // Get image url
        var carFromDb = await this.GetCarAsync(car.RegistrationNumber);
        var imageUrl = carFromDb.ImageUrl;
        if (car.ShouldChangeFile)
        {
            imageUrl = await _cloudinaryService.UploadImageAsync(car.ImageByteArr);
        }

        var response = await client.updateCarAsync(
            new UpdateCarRequest
            {
                RegNumber = car.RegistrationNumber,
                Color = (int)car.Color,
                Make = (int)car.Make,
                Model = (int)car.Model,
                Type = (int)car.Type,
                Image = imageUrl,
            }
        );

        return new CarDto() { RegistrationNumber = response.RegNumber, };
    }

    public async Task DeleteCarAsync(string regNumber)
    {
        var client = GrpcConnector.ConnectCarServiceAsync();
        var request = new DeleteCarRequest { RegNumber = regNumber, };
        await client.deleteCarAsync(request);
    }
}
