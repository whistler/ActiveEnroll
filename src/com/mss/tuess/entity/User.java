package com.mss.tuess.entity;

import java.sql.SQLException;

public abstract class User {

    private String firstName = "";
    private String lastName = "";
    private String address = "";
    private String city = "";
    private String email = "";
    private String country = "";
    private String state = "";
    private String zipcode = "";
    private String phone = "";
    private String password = "";

    public abstract void fetch(int id) throws SQLException;

    public abstract void insert() throws SQLException;

    public abstract void delete() throws SQLException;

    public abstract void update() throws SQLException;

    public abstract void setID(int id);

    public abstract int getID();

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * @return the state
     */
    public String getState() {
        return state;
    }
        /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }



    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Returns password for the user
     *
     * @return password
     */
    public String getPassword() {
        return this.password;
    }
    
    /**
    * @param password the password to set
    */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns Student if login and password match
     *
     * @param userID id of the user
     * @param password password of the user
     * @param userType type of the user
     * @return User with that matches id and password
     * @throws SQLException
     */
    public static User login(int userID, String password, String userType) throws SQLException {
        User user;
        if (userType.equals("Student")) {
            user = new Student();
        } else if (userType.equals("Instructor")) {
            user = new Instructor();
        } else {
            user = new Administrator();
        }

        user.fetch(userID);
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }
}
