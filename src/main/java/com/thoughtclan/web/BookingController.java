package com.thoughtclan.web;

import com.thoughtclan.domain.CustomPagination;
import com.thoughtclan.domain.HotelInfo;
import com.thoughtclan.service.BookingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author thimmv
 */
@RestController
@RequestMapping("/v1/booking")
@Api(tags = "API(s) to manage the Hotel booking")
@Slf4j
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/{country}")
    @ApiOperation(value = "find All hotels available in particular country.")
    public ResponseEntity<List<HotelInfo>> findAllHotels(@ApiParam @PathVariable(required = false) String country,
                                                         @ApiParam @RequestParam(required = false, defaultValue = "price") String sortBy,
                                                         @ApiParam @RequestParam(required = false, defaultValue = "ASC") String sortDirection,
                                                         @ApiParam @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                                         @ApiParam @RequestParam(required = false, defaultValue = "15") Integer pageSize) {
        final List<HotelInfo> hotels = bookingService
                .findAllHotelsByCountry(country,
                        CustomPagination.builder()
                                .pageNumber(pageNumber)
                                .pageSize(pageSize)
                                .sortBy(sortBy)
                                .sortDirection(sortDirection)
                                .build());
//        if (hotels.isEmpty()) return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/{country}/{hotelName}")
    @ApiOperation(value = "Find hotel based on name in particular country.")
    public ResponseEntity<List<HotelInfo>> findByHotelName(@ApiParam @PathVariable(required = false) String country,
                                                           @ApiParam @PathVariable String hotelName,
                                                           @ApiParam @RequestParam(required = false, defaultValue = "price") String sortBy,
                                                           @ApiParam @RequestParam(required = false, defaultValue = "ASC") String sortDirection,
                                                           @ApiParam @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                                           @ApiParam @RequestParam(required = false, defaultValue = "15") Integer pageSize) {
        final List<HotelInfo> hotels = bookingService.findHotelsByCountryAndHotelName(country, hotelName, CustomPagination.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .sortBy(sortBy)
                .sortDirection(sortDirection)
                .build());
//        if (hotels.isEmpty()) return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(hotels);
    }


}
