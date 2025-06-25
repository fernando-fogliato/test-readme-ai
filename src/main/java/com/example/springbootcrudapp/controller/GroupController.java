package com.example.springbootcrudapp.controller;

import com.example.springbootcrudapp.entity.Group;
import com.example.springbootcrudapp.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin(origins = "*")
public class GroupController {

    @Autowired
    private GroupService groupService;

    // GET /api/groups - Get all groups
    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/{id} - Get group by ID
    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable Long id) {
        Optional<Group> group = groupService.getGroupById(id);
        if (group.isPresent()) {
            return new ResponseEntity<>(group.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST /api/groups - Create a new group
    @PostMapping
    public ResponseEntity<?> createGroup(@Valid @RequestBody Group group) {
        try {
            Group savedGroup = groupService.createGroup(group);
            return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // PUT /api/groups/{id} - Update group
    @PutMapping("/{id}")
    public ResponseEntity<?> updateGroup(@PathVariable Long id, @Valid @RequestBody Group groupDetails) {
        try {
            Group updatedGroup = groupService.updateGroup(id, groupDetails);
            return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // DELETE /api/groups/{id} - Delete group
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
        try {
            groupService.deleteGroup(id);
            return new ResponseEntity<>("Group deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // GET /api/groups/name/{name} - Get group by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Group> getGroupByName(@PathVariable String name) {
        Optional<Group> group = groupService.getGroupByName(name);
        if (group.isPresent()) {
            return new ResponseEntity<>(group.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // GET /api/groups/search/name?name={name} - Search groups by name
    @GetMapping("/search/name")
    public ResponseEntity<List<Group>> searchGroupsByName(@RequestParam String name) {
        List<Group> groups = groupService.searchGroupsByName(name);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/search/description?description={description} - Search groups by description
    @GetMapping("/search/description")
    public ResponseEntity<List<Group>> searchGroupsByDescription(@RequestParam String description) {
        List<Group> groups = groupService.searchGroupsByDescription(description);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/type/{type} - Get groups by type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Group>> getGroupsByType(@PathVariable String type) {
        List<Group> groups = groupService.getGroupsByType(type);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/search/type?type={type} - Search groups by type
    @GetMapping("/search/type")
    public ResponseEntity<List<Group>> searchGroupsByType(@RequestParam String type) {
        List<Group> groups = groupService.searchGroupsByType(type);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/owner/{ownerName} - Get groups by owner name
    @GetMapping("/owner/{ownerName}")
    public ResponseEntity<List<Group>> getGroupsByOwnerName(@PathVariable String ownerName) {
        List<Group> groups = groupService.getGroupsByOwnerName(ownerName);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/search/owner?owner={owner} - Search groups by owner name
    @GetMapping("/search/owner")
    public ResponseEntity<List<Group>> searchGroupsByOwnerName(@RequestParam String owner) {
        List<Group> groups = groupService.searchGroupsByOwnerName(owner);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/owner-email/{email} - Get groups by owner email
    @GetMapping("/owner-email/{email}")
    public ResponseEntity<List<Group>> getGroupsByOwnerEmail(@PathVariable String email) {
        List<Group> groups = groupService.getGroupsByOwnerEmail(email);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/active - Get active groups
    @GetMapping("/active")
    public ResponseEntity<List<Group>> getActiveGroups() {
        List<Group> groups = groupService.getActiveGroups();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/inactive - Get inactive groups
    @GetMapping("/inactive")
    public ResponseEntity<List<Group>> getInactiveGroups() {
        List<Group> groups = groupService.getInactiveGroups();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/public - Get public groups
    @GetMapping("/public")
    public ResponseEntity<List<Group>> getPublicGroups() {
        List<Group> groups = groupService.getPublicGroups();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/private - Get private groups
    @GetMapping("/private")
    public ResponseEntity<List<Group>> getPrivateGroups() {
        List<Group> groups = groupService.getPrivateGroups();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/requires-approval - Get groups that require approval
    @GetMapping("/requires-approval")
    public ResponseEntity<List<Group>> getGroupsRequiringApproval() {
        List<Group> groups = groupService.getGroupsRequiringApproval();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/no-approval - Get groups that don't require approval
    @GetMapping("/no-approval")
    public ResponseEntity<List<Group>> getGroupsNotRequiringApproval() {
        List<Group> groups = groupService.getGroupsNotRequiringApproval();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/filter/active-public?active={active}&public={public} - Get groups by active status and public visibility
    @GetMapping("/filter/active-public")
    public ResponseEntity<List<Group>> getGroupsByActiveAndPublic(
            @RequestParam Boolean active, 
            @RequestParam Boolean publicGroup) {
        List<Group> groups = groupService.getGroupsByActiveAndPublic(active, publicGroup);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/filter/type-active?type={type}&active={active} - Get groups by type and active status
    @GetMapping("/filter/type-active")
    public ResponseEntity<List<Group>> getGroupsByTypeAndActive(
            @RequestParam String type, 
            @RequestParam Boolean active) {
        List<Group> groups = groupService.getGroupsByTypeAndActive(type, active);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/filter/owner-active?owner={owner}&active={active} - Get groups by owner and active status
    @GetMapping("/filter/owner-active")
    public ResponseEntity<List<Group>> getGroupsByOwnerAndActive(
            @RequestParam String owner, 
            @RequestParam Boolean active) {
        List<Group> groups = groupService.getGroupsByOwnerAndActive(owner, active);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/members/greater-than?count={count} - Get groups with member count greater than
    @GetMapping("/members/greater-than")
    public ResponseEntity<List<Group>> getGroupsWithMemberCountGreaterThan(@RequestParam Integer count) {
        List<Group> groups = groupService.getGroupsWithMemberCountGreaterThan(count);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/members/less-than?count={count} - Get groups with member count less than
    @GetMapping("/members/less-than")
    public ResponseEntity<List<Group>> getGroupsWithMemberCountLessThan(@RequestParam Integer count) {
        List<Group> groups = groupService.getGroupsWithMemberCountLessThan(count);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/available-capacity - Get groups with available capacity
    @GetMapping("/available-capacity")
    public ResponseEntity<List<Group>> getGroupsWithAvailableCapacity() {
        List<Group> groups = groupService.getGroupsWithAvailableCapacity();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/max-members/greater-than?count={count} - Get groups by max members greater than
    @GetMapping("/max-members/greater-than")
    public ResponseEntity<List<Group>> getGroupsByMaxMembersGreaterThan(@RequestParam Integer count) {
        List<Group> groups = groupService.getGroupsByMaxMembersGreaterThan(count);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/created-after?date={date} - Get groups created after date
    @GetMapping("/created-after")
    public ResponseEntity<List<Group>> getGroupsCreatedAfter(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<Group> groups = groupService.getGroupsCreatedAfter(date);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/recent-activity?date={date} - Get groups with recent activity
    @GetMapping("/recent-activity")
    public ResponseEntity<List<Group>> getGroupsWithRecentActivity(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<Group> groups = groupService.getGroupsWithRecentActivity(date);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/tag/{tag} - Get groups by tag
    @GetMapping("/tag/{tag}")
    public ResponseEntity<List<Group>> getGroupsByTag(@PathVariable String tag) {
        List<Group> groups = groupService.getGroupsByTag(tag);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/ordered/name - Get all groups ordered by name
    @GetMapping("/ordered/name")
    public ResponseEntity<List<Group>> getAllGroupsOrderedByName() {
        List<Group> groups = groupService.getAllGroupsOrderedByName();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/ordered/creation-date - Get all groups ordered by creation date (newest first)
    @GetMapping("/ordered/creation-date")
    public ResponseEntity<List<Group>> getAllGroupsOrderedByCreationDate() {
        List<Group> groups = groupService.getAllGroupsOrderedByCreationDate();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/ordered/member-count - Get all groups ordered by member count (highest first)
    @GetMapping("/ordered/member-count")
    public ResponseEntity<List<Group>> getAllGroupsOrderedByMemberCount() {
        List<Group> groups = groupService.getAllGroupsOrderedByMemberCount();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/ordered/activity - Get all groups ordered by last activity (most recent first)
    @GetMapping("/ordered/activity")
    public ResponseEntity<List<Group>> getAllGroupsOrderedByActivity() {
        List<Group> groups = groupService.getAllGroupsOrderedByActivity();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // GET /api/groups/criteria?type={type}&public={public}&active={active} - Get groups by multiple criteria
    @GetMapping("/criteria")
    public ResponseEntity<List<Group>> getGroupsByCriteria(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Boolean publicGroup,
            @RequestParam(required = false) Boolean active) {
        List<Group> groups = groupService.getGroupsByCriteria(type, publicGroup, active);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // PUT /api/groups/{id}/activate - Activate group
    @PutMapping("/{id}/activate")
    public ResponseEntity<?> activateGroup(@PathVariable Long id) {
        try {
            Group group = groupService.activateGroup(id);
            return new ResponseEntity<>(group, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // PUT /api/groups/{id}/deactivate - Deactivate group
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivateGroup(@PathVariable Long id) {
        try {
            Group group = groupService.deactivateGroup(id);
            return new ResponseEntity<>(group, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // PUT /api/groups/{id}/make-public - Make group public
    @PutMapping("/{id}/make-public")
    public ResponseEntity<?> makeGroupPublic(@PathVariable Long id) {
        try {
            Group group = groupService.makeGroupPublic(id);
            return new ResponseEntity<>(group, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // PUT /api/groups/{id}/make-private - Make group private
    @PutMapping("/{id}/make-private")
    public ResponseEntity<?> makeGroupPrivate(@PathVariable Long id) {
        try {
            Group group = groupService.makeGroupPrivate(id);
            return new ResponseEntity<>(group, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // PUT /api/groups/{id}/member-count - Update member count
    @PutMapping("/{id}/member-count")
    public ResponseEntity<?> updateMemberCount(@PathVariable Long id, @RequestBody MemberCountRequest request) {
        try {
            Group group = groupService.updateMemberCount(id, request.getMemberCount());
            return new ResponseEntity<>(group, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // PUT /api/groups/{id}/add-member - Add member to group
    @PutMapping("/{id}/add-member")
    public ResponseEntity<?> addMemberToGroup(@PathVariable Long id) {
        try {
            Group group = groupService.addMemberToGroup(id);
            return new ResponseEntity<>(group, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // PUT /api/groups/{id}/remove-member - Remove member from group
    @PutMapping("/{id}/remove-member")
    public ResponseEntity<?> removeMemberFromGroup(@PathVariable Long id) {
        try {
            Group group = groupService.removeMemberFromGroup(id);
            return new ResponseEntity<>(group, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // PUT /api/groups/{id}/tags - Update group tags
    @PutMapping("/{id}/tags")
    public ResponseEntity<?> updateGroupTags(@PathVariable Long id, @RequestBody TagsRequest request) {
        try {
            Group group = groupService.updateGroupTags(id, request.getTags());
            return new ResponseEntity<>(group, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // PUT /api/groups/{id}/update-activity - Update last activity date
    @PutMapping("/{id}/update-activity")
    public ResponseEntity<?> updateLastActivity(@PathVariable Long id) {
        try {
            Group group = groupService.updateLastActivity(id);
            return new ResponseEntity<>(group, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Helper classes for request bodies
    public static class MemberCountRequest {
        private Integer memberCount;

        public Integer getMemberCount() {
            return memberCount;
        }

        public void setMemberCount(Integer memberCount) {
            this.memberCount = memberCount;
        }
    }

    public static class TagsRequest {
        private String tags;

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }
    }
} 