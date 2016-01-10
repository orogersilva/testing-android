package com.orogersilva.androidtesting.utils.exception;

/**
 * Created by azevedor on 10/01/2016.
 */
public class InvalidStringException extends Exception {

    // region CONSTRCUTORS

    public InvalidStringException() {}

    public InvalidStringException(String detailMessage) {
        super(detailMessage);
    }

    // endregion
}
