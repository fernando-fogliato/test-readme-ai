package com.example.springbootcrudapp.repository;

import com.example.springbootcrudapp.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    // Find addresses by street containing (case-insensitive)
    List<Address> findByStreetContainingIgnoreCase(String street);

    // Find addresses by city
    List<Address> findByCity(String city);

    // Find addresses by city containing (case-insensitive)
    List<Address> findByCityContainingIgnoreCase(String city);

    // Find addresses by state
    List<Address> findByState(String state);

    // Find addresses by country
    List<Address> findByCountry(String country);

    // Find addresses by country containing (case-insensitive)
    List<Address> findByCountryContainingIgnoreCase(String country);

    // Find addresses by postal code
    List<Address> findByPostalCode(String postalCode);

    // Find addresses by address type
    List<Address> findByAddressType(String addressType);

    // Find active addresses
    List<Address> findByActive(Boolean active);

    // Find primary addresses
    List<Address> findByIsPrimary(Boolean isPrimary);

    // Find addresses by city and country
    List<Address> findByCityAndCountry(String city, String country);

    // Find addresses by state and country
    List<Address> findByStateAndCountry(String state, String country);

    // Find addresses by active status and country
    List<Address> findByActiveAndCountry(Boolean active, String country);

    // Find addresses by active status and city
    List<Address> findByActiveAndCity(Boolean active, String city);

    // Find addresses by address type and active status
    List<Address> findByAddressTypeAndActive(String addressType, Boolean active);

    // Custom query to find addresses by postal code pattern
    @Query("SELECT a FROM Address a WHERE a.postalCode LIKE :pattern")
    List<Address> findByPostalCodePattern(@Param("pattern") String pattern);

    // Custom query to find addresses within coordinate range
    @Query("SELECT a FROM Address a WHERE a.latitude BETWEEN :minLat AND :maxLat AND a.longitude BETWEEN :minLng AND :maxLng")
    List<Address> findAddressesWithinCoordinates(@Param("minLat") Double minLatitude, 
                                                @Param("maxLat") Double maxLatitude,
                                                @Param("minLng") Double minLongitude, 
                                                @Param("maxLng") Double maxLongitude);

    // Custom query to find addresses by additional info containing
    @Query("SELECT a FROM Address a WHERE a.additionalInfo LIKE %:info%")
    List<Address> findByAdditionalInfoContaining(@Param("info") String info);

    // Check if address exists with same street, city, and postal code
    boolean existsByStreetAndCityAndPostalCode(String street, String city, String postalCode);

    // Find addresses ordered by city
    List<Address> findAllByOrderByCityAsc();

    // Find addresses ordered by country and city
    List<Address> findAllByOrderByCountryAscCityAsc();
} 