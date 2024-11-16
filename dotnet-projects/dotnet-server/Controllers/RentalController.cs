using dotnet_server.Contracts;
using Microsoft.AspNetCore.Mvc;
using shared.Models;

namespace dotnet_server.Controllers;

[ApiController]
[Route("[controller]")]
public class RentalController : ControllerBase
{
    private readonly IRentalService _rentalService;
//TODO: make check if the logic is correct in this class because I might be dumb
    public RentalController(IRentalService rentalService)
    {
        _rentalService = rentalService;
    }

    [HttpGet("{id}")]
    public async Task<ActionResult<CarDto>> GetById([FromRoute] int id)
    {
        var rental = await _rentalService.GetRentalAsync(id);
        if (rental == null)
        {
            return NotFound();
        }
        return Ok(rental);
    }

    [HttpGet("all")]
    public async Task<ActionResult<IEnumerable<CarDto>>> Get()
    {
        var rentalsList = await _rentalService.GetRentalsAsync();
        return Ok(rentalsList);
    }

    [HttpPost]
    public async Task<ActionResult<CarDto>> Create([FromBody] Car car)
    {
        var response = await _rentalService.CreateRentalAsync(car);
        return CreatedAtAction(nameof(GetById), new { id = response.Id }, response);
    }

    [HttpPut("{id}")]
    public async Task<ActionResult<CarDto>> Update([FromRoute] int id, [FromBody] Car car)
    {
        var response = await _rentalService.UpdateRentalAsync(id, car);
        if (response == null)
        {
            return NotFound();
        }
        return Ok(response);
    }

    [HttpDelete("{id}")]
    public async Task<ActionResult> Delete([FromRoute] int id)
    {
        var rental = await _rentalService.GetRentalAsync(id);
        if (rental == null)
        {
            return NotFound();
        }
        await _rentalService.DeleteRentalAsync(id);
        return NoContent();
    }
}