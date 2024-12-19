using System.Net.Http.Json;
using blazor_client.Contacts;
using shared.Models;

namespace blazor_client.Services;

public class ApiService : IApiService
{
    private readonly HttpClient _httpClient;

    public ApiService(HttpClient httpClient)
    {
        _httpClient = httpClient;

        _httpClient.BaseAddress = new Uri("http://localhost:5098");
    }

    public async Task<string?> AddCarAsync(CarPostModel car)
    {
        try
        {
            await _httpClient.PostAsJsonAsync($"/cars", car);
            return null;
        }
        catch (Exception ex)
        {
            return ex.Message;
        }
    }

    public async Task<CarDto[]?> GetAllCarsAsync()
    {
        try
        {
            return await _httpClient.GetFromJsonAsync<CarDto[]>($"/cars/all");
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            return null;
        }
    }

    public async Task<CarDto?> GetCarByRegNumberAsync(int regNumber)
    {
        try
        {
            return await _httpClient.GetFromJsonAsync<CarDto>($"/cars/{regNumber}");
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex);
            throw;
        }
    }

    public async Task<string?> UpdateCarAsync(int regNumber, CarPostModel car)
    {
        try
        {
            var response = await _httpClient.PutAsJsonAsync($"/cars/{regNumber}", car);
            if (response.IsSuccessStatusCode)
                return null;

            return $"Error: {response.StatusCode}";
        }
        catch (Exception ex)
        {
            return ex.Message;
        }
    }

    public async Task<string?> DeleteCarAsync(int regNumber)
    {
        try
        {
            var response = await _httpClient.DeleteAsync($"/cars/{regNumber}");
            if (response.IsSuccessStatusCode)
                return null;

            return $"Error: {response.StatusCode}";
        }
        catch (Exception ex)
        {
            return ex.Message;
        }
    }

    public async Task<string?> AddRentalAsync(RentalDto rental)
    {
        try
        {
            await _httpClient.PostAsJsonAsync($"/rental", rental);
            return null;
        }
        catch (Exception ex)
        {
            return ex.Message;
        }
    }

    public async Task<string?> EditRentalAsync(int id, RentalDto rental)
    {
        try
        {
            await _httpClient.PutAsJsonAsync($"/rental/{id}", rental);
            return null;
        }
        catch (Exception ex)
        {
            return ex.Message;
        }
    }

    public async Task<RentalDto[]?> GetAllRentalsAsync()
    {
        try
        {
            return await _httpClient.GetFromJsonAsync<RentalDto[]>($"/rental/all");
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            return null;
        }
    }

    public async Task<RentalDto?> GetRentalByIdAsync(int Id)
    {
        try
        {
            return await _httpClient.GetFromJsonAsync<RentalDto>($"/rental/{Id}");
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex);
            throw;
        }
    }

    public async Task<string?> UpdateRentalAsync(int id, RentalDto rental)
    {
        try
        {
            var response = await _httpClient.PutAsJsonAsync($"/rental/{id}", rental);
            if (response.IsSuccessStatusCode)
                return null;

            return $"Error: {response.StatusCode}";
        }
        catch (Exception ex)
        {
            return ex.Message;
        }
    }

    public async Task<string?> DeleteRentalAsync(int id)
    {
        try
        {
            var response = await _httpClient.DeleteAsync($"/rental/{id}");
            if (response.IsSuccessStatusCode)
                return null;

            return $"Error: {response.StatusCode}";
        }
        catch (Exception ex)
        {
            return ex.Message;
        }
    }

    public async Task<IEnumerable<UserDto>> GetUsersAsync()
    {
        try
        {
            var response = await _httpClient.GetAsync($"/users/all");
            if (!response.IsSuccessStatusCode)
                return new List<UserDto>();

            return await response.Content.ReadFromJsonAsync<IEnumerable<UserDto>>()
                ?? new List<UserDto>();
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            return new List<UserDto>();
        }
    }

    public async Task<TokenResponse?> LoginAsync(LoginModel login)
    {
        try
        {
            var response = await _httpClient.PostAsJsonAsync($"/users/login", login);

            if (response.IsSuccessStatusCode)
            {
                return await response.Content.ReadFromJsonAsync<TokenResponse>();
            }

            return null;
        }
        catch (Exception ex)
        {
            throw new Exception(ex.Message);
        }
    }

    public async Task<string?> CreateUserAsync(CreateUserModel user)
    {
        try
        {
            var response = await _httpClient.PostAsJsonAsync($"/users", user);

            if (response.IsSuccessStatusCode)
            {
                return null;
            }

            return "The user could not be created";
        }
        catch (Exception ex)
        {
            return ex.Message;
        }
    }
}
