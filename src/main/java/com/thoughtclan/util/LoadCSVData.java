package com.thoughtclan.util;

import com.thoughtclan.domain.HotelInfo;
import com.thoughtclan.entity.HotelEntity;
import com.thoughtclan.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author thimmv
 */
@Component
@Slf4j
public class LoadCSVData implements CommandLineRunner {


    private BookingRepository bookingRepository;

    public LoadCSVData(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Value("${csv.file.path}")
    private String filePath;

    private void loadFileFromCloudAndUploadToLocalDB() {
        Set<HotelEntity> hotels = new HashSet<>();
        try {
            Scanner sc = new Scanner(this.getClass().getResourceAsStream(filePath));
            // skip header info
            sc.nextLine();
            while (sc.hasNextLine()) {
                hotels.add(convertToHotelInfo(sc.nextLine().split(",")));
            }
        } catch (Exception e) {
            log.error("CSV File not exists in desired path {}", e.getMessage());
            e.printStackTrace();
        }
        bookingRepository.saveAll(hotels);

    }

    private HotelEntity convertToHotelInfo(final String[] s) {
        return new HotelEntity(new HotelInfo(s));
    }

    @Override
    public void run(final String... args) throws Exception {
        loadFileFromCloudAndUploadToLocalDB();
    }
}
