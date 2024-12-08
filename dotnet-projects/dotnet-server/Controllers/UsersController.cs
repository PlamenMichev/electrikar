using dotnet_server.Contracts;
using Microsoft.AspNetCore.Mvc;
using shared.Models;

namespace dotnet_server.Controllers;

[ApiController]
[Route("[controller]")]
public class UsersController : ControllerBase
{
    private readonly IUsersService _usersService;

    public UsersController(IUsersService usersService)
    {
        _usersService = usersService;
    }

    [HttpGet("{id}")]
    public async Task<ActionResult<UserDto>> GetById([FromRoute] int id)
    {
        var user = await _usersService.GetUserAsync(id);
        if (user == null)
        {
            return NotFound();
        }
        return Ok(user);
    }

    [HttpGet("all")]
    public async Task<ActionResult<IEnumerable<UserDto>>> Get()
    {
        var usersList = await _usersService.GetUsersAsync();
        return Ok(usersList);
    }

    [HttpPost]
    public async Task<ActionResult<UserDto>> Create([FromBody] CreateUserModel user)
    {
        var response = await _usersService.CreateUserAsync(user);
        return CreatedAtAction(nameof(GetById), new { id = response.Id }, response);
    }

    [HttpPut("{id}")]
    public async Task<ActionResult<UserDto>> Update([FromRoute] Guid id, [FromBody] UserDto user)
    {
        var response = await _usersService.UpdateUserAsync(user);
        return Ok(response);
    }

    [HttpDelete("{id}")]
    public async Task<ActionResult> Delete([FromRoute] int id)
    {
        await _usersService.DeleteUserAsync(id);
        return NoContent();
    }
}
