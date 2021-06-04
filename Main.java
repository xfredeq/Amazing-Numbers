import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n");

        long n;
        long m;

        do {
            System.out.println("Enter a request:");
            String line = scanner.nextLine();
            String[] arg = line.split(" ");

            switch (arg.length) {
                case 0:
                    System.out.println("Supported requests:\n" +
                            "- enter a natural number to know its properties;\n" +
                            "- enter two natural numbers to obtain the properties of the list:\n" +
                            "  * the first parameter represents a starting number;\n" +
                            "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                            "- two natural numbers and properties to search for;\n" +
                            "- a property preceded by minus must not be present in numbers;\n" +
                            "- separate the parameters with one space;\n" +
                            "- enter 0 to exit.\n");
                    n = -1;
                    break;
                case 1:
                    try {
                        n = Long.parseLong(arg[0]);
                    } catch (NumberFormatException e) {
                        n = -1;
                    }
                    oneParam(n);
                    break;
                case 2:
                    try {
                        n = Long.parseLong(arg[0]);
                    } catch (NumberFormatException e) {
                        n = -1;
                    }
                    try {
                        m = Long.parseLong(arg[1]);
                    } catch (NumberFormatException e) {
                        m=0;
                    }
                    twoParams(n, m);
                    break;
                default:
                    try {
                        n = Long.parseLong(arg[0]);
                    } catch (NumberFormatException e) {
                        n = -1;
                    }
                    try {
                        m = Long.parseLong(arg[1]);
                    } catch (NumberFormatException e) {
                        m=0;
                    }

                    String properties = "";
                    for (int i = 2;i < arg.length; i++) {
                        properties += arg[i] +' ';
                    }

                    moreParams (n, m, properties.toUpperCase(Locale.ROOT));
                    break;
            }
        } while (n != 0);

        System.out.println("Goodbye!");
    }

    private static void moreParams (long n, long m, String properties) {
        boolean czy = true;
        String legalProperties = "BUZZ DUCK PALINDROMIC GAPFUL SPY EVEN ODD SQUARE SUNNY JUMPING HAPPY SAD";
        String[] exclusiveProperties = {"EVEN ODD", "DUCK SPY", "SUNNY SQUARE", "SAD HAPPY"};
        if (n == 0) {
            return;
        }
        if (n < 0) {
            System.out.println("The first parameter should be a natural number or zero.\n");
            czy = false;
        }
        if (m <= 0) {
            System.out.println("The second parameter should be a natural number.\n");
            czy = false;
        }

        for (String property1 : properties.split(" ")) {
            for (String property2 : properties.split(" ")) {
                for (String ex : exclusiveProperties) {
                    if (!property1.equals(property2)) {
                        if (property1.contains(property2) || property2.contains(property1) ||
                                (ex.contains(property1) && ex.contains(property2) ) ||
                                (property1.equals("-ODD") && property2.equals("-EVEN") ) ||
                                (property2.equals("-ODD") && property1.equals("-EVEN") ) ) {
                            System.out.println("The request contains mutually exclusive properties: [" + property1 + ", " + property2 + "]\n" +
                                    "There are no numbers with these properties.\n");
                            czy = false;
                            return;
                        }
                    }
                }
            }
        }

        String badProperties = "";
        for (String property : properties.split(" ")) {
            if (!legalProperties.contains(property) && !legalProperties.contains(property.replace("-", ""))) {
                if (!property.contains("-")) {
                    badProperties += property + ", ";
                } else {
                    badProperties += "-" + property + ", ";
                }
                czy = false;
            }
        }

        if (!badProperties.equals("")) {
            badProperties = badProperties.substring(0, badProperties.length()-2);
            if (badProperties.split(", ").length == 1) {
                System.out.println("The property [" + badProperties + "] is wrong.\n" +
                        "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
            } else {
                System.out.println("The properties [" + badProperties + "] are wrong.\n" +
                        "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
            }
        }

        if (czy) {
            long i = n;
            while (m > 0) {
                boolean c = true;
                for (String property : properties.split(" ")) {
                    switch (property) {
                        case "EVEN":
                        case "-ODD":
                            if (isOdd(i)) {
                                c = false;
                            }
                            break;
                        case "ODD":
                        case "-EVEN":
                            if (!isOdd(i)) {
                                c = false;
                            }
                            break;
                        case "SAD":
                        case "-HAPPY":
                            if (isHappy(i)) {
                                c = false;
                            }
                            break;
                        case "HAPPY":
                        case "-SAD":
                            if (!isHappy(i)) {
                                c = false;
                            }
                            break;
                        case "BUZZ":
                            if (!isBuzz(i)) {
                                c = false;
                            }
                            break;
                        case "-BUZZ":
                            if (isBuzz(i)) {
                                c = false;
                            }
                            break;
                        case "DUCK":
                            if (!isDuck(i)) {
                                c = false;
                            }
                            break;
                        case "-DUCK":
                            if (isDuck(i)) {
                                c = false;
                            }
                            break;
                        case "GAPFUL":
                            if (!isGapful(i)) {
                                c = false;
                            }
                            break;
                        case "-GAPFUL":
                            if (isGapful(i)) {
                                c = false;
                            }
                            break;
                        case "PALINDROMIC":
                            if (!isPalindromic(i)) {
                                c = false;
                            }
                            break;
                        case "-PALINDROMIC":
                            if (isPalindromic(i)) {
                                c = false;
                            }
                            break;
                        case "SPY":
                            if (!isSpy(i)) {
                                c = false;
                            }
                            break;
                        case "-SPY":
                            if (isSpy(i)) {
                                c = false;
                            }
                            break;
                        case "SUNNY":
                            if (!isSunny(i)) {
                                c = false;
                            }
                            break;
                        case "-SUNNY":
                            if (isSunny(i)) {
                                c = false;
                            }
                            break;
                        case "SQUARE":
                            if (!isSquare(i)) {
                                c = false;
                            }
                            break;
                        case "-SQUARE":
                            if (isSquare(i)) {
                                c = false;
                            }
                            break;
                        case "JUMPING":
                            if (!isJumping(i)) {
                                c = false;
                            }
                            break;
                        case "-JUMPING":
                            if (isJumping(i)) {
                                c = false;
                            }
                            break;
                    }
                }

                if (c) {
                    m--;
                    showProperties(i);
                }
                i++;
            }
            System.out.println();
        }
    }

    private static void twoParams (long n, long m) {
        if (n < 0 && m <= 0) {
            System.out.println("The first parameter should be a natural number or zero.\n");
            System.out.println("The second parameter should be a natural number.\n");
        } else if (n < 0) {
            System.out.println("The first parameter should be a natural number or zero.\n");
        } else if (m <= 0) {
            System.out.println("The second parameter should be a natural number.\n");
        } else if (n > 0){
            for (long i = n; i < n + m; i++) {
                showProperties(i);
            }
        }
        System.out.println();
    }

    private static void showProperties (long i)  {
        System.out.println(i + " is " + (isBuzz(i)?"buzz, ":"") +
                (isDuck(i)?"duck, ":"") + (isGapful(i)?"gapful, ":"") +
                (isPalindromic(i)?"palindromic, ":"") + (isSpy(i)?"spy, ":"") +
                (isSunny(i)?"sunny, ":"") + (isSquare(i)?"square, ":"") +
                (isJumping(i)?"jumping, ":"") + (isOdd(i)?"odd, ":"even, ") +
                (isHappy(i)?"happy":"sad") );
    }

    private static void oneParam (long n) {
        if (n < 0) {
            System.out.println("The first parameter should be a natural number or zero.\n");
        } else if (n > 0){
            System.out.println("Properties of " + n);
            System.out.println("even: " + !isOdd(n));
            System.out.println("odd: " + isOdd(n));
            System.out.println("buzz: " + isBuzz(n));
            System.out.println("duck: " + isDuck(n));
            System.out.println("palindromic: " + isPalindromic(n));
            System.out.println("gapful: " + isGapful(n));
            System.out.println("spy: " + isSpy(n));
            System.out.println("sunny: " + isSunny(n));
            System.out.println("square: " + isSquare(n));
            System.out.println("jumping: " + isJumping(n));
            System.out.println("happy: " + isHappy(n));
            System.out.println("sad: " + !isHappy(n));
        }
        System.out.println();
    }

    private static boolean isOdd (long n) {
        return n % 2 != 0;
    }
    private static boolean isBuzz (long n) {
        return n % 7 == 0 || n % 10 == 7;
    }
    private static boolean isDuck (long n) {
        while(n > 0) {
            if (n % 10 == 0) {
                return true;
            }
            n /= 10;
        }
        return false;
    }
    private static boolean isPalindromic (long n) {
        String s = String.valueOf(n);
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
    private static boolean isGapful (long n) {
        String s = String.valueOf(n);
        if (s.length() < 3) {
            return false;
        }
        String d = "";
        d += s.charAt(0);
        d+= s.charAt(s.length()-1);
        int l = Integer.parseInt(d);
        return n % l == 0;
    }
    private static boolean isSpy (long n) {
        int sum = 0;
        int product = 1;
        while (n > 0) {
            sum += n % 10;
            product *= n % 10;
            n /= 10;
        }
        return sum == product;
    }
    private static boolean isSunny (long n) {
        long m = (long) Math.sqrt(n);
        return (m + 1) * (m + 1) == n + 1;
    }
    private static boolean isSquare (long n) {
        long m = (long) Math.sqrt(n);
        return m * m == n;
    }
    private static boolean isJumping (long n) {
        if (n < 10) {
            return true;
        } else {
            int a = (int) (n % 10);
            n /= 10;
            while (n > 0) {
                if (!jmp((int) (n % 10), a)) {
                    return false;
                }
                a = (int) (n % 10);
                n /= 10;
            }
            return true;
        }
    }
    private static boolean isHappy (long n) {
        long k = n;
        ArrayList<Long> lista = new ArrayList<Long>();
        do {
            lista.add(k);
            k = ssd(k);
        } while (k != 1 && !lista.contains(k));
        return k == 1;
    }

    private static long ssd (long n) {
        long sum = 0;
        while (n > 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }
    private static boolean jmp (int a, int b) {
        return a + 1 == b || b + 1 == a;
    }
}
