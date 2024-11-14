using shared.Models;

namespace dotnet_server.Contracts
{
    public interface ICarsService
    {
        Task<IEnumerable<CarDto>> GetCarsAsync();
        Task<CarDto> GetCarAsync(int id);
        Task<CarDto> CreateCarAsync(Car car);
        Task<CarDto> UpdateCarAsync( Car car);
        Task DeleteCarAsync(int id);
    }
}
