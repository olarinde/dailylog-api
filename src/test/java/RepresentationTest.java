import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.koweg.demo.dailylog.api.model.Registration;
import org.koweg.demo.dailylog.api.model.Registrations;
import org.koweg.demo.dailylog.api.model.Subscription;
import org.koweg.demo.dailylog.api.model.Subscription.ServiceType;
import org.koweg.demo.dailylog.api.model.Subscriptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RepresentationTest {

    @Test
    public void test() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        
        Set<String> currencyPairs = new HashSet<String>();
        currencyPairs.add("GBP");
        currencyPairs.add("USD");
        
        Set<Subscription> subscriptionSet = new HashSet<Subscription>();
        subscriptionSet.add((new Subscription()).withServiceType(ServiceType.CURR_LIVE_RATES).withCurrencyPairs(currencyPairs));
        
        Subscriptions subs = new Subscriptions().withSize(1).withSubscriptions(subscriptionSet);
        
        Registration reg = new Registration();
        reg.withEmail("jane.doe@test.com").withFirstname("Jane").withLastname("Doe").withUserId("123").withSubscriptions(subs);

        List<Registration> registrationList = new ArrayList<Registration>();
        registrationList.add(reg);
        
        reg = new Registration().withEmail("max.mustermann@test.com").withFirstname("Max").withLastname("Mustermann").withUserId("345").withSubscriptions(subs);
        registrationList.add(reg);
        
        Registrations  registrations = new Registrations().withSize(2).withRegistrations(registrationList);
        
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(registrations));
    }

}
