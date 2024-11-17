using dotnet_server.Contracts;
using Microsoft.AspNetCore.Mvc;
using shared.Enums;
using shared.Models;

namespace dotnet_server.Controllers;

[ApiController]
[Route("[controller]")]
public class CarsController : ControllerBase
{
    private readonly ICarsService _carsService;

    public CarsController(ICarsService carsService)
    {
        _carsService = carsService;
    }

    public async Task<ActionResult<IEnumerable<CarDto>>> GetById(
        [FromRoute] string registrationNumber
    )
    {
        await Task.Delay(500);
        return Ok(
            new CarDto
            {
                RegistrationNumber = registrationNumber,
                Make = Make.Tesla,
                Model = CarModel.Tesla_ModelS,
                Type = CarType.Sedan,
                Color = Color.Black,
                ImageUrl =
                    "https://media.architecturaldigest.com/photos/63079fc7b4858efb76814bd2/16:9/w_4000,h_2250,c_limit/9.%20DeLorean-Alpha-5%20%5BDeLorean%5D.jpg"
            }
        );
    }

    [HttpGet("all")]
    public async Task<ActionResult<IEnumerable<CarDto>>> Get()
    {
        var carsList = await _carsService.GetCarsAsync();
        return Ok(carsList);
    }

    [HttpPost]
    public async Task<ActionResult<Car>> Create([FromBody] CarPostModel car)
    {
        var response = await _carsService.CreateCarAsync(car);
        return CreatedAtAction(nameof(GetById), new { response.RegistrationNumber }, response);
    }
}
