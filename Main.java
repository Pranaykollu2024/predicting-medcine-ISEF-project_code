import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Case {
    private String defendant;
    private String crime;
    private List<String> evidence;
    private String juryDecision;

    public Case(String defendant, String crime, List<String> evidence) {
        this.defendant = defendant;
        this.crime = crime;
        this.evidence = evidence;
        this.juryDecision = null;
    }

    public void presentCase() {
        System.out.println("Defendant: " + this.defendant);
        System.out.println("Crime: " + this.crime);
        System.out.println("Evidence: " + String.join(", ", this.evidence));
    }

    public void runTrial() {
        // Simulating jury deliberation
        System.out.println("Jury is deliberating...");
        
        // Random outcome based on the strength of the evidence
        double juryChanceOfConviction = calculateJuryChance();
        Random random = new Random();
        this.juryDecision = random.nextDouble() < juryChanceOfConviction ? "Guilty" : "Not Guilty";
        System.out.println("Jury Decision: " + this.juryDecision);
    }

    private double calculateJuryChance() {
        // Simulates the chance of conviction based on evidence
        if (this.evidence.size() > 3) {
            return 0.75;  // Strong evidence
        } else if (this.evidence.size() == 3) {
            return 0.5;   // Moderate evidence
        } else {
            return 0.25;  // Weak evidence
        }
    }
}

public class TrialSimulator {
    public static void simulateTrial() {
        // Case 1: Strong evidence
        List<String> evidence1 = new ArrayList<>();
        evidence1.add("Fingerprint on weapon");
        evidence1.add("Witness testimony");
        evidence1.add("DNA match");
        evidence1.add("Surveillance footage");
        
        Case case1 = new Case("John Doe", "Robbery", evidence1);
        case1.presentCase();
        case1.runTrial();

        // Case 2: Moderate evidence
        List<String> evidence2 = new ArrayList<>();
        evidence2.add("Footage of crime");
        evidence2.add("DNA does not match");
        evidence2.add("Witness testimony");
        evidence2.add("Weapon at scene of crime");
        
        Case case2 = new Case("Jane Doe", "Shoplifting", evidence2);
        case2.presentCase();
        case2.runTrial();
        
        // Add more cases as needed
    }

    public static void main(String[] args) {
        simulateTrial();
    }
}
