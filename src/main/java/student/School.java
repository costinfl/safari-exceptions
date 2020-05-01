package student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

// "Behavior" ...  a method??? Pass an object, and USE it for it's method(s)
// Interface describing
// boolean test(Student s);
// Predicate<Student>
public class School {
  public static <E> List<E> getByPred(Iterable<E> ls,
                                      Predicate<E> pred) {
    List<E> out = new ArrayList<>();
    for (E s : ls) {
      if (pred.test(s)) {
        out.add(s);
      }
    }
    return out;
  }

  public static <E> void show(Iterable<E> ls) {
    for (E s : ls) {
      System.out.println("> " + s);
    }
    System.out.println("---------------------");
  }

  public static void main(String[] args) {
    List<Student> roster = Arrays.asList(
        new Student("Fred", 75, "Math", "Physics"),
        new Student("Jim", 55, "Art"),
        new Student("Sheila", 90, "Math", "Physics",
            "Astrophysics", "Quantum Mechanics")
    );
    show(roster);
    show(getByPred(roster, s -> s.getGrade() > 70));
    show(getByPred(roster, s -> s.getCourses().size() < 4));

    show(getByPred(Arrays.asList("Help", "Longer", "Me"), s -> s.length() > 2));
  }
}
