import java.math.BigInteger;
import java.util.*;

public class PolynomialSecret {
    static class Point {
        BigInteger x;
        BigInteger y;
        
        Point(BigInteger x, BigInteger y) {
            this.x = x;
            this.y = y;
        }
    }
    
    // Function to decode value from given base to decimal
    private static BigInteger decodeValue(String value, String base) {
        return new BigInteger(value, Integer.parseInt(base));
    }
    
    // Manual parsing of test cases
    private static List<Point> parseTestCase(int n, int k, Map<Integer, String[]> points) {
        List<Point> result = new ArrayList<>();
        
        for (int i = 1; i <= n && result.size() < k; i++) {
            String[] point = points.get(i);
            if (point != null) {
                BigInteger x = BigInteger.valueOf(i);
                BigInteger y = decodeValue(point[1], point[0]);
                result.add(new Point(x, y));
            }
        }
        return result;
    }
    
    // Lagrange interpolation to find constant term
    private static BigInteger findConstantTerm(List<Point> points) {
        BigInteger result = BigInteger.ZERO;
        
        for (int i = 0; i < points.size(); i++) {
            BigInteger term = points.get(i).y;
            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;
            
            for (int j = 0; j < points.size(); j++) {
                if (i != j) {
                    numerator = numerator.multiply(points.get(j).x.negate());
                    denominator = denominator.multiply(points.get(i).x.subtract(points.get(j).x));
                }
            }
            
            term = term.multiply(numerator);
            BigInteger denominatorInverse = denominator.modInverse(BigInteger.valueOf(Long.MAX_VALUE));
            term = term.multiply(denominatorInverse);
            
            result = result.add(term);
        }
        
        return result.mod(BigInteger.valueOf(Long.MAX_VALUE));
    }
    
    public static void main(String[] args) {
        try {
            // Test Case 1
            Map<Integer, String[]> points1 = new HashMap<>();
            points1.put(1, new String[]{"10", "4"});
            points1.put(2, new String[]{"2", "111"});
            points1.put(3, new String[]{"10", "12"});
            points1.put(6, new String[]{"4", "213"});
            
            List<Point> testCase1Points = parseTestCase(4, 3, points1);
            BigInteger secret1 = findConstantTerm(testCase1Points);
            
            // Test Case 2
            Map<Integer, String[]> points2 = new HashMap<>();
            points2.put(1, new String[]{"7", "420020006424065463"});
            points2.put(2, new String[]{"7", "10511630252064643035"});
            points2.put(3, new String[]{"2", "101010101001100101011100000001000111010010111101100100010"});
            points2.put(4, new String[]{"8", "31261003022226126015"});
            points2.put(5, new String[]{"7", "2564201006101516132035"});
            points2.put(6, new String[]{"15", "a3c97ed550c69484"});
            points2.put(7, new String[]{"13", "134b08c8739552a734"});
            points2.put(8, new String[]{"10", "23600283241050447333"});
            points2.put(9, new String[]{"9", "375870320616068547135"});
            points2.put(10, new String[]{"6", "30140555423010311322515333"});
            
            List<Point> testCase2Points = parseTestCase(10, 7, points2);
            BigInteger secret2 = findConstantTerm(testCase2Points);
            
            System.out.println("Secret for Test Case 1: " + secret1);
            System.out.println("Secret for Test Case 2: " + secret2);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

