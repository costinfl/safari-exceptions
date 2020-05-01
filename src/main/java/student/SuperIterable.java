package student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;
  public SuperIterable(Iterable<E> target) {
    this.self = target;
  }

  public SuperIterable<E> filter(Predicate<E> pred) {
    List<E> out = new ArrayList<>();
    for (E s : self) {
      if (pred.test(s)) {
        out.add(s);
      }
    }
    return new SuperIterable<>(out);
  }

  public <F> SuperIterable<F> map(Function<E, F> op) {
    List<F> out = new ArrayList<>();
    for (E e : self) {
      F f = op.apply(e);
      if (f != null) {
        out.add(f);
      }
    }
    return new SuperIterable<>(out);
  }

  public void show() {
    for (E s : self) {
      System.out.println("> " + s);
    }
    System.out.println("---------------------");
  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }
}
