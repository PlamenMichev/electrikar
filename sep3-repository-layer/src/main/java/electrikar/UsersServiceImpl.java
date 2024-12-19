package electrikar;

import electrikar.database.dao.SqlUserDao;
import io.grpc.stub.StreamObserver;
import org.electrikar.grpc.*;

public class UsersServiceImpl extends UsersServiceGrpc.UsersServiceImplBase
{
  /**
   * Create a new user
   * @param request
   * @param responseObserver
   */
  @Override public void createUser(
      org.electrikar.grpc.CreateUserRequest request,
      io.grpc.stub.StreamObserver<org.electrikar.grpc.CreateUserResponse> responseObserver)
  {
    System.out.println("Received request: " + request.getLegalName());
    System.out.println("Received request: " + request.getEmail());
    System.out.println("Received request: " + request.getPassword());
    System.out.println("Received request: " + request.getCpr());
    System.out.println("Received request: " + request.getPhone());
    System.out.println("Received request: " + request.getIsAdmin());
    System.out.println("Received request: " + request.getIsBanned());

    try
    {
      var user = new User((int) request.getId(), request.getLegalName(),
          request.getEmail(), request.getPassword(), request.getCpr(),
          request.getPhone(), request.getIsAdmin(), request.getIsBanned());
      var userDao = SqlUserDao.getInstance();
      var newUser = userDao.createUser(user.getLegalName(), user.getEmail(),
          user.getPassword(), user.getCpr(), user.getPhone(),
          user.verifyAdmin(), user.verifyBanned());

      var response = org.electrikar.grpc.CreateUserResponse.newBuilder()
          .setLegalName(newUser.getLegalName()).build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Update a user
   * @param request
   * @param responseObserver
   */
  @Override public void updateUser(
      org.electrikar.grpc.UpdateUserRequest request,
      io.grpc.stub.StreamObserver<org.electrikar.grpc.UpdateUserResponse> responseObserver)
  {
    try
    {
      var user = new User((int) request.getId(), request.getLegalName(),
          request.getEmail(), request.getPassword(), request.getCpr(),
          request.getPhone(), request.getIsAdmin(), request.getIsBanned());
      var userDao = SqlUserDao.getInstance();
      userDao.updateUser(user);

      var response = org.electrikar.grpc.UpdateUserResponse.newBuilder()
          .setId(user.getId()).build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Delete a user
   * @param request
   * @param responseObserver
   */
  @Override public void deleteUser(
      org.electrikar.grpc.DeleteUserRequest request,
      io.grpc.stub.StreamObserver<org.electrikar.grpc.DeleteUserResponse> responseObserver)
  {
    try
    {
      var userDao = SqlUserDao.getInstance();
      userDao.deleteUserById((int) request.getId());

      var response = org.electrikar.grpc.DeleteUserResponse.newBuilder()
          .setId(request.getId()).build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Get a user by its id
   * @param request
   * @param responseObserver
   */
  @Override public void getUser(org.electrikar.grpc.GetUserRequest request,
      io.grpc.stub.StreamObserver<org.electrikar.grpc.GetUserResponse> responseObserver)
  {
    try
    {
      var userDao = SqlUserDao.getInstance();
      var user = userDao.getUserById((int)request.getId());

      var response = org.electrikar.grpc.GetUserResponse.newBuilder()
          .setId(user.getId()).setLegalName(user.getLegalName())
          .setEmail(user.getEmail()).setPassword(user.getPassword())
          .setCpr(user.getCpr()).setPhone(user.getPhone())
          .setIsAdmin(user.verifyAdmin()).setIsBanned(user.verifyBanned())
          .build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Get a list of all users
   * @param request
   * @param responseObserver
   */
  public void getUsersList(Empty request, StreamObserver<UsersList> responseObserver)
  {
    try
    {
      var userDao = SqlUserDao.getInstance();
      var users = userDao.getAll();

      var usersList = UsersList.newBuilder();
      for (var user : users)
      {
        var userProto = CreateUserResponse.newBuilder()
            .setId(user.getId())
            .setLegalName(user.getLegalName())
            .setEmail(user.getEmail())
            .setPassword(user.getPassword())
            .setCpr(user.getCpr())
            .setPhone(user.getPhone())
            .setIsAdmin(user.verifyAdmin())
            .setIsBanned(user.verifyBanned())
            .build();
        usersList.addUsers(userProto);
      }
      responseObserver.onNext(usersList.build());
      responseObserver.onCompleted();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
