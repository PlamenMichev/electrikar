using Grpc.Net.Client;
using static RepositoryGrpcService.CarsService;
using static RepositoryGrpcService.RentalsService;
using static RepositoryGrpcService.UsersService;

namespace dotnet_server.grpc
{
    public static class GrpcConnector
    {
        private static GrpcChannel CreateChannel()
        {
            return GrpcChannel.ForAddress("http://localhost:8080");
        }

        public static CarsServiceClient ConnectCarServiceAsync()
        {
            var channel = CreateChannel();
            var client = new CarsServiceClient(channel);

            if (client == null)
            {
                throw new Exception("Could not connect to the gRPC server");
            }

            return client;
        }

        public static RentalsServiceClient ConnectRentalServiceAsync()
        {
            var channel = CreateChannel();
            var client = new RentalsServiceClient(channel);

            if (client == null)
            {
                throw new Exception("Could not connect to the gRPC server");
            }

            return client;
        }

        public static UsersServiceClient ConnectUsersServiceAsync()
        {
            var channel = CreateChannel();
            var client = new UsersServiceClient(channel);

            if (client == null)
            {
                throw new Exception("Could not connect to the gRPC server");
            }

            return client;
        }
    }
}
