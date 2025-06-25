package com.example.springbootcrudapp.repository;

import com.example.springbootcrudapp.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    // Find group by name
    Optional<Group> findByName(String name);

    // Find groups by name containing (case-insensitive)
    List<Group> findByNameContainingIgnoreCase(String name);

    // Find groups by description containing (case-insensitive)
    List<Group> findByDescriptionContainingIgnoreCase(String description);

    // Find groups by group type
    List<Group> findByGroupType(String groupType);

    // Find groups by group type containing (case-insensitive)
    List<Group> findByGroupTypeContainingIgnoreCase(String groupType);

    // Find groups by owner name
    List<Group> findByOwnerName(String ownerName);

    // Find groups by owner name containing (case-insensitive)
    List<Group> findByOwnerNameContainingIgnoreCase(String ownerName);

    // Find groups by owner email
    List<Group> findByOwnerEmail(String ownerEmail);

    // Find active groups
    List<Group> findByActive(Boolean active);

    // Find public groups
    List<Group> findByIsPublic(Boolean isPublic);

    // Find groups that require approval
    List<Group> findByRequiresApproval(Boolean requiresApproval);

    // Find groups by active status and public visibility
    List<Group> findByActiveAndIsPublic(Boolean active, Boolean isPublic);

    // Find groups by group type and active status
    List<Group> findByGroupTypeAndActive(String groupType, Boolean active);

    // Find groups by owner and active status
    List<Group> findByOwnerNameAndActive(String ownerName, Boolean active);

    // Custom query to find groups by current member count greater than
    @Query("SELECT g FROM Group g WHERE g.currentMemberCount > :memberCount")
    List<Group> findByCurrentMemberCountGreaterThan(@Param("memberCount") Integer memberCount);

    // Custom query to find groups by current member count less than
    @Query("SELECT g FROM Group g WHERE g.currentMemberCount < :memberCount")
    List<Group> findByCurrentMemberCountLessThan(@Param("memberCount") Integer memberCount);

    // Custom query to find groups with available capacity
    @Query("SELECT g FROM Group g WHERE g.maxMembers IS NULL OR g.currentMemberCount < g.maxMembers")
    List<Group> findGroupsWithAvailableCapacity();

    // Custom query to find groups by max members greater than
    @Query("SELECT g FROM Group g WHERE g.maxMembers > :maxMembers")
    List<Group> findByMaxMembersGreaterThan(@Param("maxMembers") Integer maxMembers);

    // Custom query to find groups created after date
    @Query("SELECT g FROM Group g WHERE g.createdDate > :date")
    List<Group> findGroupsCreatedAfter(@Param("date") Date date);

    // Custom query to find groups with recent activity
    @Query("SELECT g FROM Group g WHERE g.lastActivityDate > :date")
    List<Group> findGroupsWithRecentActivity(@Param("date") Date date);

    // Custom query to find groups by tags containing
    @Query("SELECT g FROM Group g WHERE g.tags LIKE %:tag%")
    List<Group> findByTagsContaining(@Param("tag") String tag);

    // Check if group exists by name
    boolean existsByName(String name);

    // Find groups ordered by name
    List<Group> findAllByOrderByNameAsc();

    // Find groups ordered by creation date (newest first)
    List<Group> findAllByOrderByCreatedDateDesc();

    // Find groups ordered by member count (highest first)
    List<Group> findAllByOrderByCurrentMemberCountDesc();

    // Find groups ordered by last activity (most recent first)
    List<Group> findAllByOrderByLastActivityDateDesc();

    // Custom query to find groups by multiple criteria
    @Query("SELECT g FROM Group g WHERE " +
           "(:groupType IS NULL OR g.groupType = :groupType) AND " +
           "(:isPublic IS NULL OR g.isPublic = :isPublic) AND " +
           "(:active IS NULL OR g.active = :active)")
    List<Group> findGroupsByCriteria(@Param("groupType") String groupType,
                                   @Param("isPublic") Boolean isPublic,
                                   @Param("active") Boolean active);
} 