package little.old.me.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import little.old.me.domain.Person;
import little.old.me.service.PersonService;

import java.util.List;
import java.util.Optional;

@Path("/api/people")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonService personService;

    @GET
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @GET
    @Path("/{id}")
    public Response getPersonById(@PathParam("id") Long id) {
        Optional<Person> person = personService.getPersonById(id);
        return person.map(Response::ok)
                     .orElse(Response.status(Response.Status.NOT_FOUND))
                     .build();
    }

    @POST
    public Response createPerson(Person person) {
        Person createdPerson = personService.createPerson(person);
        return Response.status(Response.Status.CREATED).entity(createdPerson).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") Long id) {
        boolean deleted = personService.deletePerson(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
