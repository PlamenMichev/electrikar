namespace dotnet_server.Services;

using System.IO;
using System.Threading.Tasks;
using CloudinaryDotNet;
using CloudinaryDotNet.Actions;
using dotnet_server.Contracts;

public class CloudinaryService : ICloudinaryService
{
    private readonly Cloudinary _cloudinary;

    public CloudinaryService(IConfiguration configuration)
    {
        var cloudinaryUrl = configuration["Cloudinary:CloudinaryUrl"];
        _cloudinary = new Cloudinary(cloudinaryUrl);
    }

    public async Task<string> UploadImageAsync(byte[] imageBytes)
    {
        try
        {
            var uploadParams = new ImageUploadParams()
            {
                File = new FileDescription("image", new MemoryStream(imageBytes)),
                UseFilename = true,
                UniqueFilename = false,
                Overwrite = true,
            };

            var uploadResult = await _cloudinary.UploadAsync(uploadParams);
            if (uploadResult.Error != null)
            {
                return string.Empty;
            }

            return uploadResult.SecureUrl.AbsoluteUri;
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            return string.Empty;
        }
    }
}
