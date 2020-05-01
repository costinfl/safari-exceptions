package wrapper;

import java.util.function.Consumer;
import java.util.function.Function;

public class Either<L, R> {
  private L left;
  private R right;
  private Either() {}
  public static <L, R> Either<L, R> success(R r) {
    Either<L, R> self = new Either();
    self.right = r;
    return self;
  }

  public static <L, R> Either<L, R> failure(L l) {
    Either<L, R> self = new Either();
    self.left = l;
    return self;
  }

  public static <E, F> Function<E, Either<Throwable, F>> wrap(
      ExFunction<E, F> fn) {
    return e -> {
      try {
        return Either.success(fn.apply(e));
      } catch (Throwable t) {
        return Either.failure(t);
      }
    };
  }

  public <R2> Either<L, R2> mapSuccess(Function<R, R2> op) {
    if (right != null) {
      return Either.success(op.apply(right));
    } else return Either.failure(left);
  }

  public Either<L, R> recover(Function<L, R> recovery) {
    if (left != null) {
      return Either.success(recovery.apply(left));
    } else return this;
  }
  public boolean isSuccess() {
    return right != null;
  }

  public void ifSuccess(Consumer<R> op) {
    if (right != null) {
      op.accept(right);
    }
  }

  public L getFailure() {
    return this.left;
  }

  public void ifFailure(Consumer<L> op) {
    if (left != null) {
      op.accept(left);
    }
  }

  public R getSuccess() {
    return this.right;
  }
}
