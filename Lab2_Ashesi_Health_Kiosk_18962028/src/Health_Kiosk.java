import java.util.Scanner;
import java.util.Random;

public class Health_Kiosk {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // TASK 1: Service Router
        System.out.println("Welcome to Ashesi Health Kiosk");
        System.out.print("Please select one of these services (P/L/T/C): ");
        char service_code = scanner.next().charAt(0);
        service_code = Character.toUpperCase(service_code);

        switch (service_code) {
            case 'P':
                System.out.println("Go to: Pharmacy Desk");
                break;
            case 'L':
                System.out.println("Go to: Lab Desk");
                break;
            case 'T':
                System.out.println("Go to: Triage Desk");
                break;
            case 'C':
                System.out.println("Go to: Counseling Desk");
                break;
            default:
                System.out.println("Invalid service code");
        }

        // TASK 2: Health Metric
        double metric_value = 0;
        double bmi = 0;
        System.out.println("What health metric do you want? (1 for BMI, 2 for Dosage round-up, 3 for Simple trig help): ");
        int health_metric = scanner.nextInt();

        if (health_metric == 1) {
            System.out.print("Please enter your weight(kg): ");
            double weight = scanner.nextDouble();
            System.out.print("Please enter your height(m): ");
            double height = scanner.nextDouble();

            bmi = weight / (height * height);
            bmi = Math.round(bmi * 10) / 10.0; // round to 1 decimal
            metric_value = bmi;

            System.out.println("Your BMI is: " + bmi);
            if (bmi < 18.5) {
                System.out.println("Category: Underweight");
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                System.out.println("Category: Normal");
            } else if (bmi >= 25 && bmi <= 29.9) {
                System.out.println("Category: Overweight");
            } else {
                System.out.println("Category: Obese");
            }
        } else if (health_metric == 2) {
            System.out.print("Please enter the required dosage(mg): ");
            double dosage = scanner.nextDouble();
            double number_of_Tablets = Math.ceil(dosage / 250.0);
            metric_value = number_of_Tablets;

            System.out.println("Number of Tablets: " + (int) number_of_Tablets);
        } else if (health_metric == 3) {
            System.out.print("Enter an angle in degrees: ");
            double degrees = scanner.nextDouble();
            double radians = Math.toRadians(degrees);

            double sinVal = Math.round(Math.sin(radians) * 1000) / 1000.0;
            double cosVal = Math.round(Math.cos(radians) * 1000) / 1000.0;
            metric_value = Math.round(Math.sin(radians) * 100); // sin(angle) * 100 rounded

            System.out.println("sin: " + sinVal);
            System.out.println("cos: " + cosVal);
        }

        // TASK 3: ID Sanity Check
        char randomChar = (char) ('A' + (int) (Math.random() * 26));
        Random rand = new Random();
        int num1 = 3 + rand.nextInt(7);
        int num2 = 3 + rand.nextInt(7);
        int num3 = 3 + rand.nextInt(7);
        int num4 = 3 + rand.nextInt(7);
        String id = "" + randomChar + num1 + num2 + num3 + num4;

        System.out.println("Student ID: " + id);

        if (id.length() != 5) {
            System.out.println("Invalid length");
        } else if (!Character.isLetter(id.charAt(0))) {
            System.out.println("Invalid: first character must be a letter");
        } else if (!(Character.isDigit(id.charAt(1)) && Character.isDigit(id.charAt(2))
                && Character.isDigit(id.charAt(3)) && Character.isDigit(id.charAt(4)))) {
            System.out.println("Invalid: last 4 must be digits");
        } else {
            System.out.println("ID OK");
        }

        // TASK 4: Secure Display Code
        scanner.nextLine(); // clear buffer
        System.out.print("Please enter your first name: ");
        String name = scanner.nextLine();
        char firstChar = Character.toUpperCase(name.charAt(0));
        char shiftedLetter = (char) ('A' + (firstChar - 'A' + 2) % 26);

        String lastTwo = id.substring(3); // last 2 digits
        int roundedMetric = (int) Math.round(metric_value);

        String code = shiftedLetter + lastTwo + "-" + roundedMetric;
        System.out.println("Display Code: " + code);

        // TASK 5: Service Summary
        System.out.println("Summary:");
        switch (service_code) {
            case 'P':
                System.out.println("PHARMACY | ID=" + id + " | Code=" + code);
                break;
            case 'T':
                System.out.println("TRIAGE | ID=" + id + " | BMI=" + bmi + " | Code=" + code);
                break;
            case 'L':
                System.out.println("LAB | ID=" + id + " | Code=" + code);
                break;
            case 'C':
                System.out.println("COUNSELING | ID=" + id + " | Code=" + code);
                break;
            default:
                System.out.println("Invalid service code");
        }
    }
}