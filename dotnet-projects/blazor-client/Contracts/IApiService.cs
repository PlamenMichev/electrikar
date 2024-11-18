namespace blazor_client.Contacts;

public interface IApiService
{
    Task<string?> AddCarAsync(CarPostModel car);
}
