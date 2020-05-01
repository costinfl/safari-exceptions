package wrapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

interface ExFunction<E,F> {
  F apply(E e) throws Throwable;
  static <E, F> Function<E, Optional<F>> wrap(ExFunction<E, F> fn) {
    return e -> {
      try {
        return Optional.of(fn.apply(e));
      } catch (Throwable t) {
        return Optional.empty();
      }
    };
  }
}

public class HandleExceptions {

//  public static Optional<Stream<String>> getLines(String name) {
//    try {
//      return Optional.of(Files.lines(Paths.get(name)));
//    } catch (IOException e) {
//      return Optional.empty();
//    }
//  }


  public static void main(String[] args) throws Throwable {
    Stream.of("A.txt", "B.txt", "C.txt")
        .map(ExFunction.wrap(n -> Files.lines(Paths.get(n))))
        .peek(opt -> {
          if (!opt.isPresent()) {
            System.out.println("Oops a file was not found");
          }
        })
        .filter(opt -> opt.isPresent())
        .flatMap(opt -> opt.get())
        .forEach(s -> System.out.println(s));

    System.out.println("-----------------------------------");
    Stream.of("A.txt", "B.txt", "C.txt")
        .map(Either.wrap(n -> Files.lines(Paths.get(n))))
        .peek(e -> e.ifFailure(f -> System.out.println("Didn't work: " + f)))
        .map(e -> e.recover(Either.wrap(n -> Files.lines(Paths.get("F.txt")))))
        .filter(e -> e.isSuccess()) //filter after recovery attempt
        .flatMap(opt -> opt.getSuccess())
        .forEach(s -> System.out.println(s));
  }
}
