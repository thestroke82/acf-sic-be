package little.old.me.shared.infra;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class BeansFactory {
    @Produces
    @ApplicationScoped
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
