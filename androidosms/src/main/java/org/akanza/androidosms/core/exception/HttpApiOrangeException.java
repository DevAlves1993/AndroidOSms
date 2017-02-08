package org.akanza.androidosms.core.exception;

import org.akanza.androidosms.entity.apiorangeresponse.ResponseError;

/**
 * Created by user on 05/02/2017.
 */

public class HttpApiOrangeException extends Exception
{
    private ResponseError error;

    public HttpApiOrangeException(ResponseError error)
    {
        this.error = error;
    }

    public ResponseError getError()
    {
        return error;
    }

    @Override
    public String getMessage()
    {
        return error.getMessage();
    }
}
