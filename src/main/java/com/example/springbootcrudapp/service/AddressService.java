package com.example.springbootcrudapp.service;

import com.example.springbootcrudapp.entity.Address;
import com.example.springbootcrudapp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    // Get all addresses
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    // Get address by ID
    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    // Create a new address
    public Address createAddress(Address address) {
        // Check if address already exists with same street, city, and postal code
        if (addressRepository.existsByStreetAndCityAndPostalCode(
                address.getStreet(), address.getCity(), address.getPostalCode())) {
            throw new RuntimeException("Address already exists with same street, city, and postal code");
        }
        return addressRepository.save(address);
    }

    // Update address
    public Address updateAddress(Long id, Address addressDetails) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));

        // Check if address is being changed and if it already exists
        if (!isSameAddress(address, addressDetails) && 
            addressRepository.existsByStreetAndCityAndPostalCode(
                addressDetails.getStreet(), addressDetails.getCity(), addressDetails.getPostalCode())) {
            throw new RuntimeException("Address already exists with same street, city, and postal code");
        }

        address.setStreet(addressDetails.getStreet());
        address.setCity(addressDetails.getCity());
        address.setState(addressDetails.getState());
        address.setCountry(addressDetails.getCountry());
        address.setPostalCode(addressDetails.getPostalCode());
        address.setAddressType(addressDetails.getAddressType());
        address.setAdditionalInfo(addressDetails.getAdditionalInfo());
        address.setLatitude(addressDetails.getLatitude());
        address.setLongitude(addressDetails.getLongitude());
        address.setIsPrimary(addressDetails.getIsPrimary());
        address.setActive(addressDetails.getActive());

        return addressRepository.save(address);
    }

    // Delete address
    public void deleteAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
        addressRepository.delete(address);
    }

    // Search addresses by street
    public List<Address> searchAddressesByStreet(String street) {
        return addressRepository.findByStreetContainingIgnoreCase(street);
    }

    // Find addresses by city
    public List<Address> getAddressesByCity(String city) {
        return addressRepository.findByCity(city);
    }

    // Search addresses by city
    public List<Address> searchAddressesByCity(String city) {
        return addressRepository.findByCityContainingIgnoreCase(city);
    }

    // Find addresses by state
    public List<Address> getAddressesByState(String state) {
        return addressRepository.findByState(state);
    }

    // Find addresses by country
    public List<Address> getAddressesByCountry(String country) {
        return addressRepository.findByCountry(country);
    }

    // Search addresses by country
    public List<Address> searchAddressesByCountry(String country) {
        return addressRepository.findByCountryContainingIgnoreCase(country);
    }

    // Find addresses by postal code
    public List<Address> getAddressesByPostalCode(String postalCode) {
        return addressRepository.findByPostalCode(postalCode);
    }

    // Find addresses by postal code pattern
    public List<Address> getAddressesByPostalCodePattern(String pattern) {
        return addressRepository.findByPostalCodePattern(pattern);
    }

    // Find addresses by address type
    public List<Address> getAddressesByType(String addressType) {
        return addressRepository.findByAddressType(addressType);
    }

    // Find active addresses
    public List<Address> getActiveAddresses() {
        return addressRepository.findByActive(true);
    }

    // Find inactive addresses
    public List<Address> getInactiveAddresses() {
        return addressRepository.findByActive(false);
    }

    // Find primary addresses
    public List<Address> getPrimaryAddresses() {
        return addressRepository.findByIsPrimary(true);
    }

    // Find non-primary addresses
    public List<Address> getNonPrimaryAddresses() {
        return addressRepository.findByIsPrimary(false);
    }

    // Find addresses by city and country
    public List<Address> getAddressesByCityAndCountry(String city, String country) {
        return addressRepository.findByCityAndCountry(city, country);
    }

    // Find addresses by state and country
    public List<Address> getAddressesByStateAndCountry(String state, String country) {
        return addressRepository.findByStateAndCountry(state, country);
    }

    // Find addresses by active status and country
    public List<Address> getAddressesByActiveAndCountry(Boolean active, String country) {
        return addressRepository.findByActiveAndCountry(active, country);
    }

    // Find addresses by active status and city
    public List<Address> getAddressesByActiveAndCity(Boolean active, String city) {
        return addressRepository.findByActiveAndCity(active, city);
    }

    // Find addresses by type and active status
    public List<Address> getAddressesByTypeAndActive(String addressType, Boolean active) {
        return addressRepository.findByAddressTypeAndActive(addressType, active);
    }

    // Find addresses within coordinate range
    public List<Address> getAddressesWithinCoordinates(Double minLatitude, Double maxLatitude, 
                                                      Double minLongitude, Double maxLongitude) {
        return addressRepository.findAddressesWithinCoordinates(minLatitude, maxLatitude, minLongitude, maxLongitude);
    }

    // Find addresses by additional info
    public List<Address> getAddressesByAdditionalInfo(String info) {
        return addressRepository.findByAdditionalInfoContaining(info);
    }

    // Get all addresses ordered by city
    public List<Address> getAllAddressesOrderedByCity() {
        return addressRepository.findAllByOrderByCityAsc();
    }

    // Get all addresses ordered by country and city
    public List<Address> getAllAddressesOrderedByCountryAndCity() {
        return addressRepository.findAllByOrderByCountryAscCityAsc();
    }

    // Activate address
    public Address activateAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
        address.setActive(true);
        return addressRepository.save(address);
    }

    // Deactivate address
    public Address deactivateAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
        address.setActive(false);
        return addressRepository.save(address);
    }

    // Set address as primary
    public Address setPrimaryAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
        address.setIsPrimary(true);
        return addressRepository.save(address);
    }

    // Set address as non-primary
    public Address setNonPrimaryAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
        address.setIsPrimary(false);
        return addressRepository.save(address);
    }

    // Update address coordinates
    public Address updateAddressCoordinates(Long id, Double latitude, Double longitude) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
        address.setLatitude(latitude);
        address.setLongitude(longitude);
        return addressRepository.save(address);
    }

    // Helper method to check if two addresses are the same
    private boolean isSameAddress(Address address1, Address address2) {
        return address1.getStreet().equals(address2.getStreet()) &&
               address1.getCity().equals(address2.getCity()) &&
               ((address1.getPostalCode() == null && address2.getPostalCode() == null) ||
                (address1.getPostalCode() != null && address1.getPostalCode().equals(address2.getPostalCode())));
    }
} 