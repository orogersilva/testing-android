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

    public User() {}

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

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // endregion

    // TODO: 2/7/2016 Override equals method when Realm to allow.
}
