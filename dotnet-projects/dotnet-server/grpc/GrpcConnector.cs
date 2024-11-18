using Grpc.Net.Client;
using static RepositoryGrpcService.CarsService;

namespace dotnet_server.grpc;

public static class GrpcConnector
{
    public static CarsServiceClient ConnectCarServiceAsync()
    {
        using var channel = GrpcChannel.ForAddress("http://localhost:8080");
        var client = new RepositoryGrpcService.CarsService.CarsServiceClient(channel);

        if (client == null)
        {
            throw new Exception("Could not connect to the gRPC server");
        }

        return client;
    }
}
