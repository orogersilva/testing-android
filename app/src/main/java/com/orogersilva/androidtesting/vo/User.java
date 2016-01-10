package com.orogersilva.androidtesting.vo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by azevedor on 10/01/2016.
 */
public class User extends RealmObject {

    // region FIELDS

    @PrimaryKey
    private String name;
    private String age;
    private String city;

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
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    private void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    private void setCity(String city) {
        this.city = city;
    }

    // endregion
}
