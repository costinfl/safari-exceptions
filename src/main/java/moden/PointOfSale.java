package moden;

import java.io.IOException;
import java.net.Socket;

class ModemDidNotConnectException extends Exception {
}

class ModemHelper {
  public static void dialModem(int num) throws ModemDidNotConnectException {
  }
}

public class PointOfSale {
  private static final boolean USE_MODEM = false;

  public static boolean getPaymentFromCard(int amount, int card)
      throws ModemDidNotConnectException, IOException {
//      throws ModemDidNotConnectException {
    int retriesLeft = 3;
    boolean success = false;
    while (!success && retriesLeft > 0) {
      try {
        if (USE_MODEM) {
          ModemHelper.dialModem(123);
        } else {
          Socket s = new Socket("", 8000);
        }
        // get payment...
        return true;
      } catch (ModemDidNotConnectException | IOException e) {
        if (--retriesLeft == 0) {
          throw e;
        }
      }
    }
    return false;
  }

  public static void sellGoods(String item, int card) /*throws ...*/ {
    // get price
    try {
      if (getPaymentFromCard(10, card)) {
        // let them take it away
      }
    } catch (IOException | ModemDidNotConnectException e) {
      System.out.println("Jiggle connections...");
      ;
    }
  }
}
