package moden;

class ModemDidNotConnectException extends Exception {}

class ModemHelper {
  public static void dialModem(int num) throws ModemDidNotConnectException {}
}

public class PointOfSale {
  public static boolean getPaymentFromCard(int amount, int card)
      throws ModemDidNotConnectException{
    int retriesLeft = 3;
    boolean success = false;
    while (!success && retriesLeft > 0) {
      try {
        ModemHelper.dialModem(123);
        // get payment...
        return true;
      } catch (ModemDidNotConnectException e) {
        if (--retriesLeft == 0) {
          throw e;
        }
      }
    }
    return false;
  }

  public static void sellGoods(String item, int card) {
    // get price
    try {
      if (getPaymentFromCard(10, card)) {
        // let them take it away
      }
    } catch (ModemDidNotConnectException e) {
      System.out.println("Jiggle connections...");;
    }
  }
}
