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
}
