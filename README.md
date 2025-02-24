# Polynomial Secret Recovery

## Overview
This project implements a simplified version of **Shamir's Secret Sharing** algorithm to recover the constant term **c** of a polynomial given a set of encoded roots. The program reads input in **JSON format**, decodes values stored in different bases, and applies **Lagrange interpolation** to determine the secret constant term.

## Problem Statement
Given an **unknown polynomial of degree m**:

\[ f(x) = a_m x^m + a_{m-1} x^{m-1} + ... + a_1 x + c \]

where:
- \( m \) is the degree of the polynomial.
- \( c \) is the constant term (the secret to be recovered).
- The polynomial is uniquely determined using **k = m + 1** roots.
- The given input provides roots in a structured format but encoded in different numerical bases.

### Example Input (JSON Format)
```json
{
    "keys": {
        "n": 4,
        "k": 3
    },
    "1": {
        "base": "10",
        "value": "4"
    },
    "2": {
        "base": "2",
        "value": "111"
    },
    "3": {
        "base": "10",
        "value": "12"
    },
    "6": {
        "base": "4",
        "value": "213"
    }
}
```

### Explanation
- The key represents \( x \) values.
- The corresponding value is stored in different number bases.
- These values need to be decoded to decimal before processing.

## Approach
1. **Read and Parse JSON Input**  
   - Extract the number of roots \( n \) and the required minimum roots \( k \).
   - Decode \( y \) values from their respective bases.
2. **Polynomial Interpolation (Lagrange Interpolation Method)**  
   - Use a subset of \( k \) points to reconstruct the polynomial.
   - Compute the constant term \( c \).

## Implementation Details
- The program is implemented in **Java**.
- Uses **BigInteger** for handling large numbers.
- Leverages **Lagrange interpolation** for polynomial reconstruction.

## Running the Program
1. Compile the Java program:
   ```sh
   javac PolynomialSecret.java
   ```
2. Run the program:
   ```sh
   java PolynomialSecret
   ```

## Output
The program prints the recovered secret constants for the test cases:
```
Secret for Test Case 1: <calculated_value>
Secret for Test Case 2: <calculated_value>
```

## Dependencies
- Java Development Kit (JDK) 8+

## Constraints
- Coefficients are **positive integers** within a **256-bit range**.
- The number of provided roots \( n \) is always greater than or equal to \( k \).
- The program only requires a **valid JSON input file** to work.

## Future Improvements
- Extend the solution to handle larger polynomials.
- Implement alternative interpolation methods for efficiency.
- Improve input handling for dynamic test cases.

---
### Author
Developed as part of **Catalog Placements Assignment - Offline**.

