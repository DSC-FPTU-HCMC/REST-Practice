package dev.dscfptuhcmc.rest.practices.commons;

/**
 * @author quangdatpham
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(long id) {

        super("Could not find resource with id = " + id);
    }
}
