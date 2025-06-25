package com.example.springbootcrudapp.service;

import com.example.springbootcrudapp.entity.Group;
import com.example.springbootcrudapp.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    // Get all groups
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    // Get group by ID
    public Optional<Group> getGroupById(Long id) {
        return groupRepository.findById(id);
    }

    // Create a new group
    public Group createGroup(Group group) {
        // Check if group name already exists
        if (groupRepository.existsByName(group.getName())) {
            throw new RuntimeException("Group name already exists: " + group.getName());
        }
        
        // Set creation and activity dates
        Date now = new Date();
        group.setCreatedDate(now);
        group.setLastActivityDate(now);
        
        // Initialize member count if not set
        if (group.getCurrentMemberCount() == null) {
            group.setCurrentMemberCount(0);
        }
        
        return groupRepository.save(group);
    }

    // Update group
    public Group updateGroup(Long id, Group groupDetails) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));

        // Check if name is being changed and if it already exists
        if (!group.getName().equals(groupDetails.getName()) && 
            groupRepository.existsByName(groupDetails.getName())) {
            throw new RuntimeException("Group name already exists: " + groupDetails.getName());
        }

        group.setName(groupDetails.getName());
        group.setDescription(groupDetails.getDescription());
        group.setGroupType(groupDetails.getGroupType());
        group.setOwnerName(groupDetails.getOwnerName());
        group.setOwnerEmail(groupDetails.getOwnerEmail());
        group.setMaxMembers(groupDetails.getMaxMembers());
        group.setCurrentMemberCount(groupDetails.getCurrentMemberCount());
        group.setIsPublic(groupDetails.getIsPublic());
        group.setRequiresApproval(groupDetails.getRequiresApproval());
        group.setTags(groupDetails.getTags());
        group.setActive(groupDetails.getActive());
        
        // Update last activity date
        group.setLastActivityDate(new Date());

        return groupRepository.save(group);
    }

    // Delete group
    public void deleteGroup(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        groupRepository.delete(group);
    }

    // Find group by name
    public Optional<Group> getGroupByName(String name) {
        return groupRepository.findByName(name);
    }

    // Search groups by name
    public List<Group> searchGroupsByName(String name) {
        return groupRepository.findByNameContainingIgnoreCase(name);
    }

    // Search groups by description
    public List<Group> searchGroupsByDescription(String description) {
        return groupRepository.findByDescriptionContainingIgnoreCase(description);
    }

    // Find groups by type
    public List<Group> getGroupsByType(String groupType) {
        return groupRepository.findByGroupType(groupType);
    }

    // Search groups by type
    public List<Group> searchGroupsByType(String groupType) {
        return groupRepository.findByGroupTypeContainingIgnoreCase(groupType);
    }

    // Find groups by owner name
    public List<Group> getGroupsByOwnerName(String ownerName) {
        return groupRepository.findByOwnerName(ownerName);
    }

    // Search groups by owner name
    public List<Group> searchGroupsByOwnerName(String ownerName) {
        return groupRepository.findByOwnerNameContainingIgnoreCase(ownerName);
    }

    // Find groups by owner email
    public List<Group> getGroupsByOwnerEmail(String ownerEmail) {
        return groupRepository.findByOwnerEmail(ownerEmail);
    }

    // Find active groups
    public List<Group> getActiveGroups() {
        return groupRepository.findByActive(true);
    }

    // Find inactive groups
    public List<Group> getInactiveGroups() {
        return groupRepository.findByActive(false);
    }

    // Find public groups
    public List<Group> getPublicGroups() {
        return groupRepository.findByIsPublic(true);
    }

    // Find private groups
    public List<Group> getPrivateGroups() {
        return groupRepository.findByIsPublic(false);
    }

    // Find groups that require approval
    public List<Group> getGroupsRequiringApproval() {
        return groupRepository.findByRequiresApproval(true);
    }

    // Find groups that don't require approval
    public List<Group> getGroupsNotRequiringApproval() {
        return groupRepository.findByRequiresApproval(false);
    }

    // Find groups by active status and public visibility
    public List<Group> getGroupsByActiveAndPublic(Boolean active, Boolean isPublic) {
        return groupRepository.findByActiveAndIsPublic(active, isPublic);
    }

    // Find groups by type and active status
    public List<Group> getGroupsByTypeAndActive(String groupType, Boolean active) {
        return groupRepository.findByGroupTypeAndActive(groupType, active);
    }

    // Find groups by owner and active status
    public List<Group> getGroupsByOwnerAndActive(String ownerName, Boolean active) {
        return groupRepository.findByOwnerNameAndActive(ownerName, active);
    }

    // Find groups with member count greater than
    public List<Group> getGroupsWithMemberCountGreaterThan(Integer memberCount) {
        return groupRepository.findByCurrentMemberCountGreaterThan(memberCount);
    }

    // Find groups with member count less than
    public List<Group> getGroupsWithMemberCountLessThan(Integer memberCount) {
        return groupRepository.findByCurrentMemberCountLessThan(memberCount);
    }

    // Find groups with available capacity
    public List<Group> getGroupsWithAvailableCapacity() {
        return groupRepository.findGroupsWithAvailableCapacity();
    }

    // Find groups by max members greater than
    public List<Group> getGroupsByMaxMembersGreaterThan(Integer maxMembers) {
        return groupRepository.findByMaxMembersGreaterThan(maxMembers);
    }

    // Find groups created after date
    public List<Group> getGroupsCreatedAfter(Date date) {
        return groupRepository.findGroupsCreatedAfter(date);
    }

    // Find groups with recent activity
    public List<Group> getGroupsWithRecentActivity(Date date) {
        return groupRepository.findGroupsWithRecentActivity(date);
    }

    // Find groups by tags
    public List<Group> getGroupsByTag(String tag) {
        return groupRepository.findByTagsContaining(tag);
    }

    // Get all groups ordered by name
    public List<Group> getAllGroupsOrderedByName() {
        return groupRepository.findAllByOrderByNameAsc();
    }

    // Get all groups ordered by creation date (newest first)
    public List<Group> getAllGroupsOrderedByCreationDate() {
        return groupRepository.findAllByOrderByCreatedDateDesc();
    }

    // Get all groups ordered by member count (highest first)
    public List<Group> getAllGroupsOrderedByMemberCount() {
        return groupRepository.findAllByOrderByCurrentMemberCountDesc();
    }

    // Get all groups ordered by last activity (most recent first)
    public List<Group> getAllGroupsOrderedByActivity() {
        return groupRepository.findAllByOrderByLastActivityDateDesc();
    }

    // Find groups by multiple criteria
    public List<Group> getGroupsByCriteria(String groupType, Boolean isPublic, Boolean active) {
        return groupRepository.findGroupsByCriteria(groupType, isPublic, active);
    }

    // Activate group
    public Group activateGroup(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        group.setActive(true);
        group.setLastActivityDate(new Date());
        return groupRepository.save(group);
    }

    // Deactivate group
    public Group deactivateGroup(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        group.setActive(false);
        group.setLastActivityDate(new Date());
        return groupRepository.save(group);
    }

    // Make group public
    public Group makeGroupPublic(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        group.setIsPublic(true);
        group.setLastActivityDate(new Date());
        return groupRepository.save(group);
    }

    // Make group private
    public Group makeGroupPrivate(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        group.setIsPublic(false);
        group.setLastActivityDate(new Date());
        return groupRepository.save(group);
    }

    // Update member count
    public Group updateMemberCount(Long id, Integer memberCount) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        
        // Validate member count against max members if set
        if (group.getMaxMembers() != null && memberCount > group.getMaxMembers()) {
            throw new RuntimeException("Member count cannot exceed max members limit: " + group.getMaxMembers());
        }
        
        group.setCurrentMemberCount(memberCount);
        group.setLastActivityDate(new Date());
        return groupRepository.save(group);
    }

    // Add member to group
    public Group addMemberToGroup(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        
        int currentCount = group.getCurrentMemberCount() != null ? group.getCurrentMemberCount() : 0;
        
        // Check if group has capacity
        if (group.getMaxMembers() != null && currentCount >= group.getMaxMembers()) {
            throw new RuntimeException("Group has reached maximum capacity: " + group.getMaxMembers());
        }
        
        group.setCurrentMemberCount(currentCount + 1);
        group.setLastActivityDate(new Date());
        return groupRepository.save(group);
    }

    // Remove member from group
    public Group removeMemberFromGroup(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        
        int currentCount = group.getCurrentMemberCount() != null ? group.getCurrentMemberCount() : 0;
        
        if (currentCount <= 0) {
            throw new RuntimeException("Group has no members to remove");
        }
        
        group.setCurrentMemberCount(currentCount - 1);
        group.setLastActivityDate(new Date());
        return groupRepository.save(group);
    }

    // Update group tags
    public Group updateGroupTags(Long id, String tags) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        group.setTags(tags);
        group.setLastActivityDate(new Date());
        return groupRepository.save(group);
    }

    // Update last activity date
    public Group updateLastActivity(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        group.setLastActivityDate(new Date());
        return groupRepository.save(group);
    }
} 