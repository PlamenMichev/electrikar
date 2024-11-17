namespace dotnet_server.Contracts;

using System.Threading.Tasks;

public interface ICloudinaryService
{
    Task<string> UploadImageAsync(byte[] imageBytes);
}
