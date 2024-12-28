package com.aziz.booksocialnetwork.restControllers.customer;

import com.aziz.booksocialnetwork.dto.ReservationDto;
import com.aziz.booksocialnetwork.services.customer.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class BookingController {
    private final BookingService bookingService;
    @PostMapping("/book")
    public ResponseEntity<?> postBooking(@RequestBody ReservationDto reservationDto){
        boolean success = bookingService.postReservation(reservationDto);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/bookings/{userId}/{pageNumber}")
    public ResponseEntity<?> getAllBookingsByUserId(@PathVariable Long userId,@PathVariable int pageNumber){
        try {
            return ResponseEntity.ok(bookingService.getAllReservationsByUserId(userId,pageNumber));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
}
