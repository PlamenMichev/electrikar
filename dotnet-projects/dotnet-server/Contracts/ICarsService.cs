using shared.Models;

namespace dotnet_server.Contracts
{
    public interface ICarsService
    {
        Task<IEnumerable<CarDto>> GetCarsAsync();
        Task<CarDto> GetCarAsync(int id);
        Task<CarDto> CreateCarAsync(CarPostModel car);
        Task<CarDto> UpdateCarAsync(int id, Car car);
        Task DeleteCarAsync(int id);
    }
}
