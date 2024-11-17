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
        var response = await _carsService.GetCarAsync(registrationNumber);
        return Ok(response);
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

    [HttpPut]
    public async Task<ActionResult<Car>> Update([FromBody] CarPostModel car)
    {
        var response = await _carsService.UpdateCarAsync(car);
        return Ok(response);
    }

    [HttpDelete("{regNumber}")]
    public async Task<ActionResult> Delete([FromRoute] string regNumber)
    {
        await _carsService.DeleteCarAsync(regNumber);
        return NoContent();
    }
}
