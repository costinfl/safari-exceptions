package student;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UseSuperIterable {
  public static void main(String[] args) {
    SuperIterable<String> sis = new SuperIterable<>(
        Arrays.asList("Fred", "Jim", "Sheila")
    );

//    sis
//        .map(s -> s.toUpperCase())
//        .filter(s -> s.length() > 3)
//        .show();


    Map<String, String> names = new HashMap<>();
    names.put("Fred", "Jones");

    String firstName = /* complex lookup producing results */ "Freddy";
//    String lastName = names.get(firstName);
//    String message = "Dear " + lastName.toUpperCase();
//    System.out.println(message);

    new SuperIterable<>(Arrays.asList(names))
        .map(m -> m.get(firstName))
        .map(n -> "Dear " + n.toUpperCase())
        .show();
    Optional.of(names)
        .map(m -> m.get(firstName))
        .map(n -> "Dear " + n.toUpperCase())
        .ifPresent(s -> System.out.println(s));
  }
}
