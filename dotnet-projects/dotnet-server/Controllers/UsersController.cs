using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using dotnet_server.Contracts;
using Microsoft.AspNetCore.Mvc;
using Microsoft.IdentityModel.Tokens;
using shared.Models;

namespace dotnet_server.Controllers;

[ApiController]
[Route("[controller]")]
public class UsersController : ControllerBase
{
    private readonly IUsersService _usersService;
    private readonly IConfiguration configuration;

    public UsersController(IUsersService usersService, IConfiguration configuration)
    {
        _usersService = usersService;
        this.configuration = configuration;
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

    [HttpPost("login")]
    public async Task<ActionResult<string>> Login([FromBody] LoginModel login)
    {
        // Validate user credentials
        var allUsers = await _usersService.GetUsersAsync(true);
        var user = allUsers.FirstOrDefault(u =>
            u.Email == login.Email && u.Password == login.Password
        );
        if (user == null)
            return Unauthorized();

        var claims = new List<Claim>
        {
            new Claim(ClaimTypes.Name, user.LegalName),
            new Claim("IsAdmin", user.IsAdmin.ToString()) // Add isAdmin claim
        };

        var secret = configuration["AuthSecret"];
        if (string.IsNullOrEmpty(secret))
            throw new Exception("AuthSecret is missing in appsettings.json");
        var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(secret));
        var creds = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);

        var token = new JwtSecurityToken(
            issuer: "elektricar.api.com",
            audience: "elektricar.api.com",
            claims: claims,
            expires: DateTime.Now.AddHours(8),
            signingCredentials: creds
        );

        return Ok(new { Token = new JwtSecurityTokenHandler().WriteToken(token) });
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
