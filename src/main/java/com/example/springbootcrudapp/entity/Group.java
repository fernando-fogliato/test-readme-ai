package com.example.springbootcrudapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Group name is required")
    @Size(min = 2, max = 100, message = "Group name must be between 2 and 100 characters")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Size(max = 500, message = "Description must be less than 500 characters")
    @Column(name = "description")
    private String description;

    @Size(max = 50, message = "Group type must be less than 50 characters")
    @Column(name = "group_type")
    private String groupType; // DEPARTMENT, PROJECT, TEAM, ROLE, etc.

    @NotBlank(message = "Owner name is required")
    @Size(min = 2, max = 100, message = "Owner name must be between 2 and 100 characters")
    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "owner_email")
    private String ownerEmail;

    @Min(value = 0, message = "Max members must be positive")
    @Column(name = "max_members")
    private Integer maxMembers;

    @Min(value = 0, message = "Current member count must be positive")
    @Column(name = "current_member_count")
    private Integer currentMemberCount = 0;

    @Column(name = "is_public")
    private Boolean isPublic = true;

    @Column(name = "requires_approval")
    private Boolean requiresApproval = false;

    @Size(max = 200, message = "Tags must be less than 200 characters")
    @Column(name = "tags")
    private String tags; // Comma-separated tags

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdDate;

    @Column(name = "last_activity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date lastActivityDate;

    @Column(name = "active")
    private Boolean active = true;

    // Default constructor
    public Group() {
        this.createdDate = new java.util.Date();
        this.lastActivityDate = new java.util.Date();
    }

    // Constructor with parameters
    public Group(String name, String description, String groupType, String ownerName, 
                String ownerEmail, Integer maxMembers, Boolean isPublic, Boolean requiresApproval,
                String tags, Boolean active) {
        this();
        this.name = name;
        this.description = description;
        this.groupType = groupType;
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
        this.maxMembers = maxMembers;
        this.isPublic = isPublic;
        this.requiresApproval = requiresApproval;
        this.tags = tags;
        this.active = active;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public Integer getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(Integer maxMembers) {
        this.maxMembers = maxMembers;
    }

    public Integer getCurrentMemberCount() {
        return currentMemberCount;
    }

    public void setCurrentMemberCount(Integer currentMemberCount) {
        this.currentMemberCount = currentMemberCount;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Boolean getRequiresApproval() {
        return requiresApproval;
    }

    public void setRequiresApproval(Boolean requiresApproval) {
        this.requiresApproval = requiresApproval;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public java.util.Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.util.Date createdDate) {
        this.createdDate = createdDate;
    }

    public java.util.Date getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(java.util.Date lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", groupType='" + groupType + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", ownerEmail='" + ownerEmail + '\'' +
                ", maxMembers=" + maxMembers +
                ", currentMemberCount=" + currentMemberCount +
                ", isPublic=" + isPublic +
                ", requiresApproval=" + requiresApproval +
                ", tags='" + tags + '\'' +
                ", createdDate=" + createdDate +
                ", lastActivityDate=" + lastActivityDate +
                ", active=" + active +
                '}';
    }
} 