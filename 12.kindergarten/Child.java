package org.example;

public class Child {
    private int id;
    private String fullName;
    private String gender;
    private int age;
    private int groupId;

    public Child(int id, String fullName, String gender, int age, int groupId) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.age = age;
        this.groupId = groupId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public int getGroupId() { return groupId; }
    public void setGroupId(int groupId) { this.groupId = groupId; }
}
