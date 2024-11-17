using shared.Models;

namespace dotnet_server.Contracts
{
    public interface ICarsService
    {
        Task<IEnumerable<CarDto>> GetCarsAsync();
        Task<CarDto> GetCarAsync(string regNumber);
        Task<CarDto> CreateCarAsync(CarPostModel car);
        Task<CarDto> UpdateCarAsync(CarPostModel car);
        Task DeleteCarAsync(string regNumber);
    }
}
