package electrikar;

import electrikar.database.dao.SqlRentalDao;
import electrikar.enums.RentalStatus;
import io.grpc.stub.StreamObserver;
import org.electrikar.grpc.*;

import java.sql.Timestamp;

public class RentalsServiceImpl
    extends RentalsServiceGrpc.RentalsServiceImplBase
{
  @Override
  public void create(CreateRentalRequest request, StreamObserver<CreateRentalResponse> responseObserver)
  {
    try
    {
      var rental = new Rental(request.getId(), request.getCarRegNumber(), (int)request.getUserId(), new Timestamp(request.getStartDate()), new Timestamp(request.getEndDate()));
      var rentalDao = SqlRentalDao.getInstance();
      var newRental = rentalDao.createRental(rental.getCarRegNumber(), rental.getUserId(), rental.getStartDate(), rental.getEndDate());

      var response = CreateRentalResponse.newBuilder().setId(newRental.getId()).build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }


  @Override
  public void updateRental(UpdateRentalRequest request, StreamObserver<UpdateRentalResponse> responseObserver)
  {
    try
    {
      var rental = new Rental(
              request.getId(),
              request.getCarRegNumber(),
              (int)request.getUserId(),
              new Timestamp(request.getStartDate()),
              new Timestamp(request.getEndDate()),
              new Timestamp(request.getDropDate()),
              RentalStatus.valueOf((int )request.getStatus()),
              request.getCustomerComment(),
              request.getOrganizerComment());
      var rentalDao = SqlRentalDao.getInstance();
      rentalDao.updateRental(rental);

      var response = UpdateRentalResponse.newBuilder().setId(rental.getId()).build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public void deleteRental(DeleteRentalRequest request, StreamObserver<DeleteRentalResponse> responseObserver)
  {
    try
    {
      var rentalDao = SqlRentalDao.getInstance();
      rentalDao.deleteRentalById(request.getId());

      var response = DeleteRentalResponse.newBuilder().setId(request.getId()).build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void getAllRentals(Empty request, StreamObserver<RentalsList> responseObserver)
  {
    try
    {
      var rentalDao = SqlRentalDao.getInstance();
      var rentals = rentalDao.getAllRentals();
      var response = RentalsList.newBuilder();
      for (var rental : rentals)
      {
        var rentalProto = CreateRentalResponse.newBuilder()
            .setId(rental.getId())
            .setCarRegNumber(rental.getCarRegNumber())
            .setUserId(rental.getUserId())
            .setStartDate(rental.getStartDate().getTime())
            .setEndDate(rental.getEndDate().getTime())
                .setStatus(rental.getStatus().ordinal())
                .setDropDate(rental.getDropDate().getTime())
                .setCustomerComment(rental.getCustomerComment() != null ? rental.getCustomerComment() : "")
                .setOrganizerComment(rental.getOrganizerComment() != null ? rental.getOrganizerComment() : "")
            .build();

        response.addRentals(rentalProto);
      }
      responseObserver.onNext(response.build());
      responseObserver.onCompleted();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public void getRental(GetRentalRequest request, StreamObserver<GetRentalResponse> responseObserver)
  {
    try
    {
      var rentalDao = SqlRentalDao.getInstance();
      var rental = rentalDao.getRentalById((int)request.getId());

      if (rental != null)
      {
        var rentalProto = GetRentalResponse.newBuilder()
            .setId(rental.getId())
            .setCarRegNumber(rental.getCarRegNumber())
            .setUserId(rental.getUserId())
            .setStartDate(rental.getStartDate().getTime())
            .setEndDate(rental.getEndDate().getTime())
                .setStatus(rental.getStatus().ordinal())
                .setDropDate(rental.getDropDate().getTime())
                .setCustomerComment(rental.getCustomerComment() != null ? rental.getCustomerComment() : "")
                .setOrganizerComment(rental.getOrganizerComment() != null ? rental.getOrganizerComment() : "")
            .build();
        responseObserver.onNext(rentalProto);
      }
      else
      {
        responseObserver.onNext(GetRentalResponse.newBuilder().build());
      }
      responseObserver.onCompleted();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }



}