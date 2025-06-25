package com.example.springbootcrudapp.client;

import com.example.springbootcrudapp.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Sample gRPC client for the Department service
 * This demonstrates how to connect to and use the Department gRPC service
 */
@Component
public class DepartmentGrpcClient {

    private final ManagedChannel channel;
    private final DepartmentServiceGrpc.DepartmentServiceBlockingStub blockingStub;

    public DepartmentGrpcClient() {
        // Create a channel to connect to the gRPC server
        this.channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        
        // Create a blocking stub
        this.blockingStub = DepartmentServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    // Example: Get all departments
    public GetAllDepartmentsResponse getAllDepartments() {
        GetAllDepartmentsRequest request = GetAllDepartmentsRequest.newBuilder().build();
        return blockingStub.getAllDepartments(request);
    }

    // Example: Get department by ID
    public GetDepartmentByIdResponse getDepartmentById(long id) {
        GetDepartmentByIdRequest request = GetDepartmentByIdRequest.newBuilder()
                .setId(id)
                .build();
        return blockingStub.getDepartmentById(request);
    }

    // Example: Create a new department
    public CreateDepartmentResponse createDepartment(String name, String description, String managerName) {
        Department department = Department.newBuilder()
                .setName(name)
                .setDescription(description)
                .setManagerName(managerName)
                .setActive(true)
                .build();
        
        CreateDepartmentRequest request = CreateDepartmentRequest.newBuilder()
                .setDepartment(department)
                .build();
        
        return blockingStub.createDepartment(request);
    }

    // Example: Search departments by name
    public SearchDepartmentsByNameResponse searchDepartmentsByName(String name) {
        SearchDepartmentsByNameRequest request = SearchDepartmentsByNameRequest.newBuilder()
                .setName(name)
                .build();
        return blockingStub.searchDepartmentsByName(request);
    }

    // Example: Get active departments
    public GetActiveDepartmentsResponse getActiveDepartments() {
        GetActiveDepartmentsRequest request = GetActiveDepartmentsRequest.newBuilder().build();
        return blockingStub.getActiveDepartments(request);
    }

    // Example: Activate department
    public ActivateDepartmentResponse activateDepartment(long id) {
        ActivateDepartmentRequest request = ActivateDepartmentRequest.newBuilder()
                .setId(id)
                .build();
        return blockingStub.activateDepartment(request);
    }

    // Example: Update department budget
    public UpdateDepartmentBudgetResponse updateDepartmentBudget(long id, double budget) {
        UpdateDepartmentBudgetRequest request = UpdateDepartmentBudgetRequest.newBuilder()
                .setId(id)
                .setBudget(budget)
                .build();
        return blockingStub.updateDepartmentBudget(request);
    }
} 