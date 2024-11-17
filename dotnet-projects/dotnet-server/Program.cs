using dotnet_server.Contracts;
using dotnet_server.Services;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();

// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var corsPolicyName = "AllowBlazorClient";

builder.Services.AddTransient<ICarsService, CarsService>();
builder.Services.AddSingleton<ICloudinaryService, CloudinaryService>();

builder.Services.AddCors(options =>
{
    options.AddPolicy(
        name: corsPolicyName,
        policy =>
        {
            policy.WithOrigins("http://localhost:5279").AllowAnyMethod().AllowAnyHeader();
        }
    );
});

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
}

app.UseAuthorization();

app.MapControllers();
app.UseCors(corsPolicyName);

app.Run();
