package homework;

import java.util.ArrayDeque;
import java.util.Deque;

public class CustomerReverseOrder {

    private final Deque<Customer> list = new ArrayDeque<>();

    public void add(Customer customer) {
        list.push(customer);
    }

    public Customer take() {
        return list.pop();
    }
}
