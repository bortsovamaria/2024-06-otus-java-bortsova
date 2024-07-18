package homework;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import static java.util.Objects.isNull;

public class CustomerService {
    NavigableMap<Customer, String> map = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        return getCopy(map.firstEntry());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return getCopy(map.higherEntry(customer));
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }

    private Map.Entry<Customer, String> getCopy(Map.Entry<Customer, String> entry) {
        if (isNull(entry)) {
            return null;
        }
        var customer = entry.getKey();
        var customerCopy = new Customer(customer.getId(), customer.getName(), customer.getScores());
        return new AbstractMap.SimpleEntry<>(customerCopy, entry.getValue());
    }
}
