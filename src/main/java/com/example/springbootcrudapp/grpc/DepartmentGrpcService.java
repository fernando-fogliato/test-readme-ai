package com.example.springbootcrudapp.grpc;

import com.example.springbootcrudapp.entity.Department;
import com.example.springbootcrudapp.service.DepartmentService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@GrpcService
public class DepartmentGrpcService extends DepartmentServiceGrpc.DepartmentServiceImplBase {

    @Autowired
    private DepartmentService departmentService;

    // Basic CRUD operations
    @Override
    public void getAllDepartments(GetAllDepartmentsRequest request, StreamObserver<GetAllDepartmentsResponse> responseObserver) {
        try {
            List<Department> departments = departmentService.getAllDepartments();
            GetAllDepartmentsResponse response = GetAllDepartmentsResponse.newBuilder()
                    .addAllDepartments(departments.stream()
                            .map(this::convertToGrpc)
                            .collect(Collectors.toList()))
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getDepartmentById(GetDepartmentByIdRequest request, StreamObserver<GetDepartmentByIdResponse> responseObserver) {
        try {
            Optional<Department> department = departmentService.getDepartmentById(request.getId());
            GetDepartmentByIdResponse.Builder responseBuilder = GetDepartmentByIdResponse.newBuilder()
                    .setFound(department.isPresent());
            
            if (department.isPresent()) {
                responseBuilder.setDepartment(convertToGrpc(department.get()));
            }
            
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void createDepartment(CreateDepartmentRequest request, StreamObserver<CreateDepartmentResponse> responseObserver) {
        try {
            Department department = convertToJpa(request.getDepartment());
            Department savedDepartment = departmentService.createDepartment(department);
            
            CreateDepartmentResponse response = CreateDepartmentResponse.newBuilder()
                    .setDepartment(convertToGrpc(savedDepartment))
                    .setSuccess(true)
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (RuntimeException e) {
            CreateDepartmentResponse response = CreateDepartmentResponse.newBuilder()
                    .setSuccess(false)
                    .setErrorMessage(e.getMessage())
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void updateDepartment(UpdateDepartmentRequest request, StreamObserver<UpdateDepartmentResponse> responseObserver) {
        try {
            Department department = convertToJpa(request.getDepartment());
            Department updatedDepartment = departmentService.updateDepartment(request.getId(), department);
            
            UpdateDepartmentResponse response = UpdateDepartmentResponse.newBuilder()
                    .setDepartment(convertToGrpc(updatedDepartment))
                    .setSuccess(true)
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (RuntimeException e) {
            UpdateDepartmentResponse response = UpdateDepartmentResponse.newBuilder()
                    .setSuccess(false)
                    .setErrorMessage(e.getMessage())
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void deleteDepartment(DeleteDepartmentRequest request, StreamObserver<DeleteDepartmentResponse> responseObserver) {
        try {
            departmentService.deleteDepartment(request.getId());
            
            DeleteDepartmentResponse response = DeleteDepartmentResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Department deleted successfully")
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (RuntimeException e) {
            DeleteDepartmentResponse response = DeleteDepartmentResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage(e.getMessage())
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    // Search operations
    @Override
    public void getDepartmentByName(GetDepartmentByNameRequest request, StreamObserver<GetDepartmentByNameResponse> responseObserver) {
        try {
            Optional<Department> department = departmentService.getDepartmentByName(request.getName());
            GetDepartmentByNameResponse.Builder responseBuilder = GetDepartmentByNameResponse.newBuilder()
                    .setFound(department.isPresent());
            
            if (department.isPresent()) {
                responseBuilder.setDepartment(convertToGrpc(department.get()));
            }
            
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void searchDepartmentsByName(SearchDepartmentsByNameRequest request, StreamObserver<SearchDepartmentsByNameResponse> responseObserver) {
        try {
            List<Department> departments = departmentService.searchDepartmentsByName(request.getName());
            SearchDepartmentsByNameResponse response = SearchDepartmentsByNameResponse.newBuilder()
                    .addAllDepartments(departments.stream()
                            .map(this::convertToGrpc)
                            .collect(Collectors.toList()))
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void searchDepartmentsByManagerName(SearchDepartmentsByManagerNameRequest request, StreamObserver<SearchDepartmentsByManagerNameResponse> responseObserver) {
        try {
            List<Department> departments = departmentService.searchDepartmentsByManagerName(request.getManagerName());
            SearchDepartmentsByManagerNameResponse response = SearchDepartmentsByManagerNameResponse.newBuilder()
                    .addAllDepartments(departments.stream()
                            .map(this::convertToGrpc)
                            .collect(Collectors.toList()))
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void searchDepartmentsByDescription(SearchDepartmentsByDescriptionRequest request, StreamObserver<SearchDepartmentsByDescriptionResponse> responseObserver) {
        try {
            List<Department> departments = departmentService.searchDepartmentsByDescription(request.getDescription());
            SearchDepartmentsByDescriptionResponse response = SearchDepartmentsByDescriptionResponse.newBuilder()
                    .addAllDepartments(departments.stream()
                            .map(this::convertToGrpc)
                            .collect(Collectors.toList()))
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    // Filter operations
    @Override
    public void getDepartmentsByLocation(GetDepartmentsByLocationRequest request, StreamObserver<GetDepartmentsByLocationResponse> responseObserver) {
        try {
            List<Department> departments = departmentService.getDepartmentsByLocation(request.getLocation());
            GetDepartmentsByLocationResponse response = GetDepartmentsByLocationResponse.newBuilder()
                    .addAllDepartments(departments.stream()
                            .map(this::convertToGrpc)
                            .collect(Collectors.toList()))
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getActiveDepartments(GetActiveDepartmentsRequest request, StreamObserver<GetActiveDepartmentsResponse> responseObserver) {
        try {
            List<Department> departments = departmentService.getActiveDepartments();
            GetActiveDepartmentsResponse response = GetActiveDepartmentsResponse.newBuilder()
                    .addAllDepartments(departments.stream()
                            .map(this::convertToGrpc)
                            .collect(Collectors.toList()))
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getInactiveDepartments(GetInactiveDepartmentsRequest request, StreamObserver<GetInactiveDepartmentsResponse> responseObserver) {
        try {
            List<Department> departments = departmentService.getInactiveDepartments();
            GetInactiveDepartmentsResponse response = GetInactiveDepartmentsResponse.newBuilder()
                    .addAllDepartments(departments.stream()
                            .map(this::convertToGrpc)
                            .collect(Collectors.toList()))
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getDepartmentsByBudget(GetDepartmentsByBudgetRequest request, StreamObserver<GetDepartmentsByBudgetResponse> responseObserver) {
        try {
            List<Department> departments = departmentService.getDepartmentsByBudgetGreaterThan(request.getMinBudget());
            GetDepartmentsByBudgetResponse response = GetDepartmentsByBudgetResponse.newBuilder()
                    .addAllDepartments(departments.stream()
                            .map(this::convertToGrpc)
                            .collect(Collectors.toList()))
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getDepartmentsByEmployeeCount(GetDepartmentsByEmployeeCountRequest request, StreamObserver<GetDepartmentsByEmployeeCountResponse> responseObserver) {
        try {
            List<Department> departments = departmentService.getDepartmentsByEmployeeCountGreaterThan(request.getMinEmployeeCount());
            GetDepartmentsByEmployeeCountResponse response = GetDepartmentsByEmployeeCountResponse.newBuilder()
                    .addAllDepartments(departments.stream()
                            .map(this::convertToGrpc)
                            .collect(Collectors.toList()))
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getDepartmentsByManagerEmail(GetDepartmentsByManagerEmailRequest request, StreamObserver<GetDepartmentsByManagerEmailResponse> responseObserver) {
        try {
            List<Department> departments = departmentService.getDepartmentsByManagerEmail(request.getManagerEmail());
            GetDepartmentsByManagerEmailResponse response = GetDepartmentsByManagerEmailResponse.newBuilder()
                    .addAllDepartments(departments.stream()
                            .map(this::convertToGrpc)
                            .collect(Collectors.toList()))
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getDepartmentsByActiveAndLocation(GetDepartmentsByActiveAndLocationRequest request, StreamObserver<GetDepartmentsByActiveAndLocationResponse> responseObserver) {
        try {
            List<Department> departments = departmentService.getDepartmentsByActiveAndLocation(request.getActive(), request.getLocation());
            GetDepartmentsByActiveAndLocationResponse response = GetDepartmentsByActiveAndLocationResponse.newBuilder()
                    .addAllDepartments(departments.stream()
                            .map(this::convertToGrpc)
                            .collect(Collectors.toList()))
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    // Status operations
    @Override
    public void activateDepartment(ActivateDepartmentRequest request, StreamObserver<ActivateDepartmentResponse> responseObserver) {
        try {
            Department department = departmentService.activateDepartment(request.getId());
            ActivateDepartmentResponse response = ActivateDepartmentResponse.newBuilder()
                    .setDepartment(convertToGrpc(department))
                    .setSuccess(true)
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (RuntimeException e) {
            ActivateDepartmentResponse response = ActivateDepartmentResponse.newBuilder()
                    .setSuccess(false)
                    .setErrorMessage(e.getMessage())
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void deactivateDepartment(DeactivateDepartmentRequest request, StreamObserver<DeactivateDepartmentResponse> responseObserver) {
        try {
            Department department = departmentService.deactivateDepartment(request.getId());
            DeactivateDepartmentResponse response = DeactivateDepartmentResponse.newBuilder()
                    .setDepartment(convertToGrpc(department))
                    .setSuccess(true)
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (RuntimeException e) {
            DeactivateDepartmentResponse response = DeactivateDepartmentResponse.newBuilder()
                    .setSuccess(false)
                    .setErrorMessage(e.getMessage())
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    // Update operations
    @Override
    public void updateDepartmentBudget(UpdateDepartmentBudgetRequest request, StreamObserver<UpdateDepartmentBudgetResponse> responseObserver) {
        try {
            Department department = departmentService.updateDepartmentBudget(request.getId(), request.getBudget());
            UpdateDepartmentBudgetResponse response = UpdateDepartmentBudgetResponse.newBuilder()
                    .setDepartment(convertToGrpc(department))
                    .setSuccess(true)
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (RuntimeException e) {
            UpdateDepartmentBudgetResponse response = UpdateDepartmentBudgetResponse.newBuilder()
                    .setSuccess(false)
                    .setErrorMessage(e.getMessage())
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void updateDepartmentEmployeeCount(UpdateDepartmentEmployeeCountRequest request, StreamObserver<UpdateDepartmentEmployeeCountResponse> responseObserver) {
        try {
            Department department = departmentService.updateDepartmentEmployeeCount(request.getId(), request.getEmployeeCount());
            UpdateDepartmentEmployeeCountResponse response = UpdateDepartmentEmployeeCountResponse.newBuilder()
                    .setDepartment(convertToGrpc(department))
                    .setSuccess(true)
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (RuntimeException e) {
            UpdateDepartmentEmployeeCountResponse response = UpdateDepartmentEmployeeCountResponse.newBuilder()
                    .setSuccess(false)
                    .setErrorMessage(e.getMessage())
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    // Helper methods for conversion
    private com.example.springbootcrudapp.grpc.Department convertToGrpc(Department jpaEntity) {
        com.example.springbootcrudapp.grpc.Department.Builder builder = com.example.springbootcrudapp.grpc.Department.newBuilder()
                .setId(jpaEntity.getId())
                .setName(jpaEntity.getName())
                .setManagerName(jpaEntity.getManagerName())
                .setActive(jpaEntity.getActive());

        if (jpaEntity.getDescription() != null) {
            builder.setDescription(jpaEntity.getDescription());
        }
        if (jpaEntity.getManagerEmail() != null) {
            builder.setManagerEmail(jpaEntity.getManagerEmail());
        }
        if (jpaEntity.getLocation() != null) {
            builder.setLocation(jpaEntity.getLocation());
        }
        if (jpaEntity.getBudget() != null) {
            builder.setBudget(jpaEntity.getBudget());
        }
        if (jpaEntity.getEmployeeCount() != null) {
            builder.setEmployeeCount(jpaEntity.getEmployeeCount());
        }

        return builder.build();
    }

    private Department convertToJpa(com.example.springbootcrudapp.grpc.Department grpcMessage) {
        Department jpaEntity = new Department();
        jpaEntity.setId(grpcMessage.getId());
        jpaEntity.setName(grpcMessage.getName());
        jpaEntity.setDescription(grpcMessage.getDescription().isEmpty() ? null : grpcMessage.getDescription());
        jpaEntity.setManagerName(grpcMessage.getManagerName());
        jpaEntity.setManagerEmail(grpcMessage.getManagerEmail().isEmpty() ? null : grpcMessage.getManagerEmail());
        jpaEntity.setLocation(grpcMessage.getLocation().isEmpty() ? null : grpcMessage.getLocation());
        jpaEntity.setBudget(grpcMessage.getBudget() == 0.0 ? null : grpcMessage.getBudget());
        jpaEntity.setEmployeeCount(grpcMessage.getEmployeeCount() == 0 ? null : grpcMessage.getEmployeeCount());
        jpaEntity.setActive(grpcMessage.getActive());
        return jpaEntity;
    }
} 