# Java 8 to 17 Upgrade Exercises

## Exercise 1: Local Variable Type Inference (Java 10)

### Objective:
Refactor a class to use the `var` keyword where appropriate, improving code readability without losing type information.

### Steps:
1. Start with the following class:

```java
public class UserManager {
    public List<User> filterActiveUsers(List<User> users) {
        List<User> activeUsers = new ArrayList<>();
        for (User user : users) {
            if (user.isActive()) {
                activeUsers.add(user);
            }
        }
        Map<String, User> userMap = new HashMap<>();
        for (User user : activeUsers) {
            userMap.put(user.getEmail(), user);
        }
        return new ArrayList<>(userMap.values());
    }
}
```

2. Refactor the method to use `var` where appropriate:
    - Replace the explicit type declaration of `activeUsers` with `var`
    - Use `var` in the for-each loops
    - Replace the explicit type declaration of `userMap` with `var`

3. Ensure the code still compiles and functions correctly.

4. Discuss the benefits and potential drawbacks of using `var` in this context.

## Exercise 2: Switch Expressions (Java 12 and 14)

### Objective:
Convert a traditional switch statement to a switch expression, including the use of the `yield` keyword.

### Steps:
1. Start with the following enum and method:

```java
enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }

public class DayPlanner {
    public String getActivity(Day day) {
        String activity;
        switch (day) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                activity = "Work";
                break;
            case SATURDAY:
                activity = "Leisure";
                break;
            case SUNDAY:
                activity = "Rest";
                break;
            default:
                activity = "Unknown";
        }
        return activity;
    }
}
```

2. Refactor the `getActivity` method to use a switch expression:
    - Replace the switch statement with a switch expression
    - Use arrow syntax (`->`) for simple cases
    - Use a block with `yield` for the weekend cases to demonstrate both syntaxes

3. Ensure the code compiles and produces the same results as before.

4. Discuss the benefits of switch expressions in terms of conciseness and error prevention.

## Exercise 3: Records (Java 16)

### Objective:
Create a record to represent immutable data and compare it with a traditional class implementation.

### Steps:
1. Start with the following traditional class:

```java
public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
```

2. Create an equivalent `Point` record:
    - Define a record named `Point` with `int x` and `int y` components
    - Notice how the constructor, getters, equals, hashCode, and toString methods are automatically generated

3. Write a small program to demonstrate the usage of both the class and the record:
    - Create instances of both
    - Compare them for equality
    - Print them to see the toString output

4. Discuss the benefits of records in terms of conciseness, immutability, and suitability for data transfer objects.

## Exercise 4: Text Blocks (Java 15)

### Objective:
Refactor a class that deals with multi-line strings to use text blocks, improving readability and maintainability.

### Steps:
1. Start with the following class:

```java
public class SqlQueries {
    public static final String SELECT_USERS = "SELECT id, name, email " +
            "FROM users " +
            "WHERE status = 'active' " +
            "AND last_login > DATE_SUB(NOW(), INTERVAL 30 DAY)";

    public static final String INSERT_USER = "INSERT INTO users " +
            "(name, email, status, created_at) " +
            "VALUES (?, ?, 'active', NOW())";

    public static final String USER_JSON_TEMPLATE = "{\n" +
            "  \"id\": %d,\n" +
            "  \"name\": \"%s\",\n" +
            "  \"email\": \"%s\",\n" +
            "  \"status\": \"%s\"\n" +
            "}";
}
```

2. Refactor each string constant to use a text block:
    - Replace the concatenated strings with text blocks for SQL queries
    - Convert the JSON template to a text block

3. Ensure the code compiles and the strings are equivalent to their original versions.

4. Discuss the benefits of text blocks in terms of readability and ease of maintenance, especially for SQL, JSON, HTML, or other structured text.

## Exercise 5: Pattern Matching for instanceof (Java 16)

### Objective:
Refactor code that uses instanceof checks to use pattern matching, reducing verbosity and potential errors.

### Steps:
1. Start with the following code:

```java
public class ShapeCalculator {
    public double calculateArea(Object shape) {
        if (shape instanceof Rectangle) {
            Rectangle r = (Rectangle) shape;
            return r.getWidth() * r.getHeight();
        } else if (shape instanceof Circle) {
            Circle c = (Circle) shape;
            return Math.PI * c.getRadius() * c.getRadius();
        } else if (shape instanceof Triangle) {
            Triangle t = (Triangle) shape;
            return 0.5 * t.getBase() * t.getHeight();
        } else {
            throw new IllegalArgumentException("Unknown shape");
        }
    }
}

class Rectangle {
    private double width;
    private double height;
    // constructor and getters
}

class Circle {
    private double radius;
    // constructor and getter
}

class Triangle {
    private double base;
    private double height;
    // constructor and getters
}
```

2. Refactor the `calculateArea` method to use pattern matching with instanceof:
    - Replace each instanceof check and subsequent cast with a single pattern matching instanceof
    - Use the bound variable directly in the calculation

3. Ensure the code compiles and functions correctly.

4. Discuss the benefits of pattern matching in terms of code conciseness and safety.

## Exercise 6: Sealed Classes (Java 17)

### Objective:
Design a small class hierarchy using sealed classes and interfaces to restrict which classes can implement or extend them.

### Steps:
1. Create a sealed interface `Shape` with the following permitted subclasses: `Circle`, `Rectangle`, and `Triangle`.

2. Implement each subclass, ensuring they are either `final`, `sealed`, or `non-sealed`.

3. Add an abstract `area()` method to the `Shape` interface and implement it in each subclass.

4. Create a `ShapeCalculator` class with a method that uses pattern matching to calculate the area of any given shape.

5. Write a small program to demonstrate the usage of your sealed class hierarchy:
    - Create instances of each shape
    - Use the `ShapeCalculator` to calculate and print their areas

6. Try to create a new class that extends `Shape` outside of the permitted subclasses and observe the compilation error.

7. Discuss the benefits of sealed classes in terms of maintaining a closed set of subtypes and facilitating exhaustive pattern matching.

## Exercise 7: Enhanced NullPointerExceptions (Java 14)

### Objective:
Debug a program with null pointer exceptions, utilizing the more detailed error messages to quickly identify the source of the problem.

### Steps:
1. Start with the following code that contains potential null pointer exceptions:

```java
public class UserProcessor {
    public void processUser(User user) {
        String username = user.getName().toUpperCase();
        Address address = user.getAddress();
        String city = address.getCity().trim();
        System.out.println("User " + username + " lives in " + city);
    }
}

class User {
    private String name;
    private Address address;
    // getters and setters
}

class Address {
    private String city;
    // getter and setter
}

public class Main {
    public static void main(String[] args) {
        UserProcessor processor = new UserProcessor();
        User user = new User();
        user.setName("John");
        processor.processUser(user);
    }
}
```

2. Run the program and observe the enhanced NullPointerException message.

3. Use the detailed information in the error message to identify which part of the expression was null.

4. Fix the null pointer exception by adding appropriate null checks or initializing the objects properly.

5. Discuss how the enhanced NullPointerException messages improve the debugging process and code quality.

