package com.orogersilva.androidtesting.vo;

/**
 * Created by azevedor on 10/01/2016.
 */
public class User {

    // region FIELDS

    private String mName;
    private String mAge;
    private String mCity;

    // endregion

    // region CONSTRUCTORS

    public User(String name, String age, String city) {

        setName(name);
        setAge(age);
        setCity(city);
    }

    // endregion

    // region GETTERS AND SETTERS

    public String getName() {
        return mName;
    }

    private void setName(String name) {
        mName = name;
    }

    public String getAge() {
        return mAge;
    }

    private void setAge(String age) {
        mAge = age;
    }

    public String getCity() {
        return mCity;
    }

    private void setCity(String city) {
        mCity = city;
    }

    // endregion
}
