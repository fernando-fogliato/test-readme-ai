package com.example.springbootcrudapp.controller;

import com.example.springbootcrudapp.entity.Address;
import com.example.springbootcrudapp.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addresses")
@CrossOrigin(origins = "*")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // GET /api/addresses - Get all addresses
    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addresses = addressService.getAllAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/{id} - Get address by ID
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        Optional<Address> address = addressService.getAddressById(id);
        if (address.isPresent()) {
            return new ResponseEntity<>(address.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST /api/addresses - Create a new address
    @PostMapping
    public ResponseEntity<?> createAddress(@Valid @RequestBody Address address) {
        try {
            Address savedAddress = addressService.createAddress(address);
            return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // PUT /api/addresses/{id} - Update address
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long id, @Valid @RequestBody Address addressDetails) {
        try {
            Address updatedAddress = addressService.updateAddress(id, addressDetails);
            return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // DELETE /api/addresses/{id} - Delete address
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
        try {
            addressService.deleteAddress(id);
            return new ResponseEntity<>("Address deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // GET /api/addresses/search/street?street={street} - Search addresses by street
    @GetMapping("/search/street")
    public ResponseEntity<List<Address>> searchAddressesByStreet(@RequestParam String street) {
        List<Address> addresses = addressService.searchAddressesByStreet(street);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/city/{city} - Get addresses by city
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Address>> getAddressesByCity(@PathVariable String city) {
        List<Address> addresses = addressService.getAddressesByCity(city);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/search/city?city={city} - Search addresses by city
    @GetMapping("/search/city")
    public ResponseEntity<List<Address>> searchAddressesByCity(@RequestParam String city) {
        List<Address> addresses = addressService.searchAddressesByCity(city);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/state/{state} - Get addresses by state
    @GetMapping("/state/{state}")
    public ResponseEntity<List<Address>> getAddressesByState(@PathVariable String state) {
        List<Address> addresses = addressService.getAddressesByState(state);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/country/{country} - Get addresses by country
    @GetMapping("/country/{country}")
    public ResponseEntity<List<Address>> getAddressesByCountry(@PathVariable String country) {
        List<Address> addresses = addressService.getAddressesByCountry(country);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/search/country?country={country} - Search addresses by country
    @GetMapping("/search/country")
    public ResponseEntity<List<Address>> searchAddressesByCountry(@RequestParam String country) {
        List<Address> addresses = addressService.searchAddressesByCountry(country);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/postal-code/{postalCode} - Get addresses by postal code
    @GetMapping("/postal-code/{postalCode}")
    public ResponseEntity<List<Address>> getAddressesByPostalCode(@PathVariable String postalCode) {
        List<Address> addresses = addressService.getAddressesByPostalCode(postalCode);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/postal-code-pattern?pattern={pattern} - Get addresses by postal code pattern
    @GetMapping("/postal-code-pattern")
    public ResponseEntity<List<Address>> getAddressesByPostalCodePattern(@RequestParam String pattern) {
        List<Address> addresses = addressService.getAddressesByPostalCodePattern(pattern);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/type/{type} - Get addresses by type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Address>> getAddressesByType(@PathVariable String type) {
        List<Address> addresses = addressService.getAddressesByType(type);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/active - Get active addresses
    @GetMapping("/active")
    public ResponseEntity<List<Address>> getActiveAddresses() {
        List<Address> addresses = addressService.getActiveAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/inactive - Get inactive addresses
    @GetMapping("/inactive")
    public ResponseEntity<List<Address>> getInactiveAddresses() {
        List<Address> addresses = addressService.getInactiveAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/primary - Get primary addresses
    @GetMapping("/primary")
    public ResponseEntity<List<Address>> getPrimaryAddresses() {
        List<Address> addresses = addressService.getPrimaryAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/non-primary - Get non-primary addresses
    @GetMapping("/non-primary")
    public ResponseEntity<List<Address>> getNonPrimaryAddresses() {
        List<Address> addresses = addressService.getNonPrimaryAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/filter/city-country?city={city}&country={country} - Get addresses by city and country
    @GetMapping("/filter/city-country")
    public ResponseEntity<List<Address>> getAddressesByCityAndCountry(
            @RequestParam String city, 
            @RequestParam String country) {
        List<Address> addresses = addressService.getAddressesByCityAndCountry(city, country);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/filter/state-country?state={state}&country={country} - Get addresses by state and country
    @GetMapping("/filter/state-country")
    public ResponseEntity<List<Address>> getAddressesByStateAndCountry(
            @RequestParam String state, 
            @RequestParam String country) {
        List<Address> addresses = addressService.getAddressesByStateAndCountry(state, country);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/filter/active-country?active={active}&country={country} - Get addresses by active status and country
    @GetMapping("/filter/active-country")
    public ResponseEntity<List<Address>> getAddressesByActiveAndCountry(
            @RequestParam Boolean active, 
            @RequestParam String country) {
        List<Address> addresses = addressService.getAddressesByActiveAndCountry(active, country);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/filter/active-city?active={active}&city={city} - Get addresses by active status and city
    @GetMapping("/filter/active-city")
    public ResponseEntity<List<Address>> getAddressesByActiveAndCity(
            @RequestParam Boolean active, 
            @RequestParam String city) {
        List<Address> addresses = addressService.getAddressesByActiveAndCity(active, city);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/filter/type-active?type={type}&active={active} - Get addresses by type and active status
    @GetMapping("/filter/type-active")
    public ResponseEntity<List<Address>> getAddressesByTypeAndActive(
            @RequestParam String type, 
            @RequestParam Boolean active) {
        List<Address> addresses = addressService.getAddressesByTypeAndActive(type, active);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/coordinates?minLat={minLat}&maxLat={maxLat}&minLng={minLng}&maxLng={maxLng} - Get addresses within coordinate range
    @GetMapping("/coordinates")
    public ResponseEntity<List<Address>> getAddressesWithinCoordinates(
            @RequestParam Double minLat,
            @RequestParam Double maxLat,
            @RequestParam Double minLng,
            @RequestParam Double maxLng) {
        List<Address> addresses = addressService.getAddressesWithinCoordinates(minLat, maxLat, minLng, maxLng);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/search/additional-info?info={info} - Get addresses by additional info
    @GetMapping("/search/additional-info")
    public ResponseEntity<List<Address>> getAddressesByAdditionalInfo(@RequestParam String info) {
        List<Address> addresses = addressService.getAddressesByAdditionalInfo(info);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/ordered/city - Get all addresses ordered by city
    @GetMapping("/ordered/city")
    public ResponseEntity<List<Address>> getAllAddressesOrderedByCity() {
        List<Address> addresses = addressService.getAllAddressesOrderedByCity();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // GET /api/addresses/ordered/country-city - Get all addresses ordered by country and city
    @GetMapping("/ordered/country-city")
    public ResponseEntity<List<Address>> getAllAddressesOrderedByCountryAndCity() {
        List<Address> addresses = addressService.getAllAddressesOrderedByCountryAndCity();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // PUT /api/addresses/{id}/activate - Activate address
    @PutMapping("/{id}/activate")
    public ResponseEntity<?> activateAddress(@PathVariable Long id) {
        try {
            Address address = addressService.activateAddress(id);
            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // PUT /api/addresses/{id}/deactivate - Deactivate address
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivateAddress(@PathVariable Long id) {
        try {
            Address address = addressService.deactivateAddress(id);
            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // PUT /api/addresses/{id}/set-primary - Set address as primary
    @PutMapping("/{id}/set-primary")
    public ResponseEntity<?> setPrimaryAddress(@PathVariable Long id) {
        try {
            Address address = addressService.setPrimaryAddress(id);
            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // PUT /api/addresses/{id}/set-non-primary - Set address as non-primary
    @PutMapping("/{id}/set-non-primary")
    public ResponseEntity<?> setNonPrimaryAddress(@PathVariable Long id) {
        try {
            Address address = addressService.setNonPrimaryAddress(id);
            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // PUT /api/addresses/{id}/coordinates - Update address coordinates
    @PutMapping("/{id}/coordinates")
    public ResponseEntity<?> updateAddressCoordinates(@PathVariable Long id, @RequestBody CoordinatesRequest request) {
        try {
            Address address = addressService.updateAddressCoordinates(id, request.getLatitude(), request.getLongitude());
            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Helper class for coordinates request
    public static class CoordinatesRequest {
        private Double latitude;
        private Double longitude;

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }
    }
} 