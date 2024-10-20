package little.old.me.shared.infra;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class BeansFactory {
    @Produces
    @Singleton
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
