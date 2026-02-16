import java.util.ArrayList;
import java.util.List;

interface MonitoringComponent {
    void execute();
}

interface IdentityVerification extends MonitoringComponent {
}

interface BehaviourMonitoring extends MonitoringComponent {
}

class AIIdentityVerification implements IdentityVerification {
    public void execute() {
        System.out.println("AI-based Identity Verification running...");
    }
}

class BiometricIdentityVerification implements IdentityVerification {
    public void execute() {
        System.out.println("Biometric Identity Verification running...");
    }
}

class AIBehaviourMonitoring implements BehaviourMonitoring {
    public void execute() {
        System.out.println("AI-based Behaviour Monitoring running...");
    }
}

class HumanBehaviourMonitoring implements BehaviourMonitoring {
    public void execute() {
        System.out.println("Human-assisted Behaviour Monitoring running...");
    }
}

class ProctoringPipeline {

    private List<MonitoringComponent> steps = new ArrayList<>();

    public void addStep(MonitoringComponent step) {
        steps.add(step);
    }

    public void executePipeline() {
        for (MonitoringComponent step : steps) {
            step.execute();
        }
    }
}

class ProctoringController {

    private ProctoringPipeline pipeline;

    public ProctoringController(ProctoringPipeline pipeline) {
        this.pipeline = pipeline;
    }

    public void startExam() {
        System.out.println("Exam Started...");
        pipeline.executePipeline();
        System.out.println("Exam Monitoring Completed.");
    }
}

public class Main {

    public static void main(String[] args) {

        ProctoringPipeline pipeline = new ProctoringPipeline();

        pipeline.addStep(new AIIdentityVerification());
        pipeline.addStep(new HumanBehaviourMonitoring());

        ProctoringController controller = new ProctoringController(pipeline);

        controller.startExam();
    }
}
